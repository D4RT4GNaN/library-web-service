package libraryservice;

import org.openclassroom.projet.consumer.DaoFactory;

import javax.inject.Inject;

public abstract class AbstractService {

    @Inject
    private DaoFactory daoFactory;

    protected DaoFactory getDaoFactory() {
        return daoFactory;
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
