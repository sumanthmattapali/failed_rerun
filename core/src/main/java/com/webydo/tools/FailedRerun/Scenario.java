package com.webydo.tools.FailedRerun;

import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

/**
 * Scenario instance
 */
public abstract class Scenario {
    final protected List<Test> tests = new LinkedList<>();

    protected Scenario(final Path failedScenarioPath) {
        if(failedScenarioPath == null)
            throw new InvalidParameterException("The given failed scenario path is NULL or EMPTY");
        if (!failedScenarioPath.toFile().exists())
            throw new InvalidParameterException("The given failed scenario path " + failedScenarioPath + " doesn't exist");
    }

    public void addTest(final Test test) { tests.add(test); }
    abstract public void save(Path builtScenarioPath);
    public List<Test> getTests() { return tests; }
}
