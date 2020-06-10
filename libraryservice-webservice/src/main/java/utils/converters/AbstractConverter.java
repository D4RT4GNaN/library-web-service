package utils.converters;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Abstract class to provide one date converter.
 * */
public abstract class AbstractConverter {

    /**
     * Converts a date in {@link Date} format to a date in {@link XMLGregorianCalendar} format.
     *
     * @param date - The date in {@link Date} format
     *
     * @return The date in {@link XMLGregorianCalendar} format
     * */
    protected static XMLGregorianCalendar toXMLGregorianCalendar(Date date) throws DatatypeConfigurationException {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
    }

}
