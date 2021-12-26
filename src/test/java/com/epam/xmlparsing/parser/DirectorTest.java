package com.epam.xmlparsing.parser;

import com.epam.xmlparsing.entity.*;
import com.epam.xmlparsing.factory.TypeParser;
import com.epam.xmlparsing.validator.XmlValidator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class DirectorTest {
    private static final String XML_PATH = "src/test/resources/xml.xml";
    private static final String XSD_PATH = "src/test/resources/xsd.xml";
    public static final String TYPE_PARSER = "SAX";
    public static List<Device> EXPECTED_DEVICES = Arrays.asList(
            new Laptop("1", null, "Lenovo", Port.COM,
                    new Parameters(true, 1200), "nvidia"),
            new Smartphone("2", null, "Samsung", Port.USB,
                    new Parameters(false, 560), 4.2),
            new Laptop("3", "USA", "Apple", Port.LPT,
                    new Parameters(true, 1300), "ATI"),
            new Smartphone("4", "China", "Xiaomi", Port.USB,
                    new Parameters(true, 5000), 5.6));

    @Test
    public void testParseShouldCreateWhenIsValid() throws ParserException{
        //given
        XmlValidator validator = Mockito.mock(XmlValidator.class);
        Mockito.when(validator.isValid(XML_PATH, XSD_PATH)).thenReturn(true);
        DeviceParser parser = Mockito.mock(DeviceParser.class);
        Mockito.when(parser.parse(XML_PATH)).thenReturn(EXPECTED_DEVICES);
        Director director = new Director(parser, validator);

        //when
        List<Device> actual = director.parse(XML_PATH, XSD_PATH, TYPE_PARSER);

        //then
        Assert.assertEquals(EXPECTED_DEVICES, actual);
    }
}
