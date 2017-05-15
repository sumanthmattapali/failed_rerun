package com.webydo.tools.FailedRerun;

import com.webydo.tools.FailedRerun.jsystem.JSystemScenarioBuilder;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.security.InvalidParameterException;

/**
 * Main class for running the program
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        BasicConfigurator.configure();
        try {
            RepeatScenarioBuilder builder = new JSystemScenarioBuilder("test/scenarious/jsystem/failed.xml");
            assert(builder.getFailedTests().size() != 0);
            for (Test test: builder.getFailedTests())
                test.getFailMsg();
            Scenario scenario = builder.createNewScenario("adsfa");
            assert(scenario.getTests().size() == builder.getFailedTests().size());
        }
        catch (InvalidParameterException e) {
            logger.error("Can't build a new scenario", e);
        }
    }
}
