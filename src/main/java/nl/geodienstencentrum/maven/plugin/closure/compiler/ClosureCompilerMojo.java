package nl.geodienstencentrum.maven.plugin.closure.compiler;

import static org.apache.maven.plugins.annotations.LifecyclePhase.COMPILE;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import com.google.javascript.jscomp.CompilationLevel;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.JSError;
import com.google.javascript.jscomp.Result;
import com.google.javascript.jscomp.SourceFile;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 */
@Mojo(name = "compile", defaultPhase = COMPILE)
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
            List<SourceFile> externs
                    = getSourceFiles(compilation.getExternFiles());
            List<SourceFile> source
                    = getSourceFiles(compilation.getSourceFiles());

            for (String sourceFile : compilation.getExternFiles()) {
                log.info("Compiling from extern: " + sourceFile);
            }
            for (String sourceFile : compilation.getSourceFiles()) {
                log.info("Compiling from source: " + sourceFile);
            }

            log.info("Compiling to: " + compilation.getOutputFile());
            log.info("Compiling with level: "
                    + compilation.getCompilationLevel());

            CompilationLevel compilationLevel = null;
            try {
                compilationLevel
                        = CompilationLevel.valueOf(compilation
                                .getCompilationLevel());
            } catch (IllegalArgumentException e) {
                throw new MojoFailureException("Compilation level invalid", e);
            }

            CompilerOptions compilerOptions
                    = compilation.getCompilerOptionsMojo().getCompilerOptions();
            if (null == compilerOptions) {
                log.info("With no compiler options");
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
                // TODO: better info?
                throw new MojoFailureException("Compilation failure");
            }

            try {
                Files.createParentDirs(compilation.getOutputFile());
                Files.touch(compilation.getOutputFile());
                Files.write(compiler.toSource(), compilation.getOutputFile(),
                        Charsets.UTF_8);
            } catch (IOException e) {
                throw new MojoFailureException(
                        compilation.getOutputFile() != null ? compilation
                                .getOutputFile().toString() : e.getMessage(), e);
            }
        }
    }

    /**
     * Returns a list of SourceFiles if given a list of relative or absolute
     * file names. Directories are traversed recursively.
     *
     * @param filePaths
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
     * @param file
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
