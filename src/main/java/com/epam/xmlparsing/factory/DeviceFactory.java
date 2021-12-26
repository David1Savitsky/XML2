package com.epam.xmlparsing.factory;

import com.epam.xmlparsing.parser.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class DeviceFactory {
    public static final Logger LOGGER = Logger.getLogger(DeviceFactory.class);
    public static DeviceParser createDeviceBuilder(String typeParser) throws ParserException {
        String upperCase = typeParser.toUpperCase();
        TypeParser type = TypeParser.valueOf(upperCase);
        switch (type){
            case SAX:
                LOGGER.log(Level.INFO, "Sax parser has been created.");
                return new DeviceSaxParser();
            case DOM:
                LOGGER.log(Level.INFO, "Dom parser has been created.");
                return new DeviceDomParser();
            case JAXB:
                LOGGER.log(Level.INFO, "Jaxb parser has been created.");
                return new DeviceJaxbParser();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
