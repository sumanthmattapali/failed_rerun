package com.webydo.tools.FailedRerun;

/**
 * Scenario instance
 */
public abstract class Scenario {
    protected String name;

    public String getName() { return name; }
    abstract public void addTest(final Test test);
}
