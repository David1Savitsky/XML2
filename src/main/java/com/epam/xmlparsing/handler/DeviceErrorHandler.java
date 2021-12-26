package com.epam.xmlparsing.handler;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class DeviceErrorHandler extends DefaultHandler {
    private boolean isError = false;
    private static  final Logger LOGGER = Logger.getLogger(DeviceErrorHandler.class);

    public void warning(SAXParseException e){
        LOGGER.log(Level.WARN, String.format("Warning: [%s] - %s", getLineAddress(e), e.getMessage()));
        isError = true;
    }

    public void error(SAXParseException e){
        LOGGER.log(Level.ERROR, String.format("Warning: [%s] - %s", getLineAddress(e), e.getMessage()));
        isError = true;
    }

    public void fatalError(SAXParseException e){
        LOGGER.log(Level.FATAL, String.format("Warning: [%s] - %s", getLineAddress(e), e.getMessage()));
        isError = true;
    }

    public String getLineAddress(SAXParseException e){
        isError = true;
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }

    public boolean isErrorHappened(){
        return isError;
    }
}
