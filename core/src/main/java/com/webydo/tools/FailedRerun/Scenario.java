package com.webydo.tools.FailedRerun;

import java.util.LinkedList;
import java.util.List;

/**
 * Scenario instance
 */
public abstract class Scenario {
    final protected String name;
    final private List<Test> tests;

    protected Scenario(String name) {
        this.name = name;
        tests = new LinkedList<>();
    }

    public String getName() { return name; }
    public void addTest(final Test test) { tests.add(test); }
    abstract public void save(String builtScenarioPath);
    public List<Test> getTests() { return tests; }
}
