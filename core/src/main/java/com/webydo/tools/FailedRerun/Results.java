package com.webydo.tools.FailedRerun;

import java.nio.file.Path;
import java.util.List;

/**
 * Results of tests running
 */
public abstract class Results {
    protected final Path resultsPath;

    protected Results(Path resultsPath) { this.resultsPath = resultsPath; }
    abstract public List<Test> getFailedTests();
}
