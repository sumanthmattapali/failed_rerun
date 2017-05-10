package com.webydo.tools.FailedRerun.jsystem;

import com.webydo.tools.FailedRerun.RepeatScenarioBuilder;
import com.webydo.tools.FailedRerun.Scenario;
import com.webydo.tools.FailedRerun.Test;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * The builder of a JSystem scenario with failed tests
 */
public class JSystemScenarioBuilder extends RepeatScenarioBuilder {

    public JSystemScenarioBuilder(Scenario scenario, String scenarioName, String failedScenarioPath) throws InvalidParameterException {
        super(scenario, scenarioName, failedScenarioPath);
    }

    @Override
    public void buildNewScenario() {

    }

    @Override
    protected Scenario createNewScenario() {
        return null;
    }

    @Override
    protected List<Test> getFailedTests() {
        return null;
    }
}
