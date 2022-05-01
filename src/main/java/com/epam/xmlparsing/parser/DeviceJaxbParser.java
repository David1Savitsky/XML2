package com.epam.xmlparsing.parser;

import com.epam.xmlparsing.entity.Device;
import com.epam.xmlparsing.entity.Devices;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class DeviceJaxbParser implements DeviceParser {
    public static final Logger LOGGER = Logger.getLogger(DeviceJaxbParser.class);

    @Override
    public List<Device> parse(String filePath) throws ParserException {
        try{
            List<Device> devicesList;
            JAXBContext jaxbContext = JAXBContext.newInstance(Devices.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Devices devices = (Devices) unmarshaller.unmarshal(new File(filePath));
            devicesList = devices.getDevicesList();
            LOGGER.log(Level.INFO, "Jaxb parsing: Devices have been added to list");
            return devicesList;
        }catch(JAXBException | IllegalArgumentException e){
            throw new ParserException("Jaxb parsing exception cause:", e);
        }
    }
}
