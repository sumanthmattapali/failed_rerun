package com.webydo.tools.FailedRerun.jsystem;

import com.webydo.tools.FailedRerun.Scenario;
import com.webydo.tools.FailedRerun.Test;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.nio.file.Path;

/**
 * JSystem scenario
 */
public class JSystemScenario extends Scenario {
    private static final Logger logger = LogManager.getLogger(JSystemScenario.class);

    public JSystemScenario(String name) {
        super(name);
    }

    @Override
    public void addTest(Test test) {
        logger.info("Add the test '" + test.getFailMsg() + "' to the JSystem scenario " + name);
        tests.add(test);
    }

    @Override
    public void save(Path builtScenarioPath) {
        logger.info("Saving scenario '" + name + "'");
        tests.forEach(test -> System.out.println("Failed test: " + test.getFailMsg()));
    }
}
