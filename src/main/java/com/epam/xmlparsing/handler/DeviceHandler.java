package com.epam.xmlparsing.handler;

import com.epam.xmlparsing.entity.*;
import com.epam.xmlparsing.parser.DeviceXmlTag;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class DeviceHandler extends DefaultHandler {
    private final List<Device> devices = new ArrayList<>();
    private Device currentDevice;
    private DeviceXmlTag currentTag;
    private final EnumSet<DeviceXmlTag> tagsWithText = EnumSet.range(DeviceXmlTag.ORIGIN, DeviceXmlTag.SCREENDIAMETER);

    public List<Device> getDevices() {
        return devices;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String laptopDeviceTag = DeviceXmlTag.LAPTOP.getValue();
        String smartphoneDeviceTag = DeviceXmlTag.SMARTPHONE.getValue();
        if (laptopDeviceTag.equals(qName) || smartphoneDeviceTag.equals(qName)){
            currentDevice = laptopDeviceTag.equals(qName) ? new Laptop() : new Smartphone();
            defineAttributes(attributes);
        }
        else{
            DeviceXmlTag current = DeviceXmlTag.valueOfXmlTag(qName);
            if(tagsWithText.contains(current)){
                currentTag = current;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String laptopDeviceTag = DeviceXmlTag.LAPTOP.getValue();
        String smartphoneDeviceTag = DeviceXmlTag.SMARTPHONE.getValue();
        if (laptopDeviceTag.equals(qName) || smartphoneDeviceTag.equals(qName)){
            devices.add(currentDevice);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String information = new String(ch, start, length).trim();
        Laptop laptop;
        Smartphone smartphone;
        if (currentTag != null){
            switch (currentTag){
                case NAME:
                    currentDevice.setName(information);
                    break;
                case PORT:
                    Port port = Port.valueOf(information);
                    currentDevice.setPort(port);
                    break;
                case ISSENSORY:
                    Boolean isSensory = Boolean.parseBoolean(information);
                    Parameters parameters = currentDevice.getParameters();
                    parameters.setSensory(isSensory);
                    break;
                case BATTERYVOLUME:
                    Integer batteryVolume = Integer.parseInt(information);
                    parameters = currentDevice.getParameters();
                    parameters.setBatteryVolume(batteryVolume);
                    break;
                case GRAPHICSCARD:
                    laptop = (Laptop) currentDevice;
                    laptop.setGraphicsCard(information);
                    currentDevice = laptop;
                    break;
                case SCREENDIAMETER:
                    smartphone = (Smartphone) currentDevice;
                    Double screenDiameter = Double.parseDouble(information);
                    smartphone.setScreenDiameter(screenDiameter);
                    currentDevice = smartphone;
                    break;
            }
        }
        currentTag = null;
    }

    private void defineAttributes(Attributes attributes){
        String idTag = attributes.getValue(DeviceXmlTag.ID.getValue());
        currentDevice.setId(idTag);

        String producer = attributes.getValue(DeviceXmlTag.PRODUCER.getValue());
        if (producer != null){
            currentDevice.setProducer(producer);
        }
    }
}
