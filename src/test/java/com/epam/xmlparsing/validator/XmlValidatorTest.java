package com.epam.xmlparsing.validator;

import com.epam.xmlparsing.parser.ParserException;
import org.junit.Assert;
import org.junit.Test;

public class XmlValidatorTest {

    public static final String VALID_XML_FILE = "src/test/resources/xml.xml";
    public static final String VALID_XSD_FILE = "src/test/resources/xsd.xsd";
    public static final String INVALID_XML_FILE = "src/test/resources/invalidXml.xml";
    public static final String EMPTY_PATH = "";

    @Test
    public void testIsValidShouldReturnTrueWhenFileIsValid() throws ParserException {
        //given
        XmlValidator validator = new XmlValidator();

        //when
        boolean actualFlag = validator.isValid(VALID_XML_FILE, VALID_XSD_FILE);

        //then
        Assert.assertTrue(actualFlag);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenFileIsInvalid() throws ParserException {
        //given
        XmlValidator validator = new XmlValidator();

        //when
        boolean actualFlag = validator.isValid(INVALID_XML_FILE, VALID_XSD_FILE);

        //then
        Assert.assertFalse(actualFlag);
    }

    @Test(expected = ParserException.class)
    public void testIsValidShouldReturnExceptionWhenFileIsNotExisted() throws ParserException {
        //given
        XmlValidator validator = new XmlValidator();

        //when
        boolean actualFlag = validator.isValid(EMPTY_PATH, VALID_XSD_FILE);

    }
}
