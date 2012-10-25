package com.sandwormz.maven.closure;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.javascript.jscomp.CompilationLevel;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.JSError;
import com.google.javascript.jscomp.Result;
import com.google.javascript.jscomp.SourceFile;

/**
 * Example configuration:
 * 
 * <pre>
 * {@code
 * 
 * <plugin>
 *     <groupId>com.sandwormz</groupId>
 *     <artifactId>closure-compiler-maven-plugin</artifactId>
 *     <version>0.0.2-SNAPSHOT</version>
 *     <executions>
 *         <execution>
 *             <goals>
 *                 <goal>compile</goal>
 *             </goals>
 *         </execution>
 *     </executions>
 *     <configuration>
 *         <compilations>
 *             <compilation>
 *                 <compilationLevel>WHITESPACE_ONLY</compilationLevel>
 *                 <compilerOptions>
 *                    <prettyPrint>true</prettyPrint>
 *                    <lineBreak>true</lineBreak>
 *                    <runtimeTypeCheck>true</runtimeTypeCheck>
 *                 </compilerOptions>
 *                 <externFiles></externFiles>
 *                 <sourceFiles>
 *                     <param>${basedir}/src/main/web/js/_header.js</param>
 *                     <param>${basedir}/src/main/web/js/asserts.js</param>
 *                     <param>${basedir}/src/main/web/js/logger.js</param>
 *                 </sourceFiles>
 *                 <outputFile>${basedir}/src/main/web/devtools-all.js</outputFile>
 *             </compilation>
 *             <compilation>
 *                 <compilationLevel>ADVANCED_OPTIMIZATIONS</compilationLevel>
 *                 <compilerOptions>
 *                     <prettyPrint>true</prettyPrint>
 *                 </compilerOptions>
 *                 <externFiles></externFiles>
 *                 <sourceFiles>
 *                     <param>${basedir}/src/main/web/js/_header.js</param>
 *                     <param>${basedir}/src/main/web/js/asserts.js</param>
 *                     <param>${basedir}/src/main/web/js/logger.js</param>
 *                 </sourceFiles>
 *                 <outputFile>${project.build.outputDirectory}/static/js/devtools.js</outputFile>
 *             </compilation>
 *         </compilations>
 *     </configuration>
 * </plugin>
 * }
 * </pre>
 * 
 * @phase generate-sources
 * @goal compile
 */
public class ClosureCompilerMojo extends AbstractMojo {

    /**
     * @parameter
     * @required
     */
    private List<CompliationMojo> compilations;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Log log = getLog();

        for (CompliationMojo compilation : this.compilations) {

            List<SourceFile> externs =
                    getSourceFiles(compilation.getExternFiles());
            List<SourceFile> source =
                    getSourceFiles(compilation.getSourceFiles());

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
                compilationLevel =
                        CompilationLevel.valueOf(compilation
                                .getCompilationLevel());
            } catch (IllegalArgumentException e) {
                throw new MojoFailureException("Compilation level invalid", e);
            }

            CompilerOptions compilerOptions =
                    compilation.getCompilerOptionsMojo().getCompilerOptions();
            if (null == compilerOptions) {
                log.info("With no compiler options");
                compilerOptions = new CompilerOptions();
            }
            log.info("Compiler Options:" + compilerOptions);

            compilationLevel.setOptionsForCompilationLevel(compilerOptions);

            Compiler compiler = new Compiler();
            Result result = compiler.compile(externs, source, compilerOptions);

            // TODO: should log results to a file if desired.
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
     * @return
     */
    private List<SourceFile> getSourceFiles(final List<String> filePaths) {

        List<SourceFile> externs = new LinkedList<SourceFile>();
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
     * @return
     */
    private List<SourceFile> sourcefileFromFile(final File file) {
        List<SourceFile> sourceFiles = new LinkedList<SourceFile>();
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
