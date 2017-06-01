package com.webydo.tools.FailedRerun;

import com.webydo.tools.FailedRerun.jsystem.JSystemScenarioBuilder;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;

/**
 * Main class for running the program
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        BasicConfigurator.configure();
        try {
            Path curDir = new File(".").getAbsoluteFile().toPath();
            final RepeatScenarioBuilder builder = new JSystemScenarioBuilder(Paths.get(curDir + "/src/test/scenarious/jsystem/failed.xml"));
            assert(builder.getFailedTests().size() != 0): "The number of failed tests should be > 0";
            builder.buildNewScenario(Paths.get(curDir + "/src/test/scenarious/jsystem/new.xml"));

        }
        catch (InvalidParameterException e) {
            logger.error("Can't build a new scenario", e);
        }
    }
}
