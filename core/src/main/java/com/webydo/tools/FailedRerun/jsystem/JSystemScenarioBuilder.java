package com.webydo.tools.FailedRerun.jsystem;

import com.webydo.tools.FailedRerun.RepeatScenarioBuilder;
import com.webydo.tools.FailedRerun.Scenario;
import com.webydo.tools.FailedRerun.Test;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * The builder of a JSystem scenario with failed tests
 */
public class JSystemScenarioBuilder extends RepeatScenarioBuilder {
    private static final Logger logger = LogManager.getLogger(JSystemScenarioBuilder.class);

    public JSystemScenarioBuilder(Scenario scenario, String scenarioName, String failedScenarioPath) throws InvalidParameterException {
        super(scenario, scenarioName, failedScenarioPath);
        logger.info("Constructor for the new JSystem scenario " + scenarioName + " of the failed scenario " + failedScenarioPath);
    }

    @Override
    public void buildNewScenario() {
        logger.info("Build the new JSystem scenario " + scenarioName);
    }

    @Override
    protected Scenario createNewScenario() {
        logger.info("Create the new JSystem scenario " + scenarioName);
        return null;
    }

    @Override
    protected List<Test> getFailedTests() {
        logger.info("Get the the failed test of the JSystem scenario " + failedScenarioPath);
        return null;
    }
}
