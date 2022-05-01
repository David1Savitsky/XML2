package com.epam.xmlparsing.parser;

import com.epam.xmlparsing.entity.Device;
import com.epam.xmlparsing.factory.DeviceFactory;
import com.epam.xmlparsing.validator.XmlValidator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Director {
    private final DeviceParser parser;
    private final XmlValidator validator;
    private static final Logger LOGGER = Logger.getLogger(Director.class);

    public Director(DeviceParser parser, XmlValidator validator) {
        this.parser = parser;
        this.validator = validator;
    }

    List<Device> parse(String xmlPath, String xsdPath, String typeParser){
        List<Device> devices = new ArrayList<>();
        try{
            if (validator.isValid(xmlPath, xsdPath)){
                DeviceParser parser = DeviceFactory.createDeviceBuilder(typeParser);
                devices = parser.parse(xmlPath);
            }
        }catch(ParserException e){
            LOGGER.log(Level.ERROR, "Creating devices has failed. Cause: ", e);
        }
        return devices;
    }
}
