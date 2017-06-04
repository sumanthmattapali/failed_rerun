package com.webydo.tools.FailedRerun;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by daniel on 04/06/17.
 */
public abstract class Results {
    protected Path resultsPath;

    public Results(Path resultsPath) { this.resultsPath = resultsPath; }
    abstract protected List<Test> getFailedTests();
}
