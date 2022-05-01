package com.epam.xmlparsing.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "devices", namespace = "http://www.example.com/devices")
public class Devices {
    @XmlElements({
            @XmlElement(name = "laptop", namespace = "http://www.example.com/devices", type = Laptop.class),
            @XmlElement(name = "smartphone", namespace = "http://www.example.com/devices", type = Smartphone.class)
    })
    private List<Device> devicesList = new ArrayList<>();

    public Devices(){
    }

    public List<Device> getDevicesList() {
        return devicesList;
    }


}
