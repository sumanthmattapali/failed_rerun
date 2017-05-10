package com.webydo.tools.FailedRerun;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * The builder of a scenario for repeated running failed tests
 */
public abstract class RepeatScenarioBuilder {
    final protected Scenario scenario;
    final protected String scenarioName;
    final private String failedScenarioPath;

    public RepeatScenarioBuilder(final Scenario scenario, String scenarioName, String failedScenarioPath) throws InvalidParameterException {
        if(failedScenarioPath == null || failedScenarioPath.isEmpty())
            throw new InvalidParameterException("The given failed scenario path is NULL or EMPTY");
        this.failedScenarioPath = failedScenarioPath;

        if(scenario == null)
            throw new InvalidParameterException("The given scenario instance is NULL");
        this.scenario = scenario;

        if(scenarioName == null || scenarioName.isEmpty())
            throw new InvalidParameterException("The given scenario name is NULL or EMPTY");
        this.scenarioName = scenarioName;
    }

    abstract public void buildNewScenario();
    abstract protected Scenario createNewScenario();
    abstract protected List<Test> getFailedTests();
    public void save() {

    }
}
