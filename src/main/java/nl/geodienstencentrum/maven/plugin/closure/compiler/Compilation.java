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

import java.io.File;
import java.util.List;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Describe a compilation cycle.
 *
 * @author mbryant
 * @author mprins
 */
public class Compilation {

	/**
	 * Optional options for this compilation.
	 */
	@Parameter(property = "compilerOptions")
	private final CompilerOptionsMojo compilerOptions = new CompilerOptionsMojo();

	/**
	 * level of compilation, valid values are: SIMPLE_OPTIMIZATIONS,
	 * ADVANCED_OPTIMIZATIONS, or WHITESPACE_ONLY *
	 */
	@Parameter(required = true, defaultValue = "WHITESPACE_ONLY")
	private String compilationLevel;

	/**
	 * A list of externs.
	 */
	@Parameter(required = true)
	private List<String> externFiles;

	/**
	 * a list of source files.
	 */
	@Parameter(required = true)
	private List<String> sourceFiles;

	/**
	 * the name of the output file.
	 */
	@Parameter(required = true, defaultValue = "${project.build.directory}/${project.build.finalName}/js/${project.artifactId}.js")
	private File outputFile;

	/**
	 * @return the compilationLevel
	 */
	public String getCompilationLevel() {
		return this.compilationLevel;
	}

	/**
	 * @return the compilerOptions
	 */
	public CompilerOptionsMojo getCompilerOptionsMojo() {
		return this.compilerOptions;
	}

	/**
	 * Accessor for the externs.
	 *
	 * @return the externFiles
	 */
	public List<String> getExternFiles() {
		return this.externFiles;
	}

	/**
	 * Accessor for the output file.
	 *
	 * @return the outputFile
	 */
	public File getOutputFile() {
		return this.outputFile;
	}

	/**
	 * Accessor for the sources.
	 *
	 * @return the sourceFiles
	 */
	public List<String> getSourceFiles() {
		return this.sourceFiles;
	}
}
