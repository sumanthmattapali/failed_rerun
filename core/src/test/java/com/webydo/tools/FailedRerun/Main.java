package com.webydo.tools.FailedRerun;

import com.webydo.tools.FailedRerun.jsystem.JSystemScenario;
import com.webydo.tools.FailedRerun.jsystem.JSystemScenarioBuilder;

/**
 * Main class for running the program
 */
public class Main {
    public static void main(String[] args) {
        RepeatScenarioBuilder builder = new JSystemScenarioBuilder(new JSystemScenario(), "JSystemScenario", "path");
        builder.buildNewScenario();
        builder.save();
    }
}
