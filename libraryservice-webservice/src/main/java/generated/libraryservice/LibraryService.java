
package generated.libraryservice;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "LibraryService", targetNamespace = "http://LibraryService/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface LibraryService {


    /**
     * 
     * @param bookReference
     * @return
     *     returns java.util.List<generated.libraryservice.Stock>
     */
    @WebMethod(action = "http://LibraryService/getBookAvailability")
    @WebResult(name = "bookAvailability", targetNamespace = "")
    @RequestWrapper(localName = "getBookAvailability", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.GetBookAvailability")
    @ResponseWrapper(localName = "getBookAvailabilityResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.GetBookAvailabilityResponse")
    public List<Stock> getBookAvailability(
        @WebParam(name = "bookReference", targetNamespace = "")
        String bookReference);

    /**
     * 
     * @param libraryId
     * @param usager
     * @param bookReference
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://LibraryService/addNewLoan")
    @WebResult(name = "status", targetNamespace = "")
    @RequestWrapper(localName = "addNewLoan", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.AddNewLoan")
    @ResponseWrapper(localName = "addNewLoanResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.AddNewLoanResponse")
    public String addNewLoan(
        @WebParam(name = "libraryId", targetNamespace = "")
        int libraryId,
        @WebParam(name = "bookReference", targetNamespace = "")
        String bookReference,
        @WebParam(name = "usager", targetNamespace = "")
        Usager usager);

    /**
     * 
     * @param loan
     * @return
     *     returns boolean
     */
    @WebMethod(action = "http://LibraryService/extendLoan")
    @WebResult(name = "extended", targetNamespace = "")
    @RequestWrapper(localName = "extendLoan", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ExtendLoan")
    @ResponseWrapper(localName = "extendLoanResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ExtendLoanResponse")
    public boolean extendLoan(
        @WebParam(name = "loan", targetNamespace = "")
        Loan loan);

    /**
     * 
     * @param libraryId
     * @param usager
     * @param borrowingDate
     * @param bookReference
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://LibraryService/returnBook")
    @WebResult(name = "status", targetNamespace = "")
    @RequestWrapper(localName = "returnBook", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ReturnBook")
    @ResponseWrapper(localName = "returnBookResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ReturnBookResponse")
    public String returnBook(
        @WebParam(name = "borrowingDate", targetNamespace = "")
        XMLGregorianCalendar borrowingDate,
        @WebParam(name = "libraryId", targetNamespace = "")
        int libraryId,
        @WebParam(name = "bookReference", targetNamespace = "")
        String bookReference,
        @WebParam(name = "usager", targetNamespace = "")
        Usager usager);

    /**
     * 
     * @param bookReference
     * @param userID
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://LibraryService/getStatusLoan")
    @WebResult(name = "status", targetNamespace = "")
    @RequestWrapper(localName = "getStatusLoan", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.GetStatusLoan")
    @ResponseWrapper(localName = "getStatusLoanResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.GetStatusLoanResponse")
    public String getStatusLoan(
        @WebParam(name = "bookReference", targetNamespace = "")
        String bookReference,
        @WebParam(name = "userID", targetNamespace = "")
        int userID);

    /**
     * 
     * @param keyword
     * @return
     *     returns java.util.List<generated.libraryservice.Book>
     */
    @WebMethod(action = "http://LibraryService/getBooksWithKeyword")
    @WebResult(name = "listBooks", targetNamespace = "")
    @RequestWrapper(localName = "getBooksWithKeyword", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.GetBooksWithKeyword")
    @ResponseWrapper(localName = "getBooksWithKeywordResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.GetBooksWithKeywordResponse")
    public List<Book> getBooksWithKeyword(
        @WebParam(name = "keyword", targetNamespace = "")
        String keyword);

    /**
     * 
     * @param generatedUsager
     * @return
     *     returns java.lang.String
     * @throws RegisterException
     */
    @WebMethod(action = "http://LibraryService/addUser")
    @WebResult(name = "status", targetNamespace = "")
    @RequestWrapper(localName = "addUser", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.AddUser")
    @ResponseWrapper(localName = "addUserResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.AddUserResponse")
    public String addUser(
        @WebParam(name = "generatedUsager", targetNamespace = "")
        Usager generatedUsager)
        throws RegisterException
    ;

    /**
     * 
     * @param identifier
     * @param password
     * @return
     *     returns generated.libraryservice.Usager
     * @throws LoginException
     */
    @WebMethod(action = "http://LibraryService/connectUser")
    @WebResult(name = "user", targetNamespace = "")
    @RequestWrapper(localName = "connectUser", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ConnectUser")
    @ResponseWrapper(localName = "connectUserResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ConnectUserResponse")
    public Usager connectUser(
        @WebParam(name = "identifier", targetNamespace = "")
        String identifier,
        @WebParam(name = "password", targetNamespace = "")
        String password)
        throws LoginException
    ;

    /**
     * 
     * @param token
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://LibraryService/validEmailWith")
    @WebResult(name = "status", targetNamespace = "")
    @RequestWrapper(localName = "validEmailWith", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ValidEmailWith")
    @ResponseWrapper(localName = "validEmailWithResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ValidEmailWithResponse")
    public String validEmailWith(
        @WebParam(name = "token", targetNamespace = "")
        String token);

    /**
     * 
     * @param email
     * @return
     *     returns java.lang.String
     * @throws EmailSendingException
     */
    @WebMethod(action = "http://LibraryService/resendVerificationEmail")
    @WebResult(name = "status", targetNamespace = "")
    @RequestWrapper(localName = "resendVerificationEmail", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ResendVerificationEmail")
    @ResponseWrapper(localName = "resendVerificationEmailResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ResendVerificationEmailResponse")
    public String resendVerificationEmail(
        @WebParam(name = "email", targetNamespace = "")
        String email)
        throws EmailSendingException
    ;

    /**
     * 
     * @param email
     * @return
     *     returns java.lang.String
     * @throws ForgotPasswordException
     */
    @WebMethod(action = "http://LibraryService/requestPasswordReset")
    @WebResult(name = "status", targetNamespace = "")
    @RequestWrapper(localName = "requestPasswordReset", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.RequestPasswordReset")
    @ResponseWrapper(localName = "requestPasswordResetResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.RequestPasswordResetResponse")
    public String requestPasswordReset(
        @WebParam(name = "email", targetNamespace = "")
        String email)
        throws ForgotPasswordException
    ;

    /**
     * 
     * @param confirmNewPassword
     * @param newPassword
     * @param token
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://LibraryService/resetPassword")
    @WebResult(name = "status", targetNamespace = "")
    @RequestWrapper(localName = "resetPassword", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ResetPassword")
    @ResponseWrapper(localName = "resetPasswordResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ResetPasswordResponse")
    public String resetPassword(
        @WebParam(name = "token", targetNamespace = "")
        String token,
        @WebParam(name = "newPassword", targetNamespace = "")
        String newPassword,
        @WebParam(name = "confirmNewPassword", targetNamespace = "")
        String confirmNewPassword);

    /**
     * 
     * @param confirmNewPassword
     * @param newPassword
     * @param email
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://LibraryService/updatePassword")
    @WebResult(name = "status", targetNamespace = "")
    @RequestWrapper(localName = "updatePassword", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.UpdatePassword")
    @ResponseWrapper(localName = "updatePasswordResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.UpdatePasswordResponse")
    public String updatePassword(
        @WebParam(name = "email", targetNamespace = "")
        String email,
        @WebParam(name = "newPassword", targetNamespace = "")
        String newPassword,
        @WebParam(name = "confirmNewPassword", targetNamespace = "")
        String confirmNewPassword);

    /**
     * 
     * @param usager
     * @param email
     * @return
     *     returns java.lang.String
     * @throws UpdateUserException
     */
    @WebMethod(action = "http://LibraryService/updateUserInfos")
    @WebResult(name = "status", targetNamespace = "")
    @RequestWrapper(localName = "updateUserInfos", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.UpdateUserInfos")
    @ResponseWrapper(localName = "updateUserInfosResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.UpdateUserInfosResponse")
    public String updateUserInfos(
        @WebParam(name = "email", targetNamespace = "")
        String email,
        @WebParam(name = "usager", targetNamespace = "")
        Usager usager)
        throws UpdateUserException
    ;

    /**
     * 
     * @param userID
     * @return
     *     returns java.util.List<generated.libraryservice.Loan>
     */
    @WebMethod(action = "http://LibraryService/getLoansFor")
    @WebResult(name = "loans", targetNamespace = "")
    @RequestWrapper(localName = "getLoansFor", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.GetLoansFor")
    @ResponseWrapper(localName = "getLoansForResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.GetLoansForResponse")
    public List<Loan> getLoansFor(
        @WebParam(name = "userID", targetNamespace = "")
        int userID);

    /**
     * 
     * @return
     *     returns java.util.List<generated.libraryservice.Loan>
     */
    @WebMethod(operationName = "CheckExpiration", action = "http://LibraryService/CheckExpiration")
    @WebResult(name = "loansOverdue", targetNamespace = "")
    @RequestWrapper(localName = "CheckExpiration", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.CheckExpiration")
    @ResponseWrapper(localName = "CheckExpirationResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.CheckExpirationResponse")
    public List<Loan> checkExpiration();

}
