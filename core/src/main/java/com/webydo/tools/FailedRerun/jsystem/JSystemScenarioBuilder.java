package com.webydo.tools.FailedRerun.jsystem;

import com.webydo.tools.FailedRerun.RepeatScenarioBuilder;
import com.webydo.tools.FailedRerun.Scenario;
import com.webydo.tools.FailedRerun.Test;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

/**
 * The builder of a JSystem scenario with failed tests
 */
public class JSystemScenarioBuilder extends RepeatScenarioBuilder {
    private static final Logger logger = LogManager.getLogger(JSystemScenarioBuilder.class);

    public JSystemScenarioBuilder(String failedScenarioPath) throws InvalidParameterException {
        super(failedScenarioPath);
    }

    @Override
    protected Scenario createNewScenario(String newScenarioName) {
        return null;
    }

    @Override
    protected List<Test> getFailedTests() {
        logger.info("Get the the failed test of the JSystem scenario " + failedScenarioPath);
        LinkedList<Test> tests = new LinkedList<>();
        return tests;
    }
}
