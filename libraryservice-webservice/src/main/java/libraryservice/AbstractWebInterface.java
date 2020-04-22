package libraryservice;

import org.openclassroom.projet.business.services.contract.ServiceFactory;

import javax.inject.Inject;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

public abstract class AbstractWebInterface {

    @Inject
    private ServiceFactory serviceFactory;

    protected ServiceFactory getServiceFactory() {
        return serviceFactory;
    }

    protected Date XMLGregorianCalendarToDate(XMLGregorianCalendar calendar) {
        return calendar.toGregorianCalendar().getTime();
    }

}
