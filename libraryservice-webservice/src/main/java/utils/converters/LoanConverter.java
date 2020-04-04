package utils.converters;

import org.openclassroom.projet.model.database.service.Loan;
import org.openclassroom.projet.model.database.service.LoanId;

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
            generatedLoan.setBorrowingDate(toXMLGregorianCalendar(loan.getLoanId().getBorrowingDate()));
            generatedLoan.setBookReference(loan.getLoanId().getReferenceBook());
            generatedLoan.setUserId(loan.getLoanId().getUsagerId());
            generatedLoan.setExpiryDate(toXMLGregorianCalendar(loan.getExpiryDate()));
            generatedLoan.setExtended(loan.getExtended());
            generatedLoan.setStatus(loan.getStatus());
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

        loanId.setReferenceBook(generatedLoan.getBookReference());
        loanId.setUsagerId(generatedLoan.getUserId());

        return new Loan(loanId);
    }

    private static XMLGregorianCalendar toXMLGregorianCalendar(Date date) throws DatatypeConfigurationException {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
    }

}
