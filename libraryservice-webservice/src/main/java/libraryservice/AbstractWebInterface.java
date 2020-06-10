package libraryservice;

import org.openclassroom.projet.business.services.contract.ServiceFactory;

import javax.inject.Inject;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

/**
 * Abstract class to provide the {@link ServiceFactory} and one date converter.
 * */
public abstract class AbstractWebInterface {

    @Inject
    private ServiceFactory serviceFactory;

    /**
     * Spring control inversion for business module.
     *
     * @return The {@link ServiceFactory}.
     * */
    protected ServiceFactory getServiceFactory() {
        return serviceFactory;
    }

    /**
     * Converts a date in {@link XMLGregorianCalendar} format to a date in {@link Date} format.
     *
     * @param calendar - The date in {@link XMLGregorianCalendar} format
     *
     * @return The date in {@link Date} format
     * */
    protected Date XMLGregorianCalendarToDate(XMLGregorianCalendar calendar) {
        return calendar.toGregorianCalendar().getTime();
    }

}
