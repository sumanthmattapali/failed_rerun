package com.webydo.tools.FailedRerun;

/**
 * Test instance
 */
public abstract class Test {
    final private String failedMsg;

    protected Test(String failedMsg) { this.failedMsg = failedMsg; }
    public String getFailMsg() { return failedMsg; }
}
