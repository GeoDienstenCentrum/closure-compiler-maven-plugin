/*
 * Copyright 2015-2016 Mark Prins, GeoDienstenCentrum.
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
import java.io.IOException;
import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mprins
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
		this.verifier.executeGoal("clean");
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
		this.verifier.verifyTextInLog("with level: WHITESPACE_ONLY");

		final String compiled = this.verifier.getBasedir() + File.separator
				+ "target" + File.separator + this.ARTIFACTID + "-"
				+ TEST_VERSION + File.separator + "static" + File.separator
				+ "tools.min.js";
		this.verifier.assertFilePresent(compiled);
		this.verifier.verifyTextInLog("with level: ADVANCED_OPTIMIZATIONS");
	}
}
