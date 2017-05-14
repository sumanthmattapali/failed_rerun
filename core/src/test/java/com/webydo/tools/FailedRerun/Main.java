package com.webydo.tools.FailedRerun;

import com.webydo.tools.FailedRerun.jsystem.JSystemScenario;
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
            RepeatScenarioBuilder builder = new JSystemScenarioBuilder(new JSystemScenario(), "JSystemScenario", "path");
            builder.buildNewScenario("");
            builder.save();
        }
        catch (InvalidParameterException e) {
            logger.error("Can't build a new scenario", e);
        }
    }
}
