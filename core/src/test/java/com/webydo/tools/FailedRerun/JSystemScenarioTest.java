package com.webydo.tools.FailedRerun;

import com.webydo.tools.FailedRerun.jsystem.JSystemScenario;
import org.junit.Assert;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by daniel on 05/06/17.
 */
public class JSystemScenarioTest {
    Path curDir = new File(".").getAbsoluteFile().toPath();

    @org.junit.Test
    public void testScenarioBuild() {
        JSystemScenario scenario = new JSystemScenario(Paths.get(curDir + "/src/test/scenarious/jsystem/failed_scenario.xml"));
        scenario.save(Paths.get(curDir + "/src/test/scenarious/jsystem/new_scenario.xml"));
        Assert.assertEquals("Created scenario", scenario.toString(), "");
    }
}
