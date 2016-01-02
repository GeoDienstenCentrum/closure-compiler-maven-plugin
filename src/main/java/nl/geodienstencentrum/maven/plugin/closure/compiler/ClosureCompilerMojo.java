/*
 * Copyright 2015-2016 Mark Prins, GeoDienstenCentrum.
 * Copyright 2012-2014 Mason Bryant
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package nl.geodienstencentrum.maven.plugin.closure.compiler;

import  com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import com.google.javascript.jscomp.CompilationLevel;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.JSError;
import com.google.javascript.jscomp.Result;
import com.google.javascript.jscomp.SourceFile;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * The closure compiler compiles a set of configured compilations.
 */
@Mojo(name = "compile", defaultPhase = LifecyclePhase.COMPILE, threadSafe = true)
public class ClosureCompilerMojo extends AbstractMojo {

	/**
	 * The list of compilations.
	 */
	@Parameter(required = true)
	private List<Compilation> compilations;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		Log log = getLog();
		Preconditions.checkNotNull(this.compilations);

		for (Compilation compilation : this.compilations) {
			List<SourceFile> externs = getSourceFiles(compilation.getExternFiles());
			List<SourceFile> source = getSourceFiles(compilation.getSourceFiles());

			for (String sourceFile : compilation.getExternFiles()) {
				log.debug("Compiling from extern: " + sourceFile);
			}
			for (String sourceFile : compilation.getSourceFiles()) {
				log.debug("Compiling from source: " + sourceFile);
			}// this message is echecked for in the integration tests
			log.info("Compiling " + compilation.getOutputFile() + " with level: " + compilation.getCompilationLevel());

			CompilationLevel compilationLevel = null;
			try {
				compilationLevel = CompilationLevel.valueOf(compilation.getCompilationLevel());
			} catch (IllegalArgumentException e) {
				throw new MojoFailureException("Compilation level invalid", e);
			}

			CompilerOptions compilerOptions = compilation.getCompilerOptionsMojo().getCompilerOptions();
			if (null == compilerOptions) {
				log.debug("Compiling with default compiler options");
				compilerOptions = new CompilerOptions();
			}
			log.debug("Compiler Options:" + compilerOptions);
			compilationLevel.setOptionsForCompilationLevel(compilerOptions);

			Compiler compiler = new Compiler();
			Result result = compiler.compile(externs, source, compilerOptions);

			for (JSError warning : result.warnings) {
				getLog().warn(warning.toString());
			}
			for (JSError error : result.errors) {
				getLog().error(error.toString());
			}
			if (!result.success) {
				throw new MojoFailureException("Compilation failure, check messages above.");
			}

			try {
				Files.createParentDirs(compilation.getOutputFile());
				Files.touch(compilation.getOutputFile());
				Files.write(compiler.toSource(), compilation.getOutputFile(), Charsets.UTF_8);
			} catch (IOException e) {
				throw new MojoFailureException(compilation.getOutputFile() != null
						? compilation.getOutputFile().toString() : e.getMessage(), e);
			}
		}
	}

	/**
	 * Returns a list of SourceFiles if given a list of relative or absolute
	 * file names. Directories are traversed recursively.
	 *
	 * @param filePaths a list of paths
	 * @return a list of SourceFile
	 */
	private List<SourceFile> getSourceFiles(final List<String> filePaths) {
		Preconditions.checkNotNull(filePaths, "Filepaths are required.");

		List<SourceFile> externs = new LinkedList<>();
		for (String filepath : filePaths) {
			File file = new File(filepath);
			externs.addAll(sourcefileFromFile(file));
		}
		return externs;
	}

	/**
	 * Fetch a list of SourceFiles from a file. If the file is a directory, it
	 * is traversed recursively. If not, a single SourceFile is returned.
	 *
	 * @param file a file or a directory
	 * @return a list of SourceFile
	 */
	private List<SourceFile> sourcefileFromFile(final File file) {
		Preconditions.checkNotNull(file, "The file may not be null.");
		Preconditions.checkArgument(file.exists(), "The file must exist.");
		List<SourceFile> sourceFiles = new LinkedList<>();
		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				sourceFiles.addAll(sourcefileFromFile(child));
			}
		} else {
			sourceFiles.add(SourceFile.fromFile(file));
		}
		return sourceFiles;
	}
}
