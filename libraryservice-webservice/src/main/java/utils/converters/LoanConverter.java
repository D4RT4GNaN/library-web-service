package utils.converters;

import generated.libraryservice.Book;
import generated.libraryservice.Library;
import generated.libraryservice.Usager;
import org.openclassroom.projet.model.database.service.Loan;
import org.openclassroom.projet.model.database.service.LoanId;
import org.openclassroom.projet.model.database.usager.UsagerDto;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class LoanConverter {

    public static generated.libraryservice.Loan fromDatabase(Loan loan) {
        generated.libraryservice.Loan generatedLoan = new generated.libraryservice.Loan();

        try {
            Book generatedBook = BookConverter.fromDatabase(loan.getLoanId().getBook());
            Usager generatedUsager = UsagerConverter.fromDatabase(loan.getLoanId().getUsager());
            Library generatedLibrary = LibraryConverter.fromDatabase(loan.getLoanId().getLibrary());

            generatedLoan.setBorrowingDate(toXMLGregorianCalendar(loan.getLoanId().getBorrowingDate()));
            generatedLoan.setBook(generatedBook);
            generatedLoan.setUsager(generatedUsager);
            generatedLoan.setLibrary(generatedLibrary);
            generatedLoan.setExpiryDate(toXMLGregorianCalendar(loan.getExpiryDate()));
            generatedLoan.setExtended(loan.getExtended());
            generatedLoan.setStatus(loan.getStatus());
            generatedLoan.setQuantity(loan.getQuantity());
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        return generatedLoan;
    }

    public static List<generated.libraryservice.Loan> fromDatabase(List<Loan> loans) {
        List<generated.libraryservice.Loan> generatedLoans = new ArrayList<>();

        for (Loan loan : loans) {
            generatedLoans.add(fromDatabase(loan));
        }

        return generatedLoans;
    }

    public static Loan fromClient(generated.libraryservice.Loan generatedLoan) {
        LoanId loanId = new LoanId();

        if (generatedLoan.getBorrowingDate() != null) {
            loanId.setBorrowingDate(generatedLoan.getBorrowingDate().toGregorianCalendar().getTime());
        } else {
            loanId.setBorrowingDate(new Date());
        }

        loanId.setBook(BookConverter.fromClient(generatedLoan.getBook()));

        UsagerDto usagerDto = UsagerConverter.fromClient(generatedLoan.getUsager());
        loanId.setUsager(new org.openclassroom.projet.model.database.usager.Usager(usagerDto));

        return new Loan(loanId);
    }

    private static XMLGregorianCalendar toXMLGregorianCalendar(Date date) throws DatatypeConfigurationException {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
    }

}
