package com.webydo.tools.FailedRerun;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.nio.file.Path;
import java.security.InvalidParameterException;

/**
 * The builder of a scenario for repeated running failed tests
 */
public abstract class RepeatScenarioBuilder {
    private static final Logger logger = LogManager.getLogger(RepeatScenarioBuilder.class);

    protected RepeatScenarioBuilder(final Path reportPath) throws InvalidParameterException {
        if(reportPath == null)
            throw new InvalidParameterException("The given report path is NULL or EMPTY");
        if (!reportPath.toFile().exists())
            throw new InvalidParameterException("The given report path " + reportPath + " doesn't exist");
    }

    public void buildNewScenario(final Path newScenarioPath, final Path failedScenarioPath) {
        final Scenario newScenario = createNewScenario(failedScenarioPath);
        for (Test test: getResults().getFailedTests()) {
            newScenario.addTest(test);
        }
        newScenario.save(newScenarioPath);
    }

    abstract protected Scenario createNewScenario(final Path failedScenarioPath);
    abstract protected Results getResults();
}
