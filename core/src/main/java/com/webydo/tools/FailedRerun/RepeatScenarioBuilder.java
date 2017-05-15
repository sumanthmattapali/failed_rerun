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

    final protected String failedScenarioPath;

    protected RepeatScenarioBuilder(String failedScenarioPath) throws InvalidParameterException {
        if(failedScenarioPath == null || failedScenarioPath.isEmpty())
            throw new InvalidParameterException("The given failed scenario path is NULL or EMPTY");
        if (!new File(failedScenarioPath).exists())
            throw new InvalidParameterException("The given failed scenario path " + failedScenarioPath + " doesn't exist");
        this.failedScenarioPath = failedScenarioPath;
    }

    public void buildNewScenario(String newScenarioDirPath, String newScenarioName) {
        final Scenario newScenario = createNewScenario(newScenarioName);
        for (Test test: getFailedTests()) {
            newScenario.addTest(test);
        }
        newScenario.save(newScenarioDirPath);
    }

    abstract protected Scenario createNewScenario(String newScenarioName);
    abstract protected List<Test> getFailedTests();
}
