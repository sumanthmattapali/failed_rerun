package com.webydo.tools.FailedRerun.jsystem;

import com.webydo.tools.FailedRerun.Test;

/**
 * A JSystem test instance
 */
public class JSystemTest extends Test {
    private final String className;

    public JSystemTest(String className, String failedMsg) {
        super(failedMsg);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
