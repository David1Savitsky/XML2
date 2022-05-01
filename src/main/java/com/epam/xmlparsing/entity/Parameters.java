package com.epam.xmlparsing.entity;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Parameters", propOrder = {
        "isSensory",
        "batteryVolume"
})
public class Parameters {
    @XmlElement(namespace = "http://www.example.com/devices", required = true)
    private boolean isSensory;
    @XmlElement(namespace = "http://www.example.com/devices", required = true)
    private int batteryVolume;

    public Parameters() {
    }

    public Parameters(boolean isSensory, int batteryVolume) {
        this.isSensory = isSensory;
        this.batteryVolume = batteryVolume;
    }

    public boolean isSensory() {
        return isSensory;
    }

    public int getBatteryVolume() {
        return batteryVolume;
    }

    public void setSensory(boolean sensory) {
        isSensory = sensory;
    }

    public void setBatteryVolume(int batteryVolume) {
        this.batteryVolume = batteryVolume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Parameters parameters = (Parameters) o;
        return isSensory == parameters.isSensory && batteryVolume == parameters.batteryVolume;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isSensory, batteryVolume);
    }
}
