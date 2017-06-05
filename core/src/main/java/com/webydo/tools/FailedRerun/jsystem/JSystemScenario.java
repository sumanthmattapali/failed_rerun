package com.webydo.tools.FailedRerun.jsystem;

import com.webydo.tools.FailedRerun.Scenario;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidParameterException;

/**
 * JSystem scenario
 */
public class JSystemScenario extends Scenario {
    private static final Logger logger = LogManager.getLogger(JSystemScenario.class);
    private final Document newScenarioXML;

    public JSystemScenario(final Path failedScenarioPath) throws InvalidParameterException {
        super(failedScenarioPath);
        try {
            final DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            final Document scenarioXML = dBuilder.parse(failedScenarioPath.toFile());
            newScenarioXML = buildNewScenario(scenarioXML);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new InvalidParameterException("Can't initialize the XML in the file " + failedScenarioPath + ": " + e.toString());
        }
    }

    private Document buildNewScenario(final Document scenarioXML) throws ParserConfigurationException {
        final Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        doc.appendChild(doc.createElement("project"));
        return doc;
    }

    public String toString() {
        return newScenarioXML.toString();
    }

    @Override
    public void save(Path builtScenarioPath) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(newScenarioXML);
            StreamResult result = new StreamResult(new File(builtScenarioPath.toString()));

            transformer.transform(source, result);
        } catch (TransformerException e) {
            logger.error("Can't save the new created scenario");
        }
    }
}
