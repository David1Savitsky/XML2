package com.epam.xmlparsing.parser;

import com.epam.xmlparsing.entity.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DeviceJaxbParserTest {
    private static final String VALID_FILE_PATH = "src/test/resources/test.xml";
    private static final String INVALID_FILE_PATH = "invalidPath";

    @Test
    public void testParseShouldParseFileWhenIsValid() throws ParserException{
        //given
        Parameters laptopParameters = new Parameters(true, 1200);
        Laptop laptop = new Laptop("1", null, "Lenovo", Port.COM, laptopParameters, "nvidia");
        Parameters smartphoneParameters = new Parameters(true, 5000);
        Smartphone smartphone = new Smartphone("2", "China", "Xiaomi", Port.USB, smartphoneParameters, 5.6);
        DeviceJaxbParser jaxbParser = new DeviceJaxbParser();
        List<Device> expected = Arrays.asList(laptop, smartphone);

        //when
        List<Device> actual = jaxbParser.parse(VALID_FILE_PATH);

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ParserException.class)
    public void testParseShouldReturnExceptionWhenFileISInvalid() throws ParserException{
        //given
        DeviceJaxbParser jaxbParser = new DeviceJaxbParser();

        //when
        List<Device> actual = jaxbParser.parse(INVALID_FILE_PATH);
    }
}
