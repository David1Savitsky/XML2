package com.epam.xmlparsing.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Laptop", propOrder = {"graphicsCard"})
public class Laptop extends Device{
    @XmlElement(namespace = "http://www.example.com/devices", required = true)
    private String graphicsCard;

    public Laptop() {
    }

    public Laptop(String id, String producer, String name, Port port, Parameters parameters, String graphicsCard) {
        super(id, producer, name, port, parameters);
        this.graphicsCard = graphicsCard;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
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
        Laptop laptop = (Laptop) o;
        return graphicsCard.equals(laptop.graphicsCard);
    }

    @Override
    public int hashCode() {
        int prime = 17;
        prime = 31 * prime + super.hashCode();
        prime = 31 * prime + graphicsCard.hashCode();
        return prime;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "graphicsCard='" + graphicsCard + '\'' +
                '}';
    }
}
