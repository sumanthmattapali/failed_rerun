package com.webydo.tools.FailedRerun;

import com.webydo.tools.FailedRerun.jsystem.JSystemTest;
import com.webydo.tools.FailedRerun.junit.ResultsJunit;
import org.junit.Assert;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Tesing Jsystem test
 */
public class JSystemTestTest {
    Path curDir = new File(".").getAbsoluteFile().toPath();

    @org.junit.Test
    public void testMessages() {
        ResultsJunit results = new ResultsJunit(Paths.get(curDir + "/src/test/scenarious/jsystem/failed_results.xml"));
        String msg = results.getFailedTests().get(0).getFailMsg();
        Assert.assertEquals("Checking the message of the first found failed test", msg, "Fail report was submitted");
        msg = results.getFailedTests().get(1).getFailMsg();
        Assert.assertEquals("Checking the message of the second found failed test", msg, "Fail report was submitted");
        msg = results.getFailedTests().get(2).getFailMsg();
        Assert.assertEquals("Checking the message of the third found failed test", msg, "Timed out after 45 seconds waiting for element to be clickable: By.cssSelector: .remember_me li span Build info: version: '2.53.1', revision: 'a36b8b1cd5757287168e54b817830adce9b0158d', time: '2016-06-30 19:26:09' System info: host: 'QAauto4-PC', ip: '192.168.2.135', os.name: 'Windows 7', os.arch: 'amd64', os.version: '6.1', java.version: '1.8.0_102' Driver info: org.openqa.selenium.chrome.ChromeDriver Capabilities [{applicationCacheEnabled=false, rotatable=false, mobileEmulationEnabled=false, networkConnectionEnabled=false, chrome={chromedriverVersion=2.24.417431 (9aea000394714d2fbb20850021f6204f2256b9cf), userDataDir=C:\\Users\\AppautoSvc\\AppData\\Local\\Google\\Chrome\\User Data\\Default}, takesHeapSnapshot=true, pageLoadStrategy=normal, databaseEnabled=false, handlesAlerts=true, hasTouchScreen=false, version=56.0.2924.87, platform=XP, browserConnectionEnabled=false, nativeEvents=true, acceptSslCerts=true, locationContextEnabled=true, webStorageEnabled=true, browserName=chrome, takesScreenshot=true, javascriptEnabled=true, cssSelectorsEnabled=true}] Session ID: 300501bd981a167d398d0cf24156d2ed");
    }

    @org.junit.Test
    public void testClassNames() {
        ResultsJunit results = new ResultsJunit(Paths.get(curDir + "/src/test/scenarious/jsystem/failed_results.xml"));
        String className = ((JSystemTest)results.getFailedTests().get(0)).getClassName();
        Assert.assertEquals("Checking the class name of the first found failed test", className, "com.webydo.automation.dashboard.systemtests.loginArea.LoginTest");
        className = ((JSystemTest)results.getFailedTests().get(1)).getClassName();
        Assert.assertEquals("Checking the message of the second found failed test", className, "com.webydo.automation.dashboard.systemtests.MyAccountTests");
        className = ((JSystemTest)results.getFailedTests().get(2)).getClassName();
        Assert.assertEquals("Checking the message of the third found failed test", className, "com.webydo.automation.dashboard.systemtests.dashboardSite.DomainAndEmailSettings");
    }

}
