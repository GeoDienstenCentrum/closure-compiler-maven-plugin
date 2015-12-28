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
    private CompilerOptionsMojo compilerOptions = new CompilerOptionsMojo();

    /**
     * Eg: SIMPLE_OPTIMIZATIONS, ADVANCED_OPTIMIZATIONS, or WHITESPACE_ONLY
     *
     */
    @Parameter(required = true, defaultValue = "WHITESPACE_ONLY")
    private String compilationLevel;

    /**
     *
     */
    @Parameter(required = true)
    private List<String> externFiles;

    /**
     *
     */
    @Parameter(required = true)
    private List<String> sourceFiles;

    /**
     *
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
     * @return the externFiles
     */
    public List<String> getExternFiles() {
        return this.externFiles;
    }

    /**
     * @return the outputFile
     */
    public File getOutputFile() {
        return this.outputFile;
    }

    /**
     * @return the sourceFiles
     */
    public List<String> getSourceFiles() {
        return this.sourceFiles;
    }
}
