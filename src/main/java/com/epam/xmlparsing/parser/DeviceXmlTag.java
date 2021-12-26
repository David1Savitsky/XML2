package com.epam.xmlparsing.parser;


public enum DeviceXmlTag {
    ID("id"),
    PRODUCER("producer"),
    PARAMETERS("parameters"),
    DEVICES("devices"),
    LAPTOP("laptop"),
    SMARTPHONE("smartphone"),

    ORIGIN("origin"),
    NAME("name"),
    PORT("port"),
    ISSENSORY("isSensory"),
    BATTERYVOLUME("batteryVolume"),
    GRAPHICSCARD("graphicsCard"),
    SCREENDIAMETER("screenDiameter");

    private String value;

    DeviceXmlTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DeviceXmlTag valueOfXmlTag(String tag) {
        tag = tag.toUpperCase();
        return DeviceXmlTag.valueOf(tag);
    }
}
