/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.geodienstencentrum.maven.plugin.closure.compiler;

import java.io.File;
import java.io.IOException;

import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Mark Prins <mark@b3partners.nl>
 */
public class ClosureCompilerMojoIntegrationTest {

    /**
     * The groupId of the test project.
     */
    static final String TEST_GROUPID = "nl.geodienstencentrum.maven.test";
    /**
     * The version of the test project.
     */
    static final String TEST_VERSION = "1.0-SNAPSHOT";

    /**
     * The Maven verifier.
     */
    private Verifier verifier;

    /**
     * The test source directory.
     */
    private File testDir;

    /**
     * The artifactId of the test project.
     */
    private final String ARTIFACTID = "maven-closurecompiler-test";

    /**
     * The packaging of the test project.
     */
    private final String PACKAGING = "war";

    /**
     * setUp the Maven project and verifier, execute the 'compile' goal.
     *
     * @throws IOException if any
     * @throws VerificationException if any
     */
    @Before
    public void setUp() throws IOException, VerificationException {
        this.testDir = ResourceExtractor.simpleExtractResources(
                this.getClass(), "/" + this.ARTIFACTID);
        this.verifier = new Verifier(this.testDir.getAbsolutePath());
        this.verifier.deleteArtifact(TEST_GROUPID, ARTIFACTID, TEST_VERSION, PACKAGING);
        boolean debug = new Boolean(System.getProperty("debug"));
        this.verifier.setMavenDebug(debug);
        this.verifier.executeGoal("compile");
    }

    /**
     * execute the 'clean' goal.
     *
     * @throws VerificationException if any
     */
    @After
    public void tearDown() throws VerificationException {
        this.verifier.setMavenDebug(false);
        //this.verifier.executeGoal("clean");
        this.verifier.resetStreams();
    }

    /**
     * test for error free execution.
     *
     * @throws Exception if any
     */
    @Test
    public void testErrorFree() throws Exception {
        this.verifier.verifyErrorFreeLog();

        final String compileddebug = this.verifier.getBasedir() + File.separator
                + "target" + File.separator + this.ARTIFACTID + "-"
                + TEST_VERSION + File.separator + "static" + File.separator
                + "tools.debug.js";

        this.verifier.assertFilePresent(compileddebug);
        this.verifier.verifyTextInLog("Compiling with level: WHITESPACE_ONLY");

        final String compiled = this.verifier.getBasedir() + File.separator
                + "target" + File.separator + this.ARTIFACTID + "-"
                + TEST_VERSION + File.separator + "static" + File.separator
                + "tools.min.js";
        this.verifier.assertFilePresent(compiled);
        this.verifier.verifyTextInLog("Compiling with level: ADVANCED_OPTIMIZATIONS");
    }
}
