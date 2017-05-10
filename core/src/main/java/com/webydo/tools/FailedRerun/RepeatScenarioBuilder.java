package com.webydo.tools.FailedRerun;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.List;

/**
 * The builder of a scenario for repeated running failed tests
 */
public abstract class RepeatScenarioBuilder {
    private static final Logger logger = LogManager.getLogger(RepeatScenarioBuilder.class);

    final protected Scenario scenario;
    final protected String scenarioName;
    final protected String failedScenarioPath;

    public RepeatScenarioBuilder(final Scenario scenario, String scenarioName, String failedScenarioPath) throws InvalidParameterException {
        if(failedScenarioPath == null || failedScenarioPath.isEmpty())
            throw new InvalidParameterException("The given failed scenario path is NULL or EMPTY");
        if (!new File(failedScenarioPath).exists())
            throw new InvalidParameterException("The given failed scenario path " + failedScenarioPath + " doesn't exist");
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
        logger.info("Save the created scenario " + scenarioName);
    }
}
