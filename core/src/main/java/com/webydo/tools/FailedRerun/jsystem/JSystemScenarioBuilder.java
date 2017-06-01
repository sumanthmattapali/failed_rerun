package com.webydo.tools.FailedRerun.jsystem;

import com.webydo.tools.FailedRerun.RepeatScenarioBuilder;
import com.webydo.tools.FailedRerun.Scenario;
import com.webydo.tools.FailedRerun.Test;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

/**
 * The builder of a JSystem scenario with failed tests
 */
public class JSystemScenarioBuilder extends RepeatScenarioBuilder {
    private static final Logger logger = LogManager.getLogger(JSystemScenarioBuilder.class);
    private final Document reportXml;
    private String failedScenarioName;

    public JSystemScenarioBuilder(Path reportPath) throws InvalidParameterException {
        super(reportPath);

        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            reportXml = dBuilder.parse(reportPath.toFile());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new InvalidParameterException("Can't initialize the XML in the file " + reportPath + ": " + e.toString());
        }
    }

    @Override
    protected Scenario createNewScenario(String newScenarioName) {
        return (newScenarioName == null || newScenarioName.isEmpty()) ? new JSystemScenario(failedScenarioName): new JSystemScenario(newScenarioName);
    }

    private Integer getAttributeInt(final Element node, String attrName) {
        String errsNumStr = node.getAttribute(attrName);
        return (errsNumStr != null && !errsNumStr.isEmpty()) ? new Integer(errsNumStr): new Integer(0);
    }

    private boolean hasFailedTests(final Element root) {
        return (getAttributeInt(root, "errors") + getAttributeInt(root, "failures")) > 0;
    }

    private JSystemTest buildTest(final Element node, String ...failTagNames) {
        Node child;
        String msg, className;
        for (String failedTestTagName: failTagNames) {
            child = node.getElementsByTagName(failedTestTagName).item(0);
            if (child != null) {
                msg       = child.getAttributes().getNamedItem("message").toString();
                className = node.getAttributes().getNamedItem("classname").toString();
                return new JSystemTest(className, msg);
            }
        }
        return null;
    }

    @Override
    protected List<Test> getFailedTests() {
        logger.info("Get the the failed test of the JSystem scenario " + reportPath);
        LinkedList<Test> tests = new LinkedList<>();

        reportXml.getDocumentElement().normalize();
        final Element root = reportXml.getDocumentElement();
        failedScenarioName = root.getAttribute("name");

        logger.info("Get failed tests of the scenario " + failedScenarioName);

        if (hasFailedTests(root)) {
            final NodeList nList = reportXml.getElementsByTagName("testsuite").item(0).getChildNodes();
            for (int ind = 0; ind < nList.getLength(); ind++) {
                final Node nNode = nList.item(ind);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    final JSystemTest failedTest = buildTest((Element) nNode, "error", "failure");
                    if(failedTest != null)
                        tests.add(failedTest);
                }
            }
        }
        return tests;
    }
}
