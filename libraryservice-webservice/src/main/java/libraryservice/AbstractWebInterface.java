package libraryservice;

import org.openclassroom.projet.business.services.contract.ServiceFactory;

import javax.inject.Inject;

public abstract class AbstractWebInterface {

    @Inject
    private ServiceFactory serviceFactory;

    protected ServiceFactory getServiceFactory() {
        return serviceFactory;
    }

}
