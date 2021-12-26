package com.epam.xmlparsing.parser;

import com.epam.xmlparsing.entity.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeviceDomParserTest {
    private static final String VALID_FILE_PATH = "src/test/resources/test.xml";
    private static final String INVALID_FILE_PATH = "invalidPath";

    @Test
    public void testParseShouldParseFileWhenIsValid() throws ParserException{
        //given
        Parameters laptopParameters = new Parameters(true, 1200);
        Laptop laptop = new Laptop("1", null, "Lenovo", Port.COM, laptopParameters, "nvidia");
        Parameters smartphoneParameters = new Parameters(true, 5000);
        Smartphone smartphone = new Smartphone("2", "China", "Xiaomi", Port.USB, smartphoneParameters, 5.6);
        DeviceDomParser domParser = new DeviceDomParser();
        List<Device> expected = Arrays.asList(laptop, smartphone);

        //when
        List<Device> actual = domParser.parse(VALID_FILE_PATH);

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ParserException.class)
    public void testParseShouldReturnExceptionWhenFileISInvalid() throws ParserException{
        //given
        DeviceSaxParser saxParser = new DeviceSaxParser();

        //when
        List<Device> actual = saxParser.parse(INVALID_FILE_PATH);
    }
}
