package com.webydo.tools.FailedRerun.jsystem;

import com.webydo.tools.FailedRerun.RepeatScenarioBuilder;
import com.webydo.tools.FailedRerun.Results;
import com.webydo.tools.FailedRerun.Scenario;
import com.webydo.tools.FailedRerun.junit.ResultsJunit;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.nio.file.Path;
import java.security.InvalidParameterException;

/**
 * The builder of a JSystem scenario with failed tests
 */
public class JSystemScenarioBuilder extends RepeatScenarioBuilder {
    private static final Logger logger = LogManager.getLogger(JSystemScenarioBuilder.class);

    private static JSystemScenario scenario = null;
    private final ResultsJunit results;

    /**
     * Constructor
     * @param reportPath The path of tests report file
     * @throws InvalidParameterException If path isn't found or initialization from the file has failed
     */
    public JSystemScenarioBuilder(Path reportPath) throws InvalidParameterException {
        super(reportPath);
        results = new ResultsJunit(reportPath);
    }

    @Override
    protected Scenario createNewScenario(final Path failedScenarioPath) {
        if(scenario == null)
            scenario = new JSystemScenario(failedScenarioPath);
        return scenario;
    }

    @Override
    protected Results getResults() {
        return results;
    }
}
