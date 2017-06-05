package com.webydo.tools.FailedRerun.junit;

import com.webydo.tools.FailedRerun.Results;
import com.webydo.tools.FailedRerun.Test;
import com.webydo.tools.FailedRerun.jsystem.JSystemTest;
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
 * Results of JUnit tests running
 */
public class ResultsJunit extends Results {
    private static final Logger logger = LogManager.getLogger(ResultsJunit.class);
    private final Document reportXml;
    private String failedScenarioName;

    public ResultsJunit(Path resultsPath) {
        super(resultsPath);
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            reportXml = dBuilder.parse(resultsPath.toFile());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new InvalidParameterException("Can't initialize the XML in the file " + resultsPath + ": " + e.toString());
        }
    }

    @Override
    public List<Test> getFailedTests() {
        LinkedList<Test> tests = new LinkedList<>();

        reportXml.getDocumentElement().normalize();
        final Element root = reportXml.getDocumentElement();
        failedScenarioName = root.getAttribute("name");

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

    private JSystemTest buildTest(final Element node, String ...failTagNames) {
        Node child;
        String msg, className;
        for (String failedTestTagName: failTagNames) {
            child = node.getElementsByTagName(failedTestTagName).item(0);
            if (child != null) {
                msg       = child.getAttributes().getNamedItem("message").getNodeValue().toString();
                className = node.getAttributes().getNamedItem("classname").getNodeValue().toString();
                return new JSystemTest(className, msg);
            }
        }
        return null;
    }

    private boolean hasFailedTests(final Element root) {
        return (getAttributeInt(root, "errors") + getAttributeInt(root, "failures")) > 0;
    }

    private Integer getAttributeInt(final Element node, String attrName) {
        String errsNumStr = node.getAttribute(attrName);
        return (errsNumStr != null && !errsNumStr.isEmpty()) ? new Integer(errsNumStr): new Integer(0);
    }
}
