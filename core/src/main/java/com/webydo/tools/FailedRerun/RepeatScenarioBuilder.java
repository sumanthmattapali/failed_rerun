package com.webydo.tools.FailedRerun;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.util.List;

/**
 * The builder of a scenario for repeated running failed tests
 */
public abstract class RepeatScenarioBuilder {
    private static final Logger logger = LogManager.getLogger(RepeatScenarioBuilder.class);

    final protected Path reportPath;

    protected RepeatScenarioBuilder(final Path reportPath) throws InvalidParameterException {
        if(reportPath == null)
            throw new InvalidParameterException("The given failed scenario path is NULL or EMPTY");
        if (!reportPath.toFile().exists())
            throw new InvalidParameterException("The given failed scenario path " + reportPath + " doesn't exist");
        this.reportPath = reportPath;
    }

    public void buildNewScenario(final Path newScenarioPath) {
        final Scenario newScenario = createNewScenario();
        for (Test test: getFailedTests()) {
            newScenario.addTest(test);
        }
        newScenario.save(newScenarioPath);
    }

    abstract protected Scenario createNewScenario();
    abstract protected List<Test> getFailedTests();
}
