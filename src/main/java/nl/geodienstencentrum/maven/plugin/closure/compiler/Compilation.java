package nl.geodienstencentrum.maven.plugin.closure.compiler;

import java.io.File;
import java.util.List;

/**
 * Created: Oct 15, 2012 12:18:11 PM
 *
 * @author mbryant
 *
 */
public class Compilation {

    /**
     * Optional options for this compilation
     *
     * @parameter property="CompilerOptions"
     */
    private CompilerOptionsMojo compilerOptions = new CompilerOptionsMojo();

    /**
     * Eg: SIMPLE_OPTIMIZATIONS, ADVANCED_OPTIMIZATIONS, or WHITESPACE_ONLY
     *
     * @parameter expression="WHITESPACE_ONLY"
     * @required
     */
    private String compilationLevel;

    /**
     * @parameter @required
     */
    private List<String> externFiles;

    /**
     * @parameter @required
     */
    private List<String> sourceFiles;

    /**
     * @parameter expression=
     * "${project.build.directory}/${project.artifactId}-${project.version}/js/${project.artifactId}.js"
     * @required
     */
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

    /**
     * @param compilationLevel the compilationLevel to set
     */
    public void setCompilationLevel(final String compilationLevel) {
        this.compilationLevel = compilationLevel;
    }

    /**
     * @param compilerOptions the compilerOptions to set
     */
    public void setCompilerOptions(final CompilerOptionsMojo compilerOptions) {
        this.compilerOptions = compilerOptions;
    }

    /**
     * @param externFiles the externFiles to set
     */
    public void setExternFiles(final List<String> externFiles) {
        this.externFiles = externFiles;
    }

    /**
     * @param outputFile the outputFile to set
     */
    public void setOutputFile(final File outputFile) {
        this.outputFile = outputFile;
    }

    /**
     * @param sourceFiles the sourceFiles to set
     */
    public void setSourceFiles(final List<String> sourceFiles) {
        this.sourceFiles = sourceFiles;
    }

}
