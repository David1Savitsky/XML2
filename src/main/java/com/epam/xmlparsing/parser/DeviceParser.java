package com.epam.xmlparsing.parser;

import com.epam.xmlparsing.entity.Device;

import java.util.List;

public interface DeviceParser {
    List<Device> parse(String filePath) throws ParserException;
}
