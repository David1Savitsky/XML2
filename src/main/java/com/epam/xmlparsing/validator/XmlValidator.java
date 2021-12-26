package com.epam.xmlparsing.validator;

import com.epam.xmlparsing.handler.DeviceErrorHandler;
import com.epam.xmlparsing.parser.ParserException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {

    private static final Logger LOGGER = Logger.getLogger(XmlValidator.class);

    public boolean isValid(String xmlPath, String xsdPath) throws ParserException{

        if (xmlPath == null || xmlPath.isEmpty() || xsdPath == null || xsdPath.isEmpty()){
            throw new ParserException("XML or XSD is null or Empty");
        }

        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        boolean result = true;

        try{
            Schema schema = factory.newSchema(new File(xsdPath));
            Source source = new StreamSource(xmlPath);

            Validator validator = schema.newValidator();
            DeviceErrorHandler errorHandler = new DeviceErrorHandler();
            validator.setErrorHandler(errorHandler);

            validator.validate(source);
            if (errorHandler.isErrorHappened()){
                result = false;
            }
        } catch (SAXException | IOException e) {
            LOGGER.log(Level.ERROR, String.format("File %s is not valid.", xmlPath), e);
            result = false;
        }

        return result;
    }
}
