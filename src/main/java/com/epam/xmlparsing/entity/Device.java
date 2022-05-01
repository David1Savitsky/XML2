package com.epam.xmlparsing.entity;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Device", propOrder = {
        "name",
        "port",
        "parameters"
})
@XmlSeeAlso({Laptop.class, Smartphone.class})
public abstract class Device {
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    private String id;
    @XmlAttribute(name = "producer", required = false)
    private String producer;

    @XmlElement(namespace = "http://www.example.com/devices", required = true)
    private String name;
    @XmlElement(namespace = "http://www.example.com/devices", required = true)
    private Port port;
    @XmlElement(namespace = "http://www.example.com/devices", required = true)
    private Parameters parameters = new Parameters();

    public Device() {
    }

    public Device(String id, String producer, String name, Port port, Parameters parameters) {
        this.id = id;
        this.producer = producer;
        this.name = name;
        this.port = port;
        this.parameters = parameters;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public Parameters getParameters() {
        return parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Device device = (Device) o;
        return id.equals(device.id)
                //&& producer.equals(device.producer)
                && name.equals(device.name)
                && port == device.port
                && parameters.equals(device.parameters);
    }

    @Override
    public int hashCode() {
        int prime = 17;
        prime = 31 * prime + id.hashCode();
        prime = 31 * prime + name.hashCode();
        prime = 31 * prime + port.hashCode();
        prime = 31 * prime + parameters.hashCode();
        return prime;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", producer='" + producer + '\'' +
                ", name='" + name + '\'' +
                ", port=" + port +
                ", parameters=" + parameters +
                '}';
    }
}
