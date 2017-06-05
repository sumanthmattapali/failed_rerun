package com.webydo.tools.FailedRerun;

import com.webydo.tools.FailedRerun.junit.ResultsJunit;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;

import static junit.framework.TestCase.assertTrue;

/**
 * Junit results tests
 */
public class JunitResultsTest {
    Path curDir = new File(".").getAbsoluteFile().toPath();

    @org.junit.Test
     public void nonExistingFile() {
        try {
            new ResultsJunit(new File("non-existing").getAbsoluteFile().toPath());
            assertTrue("Checking non-existing Junit results file", false);
        }
        catch (InvalidParameterException e) {
            assertTrue(true);
        }

//        builder.buildNewScenario(Paths.get(curDir + "/src/test/scenarious/jsystem/new.xml"), Paths.get(curDir + "/src/test/scenarious/jsystem/failed_scenario.xml"));
    }

    @org.junit.Test
    public void invalidJunitResultsFile() {
        try {
            new ResultsJunit(Paths.get(curDir + "/src/test/scenarious/jsystem/invalid.xml"));
            assertTrue("Checking invalid Junit results file", false);
        }
        catch (InvalidParameterException e) {
            assertTrue("The thrown exception should be about failed initialization of a XML file", e.getMessage().startsWith("Can't initialize the XML in the file"));
        }
    }

    @org.junit.Test
    public void failedTests() {
        ResultsJunit results = new ResultsJunit(Paths.get(curDir + "/src/test/scenarious/jsystem/failed_results.xml"));
        assertTrue("Checking the number of failed tests in the given JUnit results file", results.getFailedTests().size() == 3);
    }

    @org.junit.Test
    public void noFailedTests() {
        ResultsJunit results = new ResultsJunit(Paths.get(curDir + "/src/test/scenarious/jsystem/no_failed_results.xml"));
        assertTrue("Checking no failed tests in the given JUnit results file", results.getFailedTests().size() == 0);
    }
}
