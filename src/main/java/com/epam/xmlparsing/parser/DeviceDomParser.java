package com.epam.xmlparsing.parser;

import com.epam.xmlparsing.entity.Device;
import com.epam.xmlparsing.entity.Laptop;
import com.epam.xmlparsing.entity.Port;
import com.epam.xmlparsing.entity.Smartphone;
import org.apache.log4j.Level;
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
import java.util.ArrayList;
import java.util.List;

public class DeviceDomParser implements DeviceParser {
    private DocumentBuilder docBuilder;
    private Device currentDevice;
    List<Device> devices = new ArrayList<>();

    public static final Logger LOGGER = Logger.getLogger(DeviceDomParser.class);

    public DeviceDomParser() throws ParserException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            docBuilder = factory.newDocumentBuilder();
        }catch(ParserConfigurationException e){
            throw new ParserException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Device> parse(String filePath) throws ParserException {
        try{
            Document doc = docBuilder.parse(filePath);
            Element root = doc.getDocumentElement();
            createSpecifiedDevice(root, DeviceXmlTag.LAPTOP.getValue());
            LOGGER.log(Level.INFO, "Dom parsing: Laptops have been created and added to list.");
            createSpecifiedDevice(root, DeviceXmlTag.SMARTPHONE.getValue());
            LOGGER.log(Level.INFO, "Dom parsing: Smartphones have been created and added to list.");
        }catch (IOException | SAXException e){
            throw new ParserException(e.getMessage(), e.getCause());
        }
        return devices;
    }

    private List<Device> createSpecifiedDevice(Element root, String tagName){
        NodeList deviceList = root.getElementsByTagName(tagName);

        for(int i = 0; i < deviceList.getLength(); i++){
            Element deviceElement = (Element) deviceList.item(i);
            buildGem(deviceElement, tagName);
            devices.add(currentDevice);
        }
        return devices;
    }

    private void buildGem(Element deviceElement, String tagName){
        String laptop = DeviceXmlTag.LAPTOP.getValue();
        currentDevice = tagName.equals(laptop) ? new Laptop() : new Smartphone();
        String id = DeviceXmlTag.ID.getValue();
        String idAttribute = deviceElement.getAttribute(id);
        currentDevice.setId(idAttribute);
        String producer = DeviceXmlTag.PRODUCER.getValue();
        String producerAttribute = deviceElement.getAttribute(producer);
        if(!producerAttribute.isEmpty()){
            currentDevice.setProducer(producerAttribute);
        }
        String name = DeviceXmlTag.NAME.getValue();
        currentDevice.setName(getElementContent(deviceElement, name));
        String portString = DeviceXmlTag.PORT.getValue();
        Port port = Port.valueOf(getElementContent(deviceElement, portString));
        currentDevice.setPort(port);
        String sensory = DeviceXmlTag.ISSENSORY.getValue();
        Boolean isSensory = Boolean.parseBoolean(getElementContent(deviceElement, sensory));
        currentDevice.getParameters().setSensory(isSensory);
        String batteryVolumeString = DeviceXmlTag.BATTERYVOLUME.getValue();
        Integer batteryVolume = Integer.parseInt(getElementContent(deviceElement,batteryVolumeString));
        currentDevice.getParameters().setBatteryVolume(batteryVolume);
        if(tagName.equals(laptop)){
            String graphicsCard = DeviceXmlTag.GRAPHICSCARD.getValue();
            ((Laptop)currentDevice).setGraphicsCard(getElementContent(deviceElement, graphicsCard));
        }else{
            String screenDiameterString = DeviceXmlTag.SCREENDIAMETER.getValue();
            Double screenDiameter = Double.parseDouble(getElementContent(deviceElement,screenDiameterString));
            ((Smartphone)currentDevice).setScreenDiameter(screenDiameter);
        }
    }

    private String getElementContent(Element element, String elementName){
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
