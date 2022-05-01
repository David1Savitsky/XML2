package com.epam.xmlparsing.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Smartphone", propOrder = {"screenDiameter"})
public class Smartphone extends Device{
    @XmlElement(namespace = "http://www.example.com/devices", required = true)
    private double screenDiameter;

    public Smartphone() {
    }

    public Smartphone(String id, String producer, String name, Port port, Parameters parameters, double screenDiameter) {
        super(id, producer, name, port, parameters);
        this.screenDiameter = screenDiameter;
    }

    public void setScreenDiameter(double screenDiameter) {
        this.screenDiameter = screenDiameter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }
        Smartphone smartPhone = (Smartphone) o;
        return screenDiameter == smartPhone.screenDiameter;
    }

    @Override
    public int hashCode() {
        int prime = 17;
        prime = 31 * prime + super.hashCode();
        prime = 31 * prime + (int)screenDiameter;
        return prime;
    }

    @Override
    public String toString() {
        return "SmartPhone{" +
                "screenDiameter=" + screenDiameter +
                '}';
    }
}
