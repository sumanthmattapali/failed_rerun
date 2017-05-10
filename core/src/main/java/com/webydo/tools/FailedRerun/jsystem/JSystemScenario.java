package com.webydo.tools.FailedRerun.jsystem;

import com.webydo.tools.FailedRerun.Scenario;
import com.webydo.tools.FailedRerun.Test;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * JSystem scenario
 */
public class JSystemScenario extends Scenario {
    private static final Logger logger = LogManager.getLogger(JSystemScenario.class);

    @Override
    public void addTest(Test test) {
        logger.info("Add the test " + test.toString() + " to the JSystem scenario " + name);
    }
}
