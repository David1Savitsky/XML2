package com.epam.xmlparsing.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "port")
@XmlEnum
public enum Port {
    @XmlEnumValue("COM")
    COM,
    @XmlEnumValue("USB")
    USB,
    @XmlEnumValue("LPT")
    LPT
}
