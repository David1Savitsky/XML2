package com.epam.xmlparsing.parser;

import com.epam.xmlparsing.entity.Device;
import com.epam.xmlparsing.handler.DeviceHandler;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class DeviceSaxParser implements DeviceParser {
    private final DeviceHandler handler = new DeviceHandler();
    public static final Logger LOGGER = Logger.getLogger(DeviceSaxParser.class);
    @Override
    public List<Device> parse(String filePath) throws ParserException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(handler);
            reader.parse(filePath);
            LOGGER.log(Level.INFO, "Sax parsing: Devices have been added to list");
        }catch(IOException | SAXException | ParserConfigurationException e) {
            throw new ParserException("Sax parser exception:", e);

        }
        return handler.getDevices();
    }
}
