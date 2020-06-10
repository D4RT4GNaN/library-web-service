
package generated.libraryservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated.libraryservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UpdateUserFault_QNAME = new QName("http://LibraryService/", "UpdateUserFault");
    private final static QName _RegisterFault_QNAME = new QName("http://LibraryService/", "RegisterFault");
    private final static QName _BookBorrowingExtensionFault_QNAME = new QName("http://LibraryService/", "BookBorrowingExtensionFault");
    private final static QName _EmailSendingFault_QNAME = new QName("http://LibraryService/", "EmailSendingFault");
    private final static QName _ForgotPasswordFault_QNAME = new QName("http://LibraryService/", "ForgotPasswordFault");
    private final static QName _LoginFault_QNAME = new QName("http://LibraryService/", "LoginFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated.libraryservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ValidEmailWith }
     * 
     */
    public ValidEmailWith createValidEmailWith() {
        return new ValidEmailWith();
    }

    /**
     * Create an instance of {@link GetBookAvailability }
     * 
     */
    public GetBookAvailability createGetBookAvailability() {
        return new GetBookAvailability();
    }

    /**
     * Create an instance of {@link ResetPassword }
     * 
     */
    public ResetPassword createResetPassword() {
        return new ResetPassword();
    }

    /**
     * Create an instance of {@link UpdatePasswordResponse }
     * 
     */
    public UpdatePasswordResponse createUpdatePasswordResponse() {
        return new UpdatePasswordResponse();
    }

    /**
     * Create an instance of {@link CheckExpiration }
     * 
     */
    public CheckExpiration createCheckExpiration() {
        return new CheckExpiration();
    }

    /**
     * Create an instance of {@link UsagerUnspecifiedFault }
     * 
     */
    public UsagerUnspecifiedFault createUsagerUnspecifiedFault() {
        return new UsagerUnspecifiedFault();
    }

    /**
     * Create an instance of {@link AddUser }
     * 
     */
    public AddUser createAddUser() {
        return new AddUser();
    }

    /**
     * Create an instance of {@link GeneratedUsager }
     * 
     */
    public GeneratedUsager createGeneratedUsager() {
        return new GeneratedUsager();
    }

    /**
     * Create an instance of {@link CheckExpirationResponse }
     * 
     */
    public CheckExpirationResponse createCheckExpirationResponse() {
        return new CheckExpirationResponse();
    }

    /**
     * Create an instance of {@link GeneratedBorrowing }
     * 
     */
    public GeneratedBorrowing createGeneratedBorrowing() {
        return new GeneratedBorrowing();
    }

    /**
     * Create an instance of {@link BorrowABook }
     * 
     */
    public BorrowABook createBorrowABook() {
        return new BorrowABook();
    }

    /**
     * Create an instance of {@link ValidEmailWithResponse }
     * 
     */
    public ValidEmailWithResponse createValidEmailWithResponse() {
        return new ValidEmailWithResponse();
    }

    /**
     * Create an instance of {@link ReturnBook }
     * 
     */
    public ReturnBook createReturnBook() {
        return new ReturnBook();
    }

    /**
     * Create an instance of {@link ConnectUser }
     * 
     */
    public ConnectUser createConnectUser() {
        return new ConnectUser();
    }

    /**
     * Create an instance of {@link ResetPasswordResponse }
     * 
     */
    public ResetPasswordResponse createResetPasswordResponse() {
        return new ResetPasswordResponse();
    }

    /**
     * Create an instance of {@link BorrowABookResponse }
     * 
     */
    public BorrowABookResponse createBorrowABookResponse() {
        return new BorrowABookResponse();
    }

    /**
     * Create an instance of {@link AddUserResponse }
     * 
     */
    public AddUserResponse createAddUserResponse() {
        return new AddUserResponse();
    }

    /**
     * Create an instance of {@link RequestPasswordReset }
     * 
     */
    public RequestPasswordReset createRequestPasswordReset() {
        return new RequestPasswordReset();
    }

    /**
     * Create an instance of {@link GetBookAvailabilityResponse }
     * 
     */
    public GetBookAvailabilityResponse createGetBookAvailabilityResponse() {
        return new GetBookAvailabilityResponse();
    }

    /**
     * Create an instance of {@link GeneratedStock }
     * 
     */
    public GeneratedStock createGeneratedStock() {
        return new GeneratedStock();
    }

    /**
     * Create an instance of {@link AddComment }
     * 
     */
    public AddComment createAddComment() {
        return new AddComment();
    }

    /**
     * Create an instance of {@link GeneratedComment }
     * 
     */
    public GeneratedComment createGeneratedComment() {
        return new GeneratedComment();
    }

    /**
     * Create an instance of {@link RequestPasswordResetResponse }
     * 
     */
    public RequestPasswordResetResponse createRequestPasswordResetResponse() {
        return new RequestPasswordResetResponse();
    }

    /**
     * Create an instance of {@link GetBooksWithKeywordResponse }
     * 
     */
    public GetBooksWithKeywordResponse createGetBooksWithKeywordResponse() {
        return new GetBooksWithKeywordResponse();
    }

    /**
     * Create an instance of {@link GeneratedBook }
     * 
     */
    public GeneratedBook createGeneratedBook() {
        return new GeneratedBook();
    }

    /**
     * Create an instance of {@link AddCommentResponse }
     * 
     */
    public AddCommentResponse createAddCommentResponse() {
        return new AddCommentResponse();
    }

    /**
     * Create an instance of {@link UpdatePassword }
     * 
     */
    public UpdatePassword createUpdatePassword() {
        return new UpdatePassword();
    }

    /**
     * Create an instance of {@link UnspecifiedFault }
     * 
     */
    public UnspecifiedFault createUnspecifiedFault() {
        return new UnspecifiedFault();
    }

    /**
     * Create an instance of {@link ResendVerificationEmail }
     * 
     */
    public ResendVerificationEmail createResendVerificationEmail() {
        return new ResendVerificationEmail();
    }

    /**
     * Create an instance of {@link UpdateUserInfosResponse }
     * 
     */
    public UpdateUserInfosResponse createUpdateUserInfosResponse() {
        return new UpdateUserInfosResponse();
    }

    /**
     * Create an instance of {@link GetBorrowingFor }
     * 
     */
    public GetBorrowingFor createGetBorrowingFor() {
        return new GetBorrowingFor();
    }

    /**
     * Create an instance of {@link ResendVerificationEmailResponse }
     * 
     */
    public ResendVerificationEmailResponse createResendVerificationEmailResponse() {
        return new ResendVerificationEmailResponse();
    }

    /**
     * Create an instance of {@link UpdateUserInfos }
     * 
     */
    public UpdateUserInfos createUpdateUserInfos() {
        return new UpdateUserInfos();
    }

    /**
     * Create an instance of {@link GetBorrowingForResponse }
     * 
     */
    public GetBorrowingForResponse createGetBorrowingForResponse() {
        return new GetBorrowingForResponse();
    }

    /**
     * Create an instance of {@link ExtendBookBorrowingResponse }
     * 
     */
    public ExtendBookBorrowingResponse createExtendBookBorrowingResponse() {
        return new ExtendBookBorrowingResponse();
    }

    /**
     * Create an instance of {@link ExtendBookBorrowing }
     * 
     */
    public ExtendBookBorrowing createExtendBookBorrowing() {
        return new ExtendBookBorrowing();
    }

    /**
     * Create an instance of {@link ConnectUserResponse }
     * 
     */
    public ConnectUserResponse createConnectUserResponse() {
        return new ConnectUserResponse();
    }

    /**
     * Create an instance of {@link ReturnBookResponse }
     * 
     */
    public ReturnBookResponse createReturnBookResponse() {
        return new ReturnBookResponse();
    }

    /**
     * Create an instance of {@link GetBooksWithKeyword }
     * 
     */
    public GetBooksWithKeyword createGetBooksWithKeyword() {
        return new GetBooksWithKeyword();
    }

    /**
     * Create an instance of {@link GeneratedLibrary }
     * 
     */
    public GeneratedLibrary createGeneratedLibrary() {
        return new GeneratedLibrary();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnspecifiedFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LibraryService/", name = "UpdateUserFault")
    public JAXBElement<UnspecifiedFault> createUpdateUserFault(UnspecifiedFault value) {
        return new JAXBElement<UnspecifiedFault>(_UpdateUserFault_QNAME, UnspecifiedFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsagerUnspecifiedFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LibraryService/", name = "RegisterFault")
    public JAXBElement<UsagerUnspecifiedFault> createRegisterFault(UsagerUnspecifiedFault value) {
        return new JAXBElement<UsagerUnspecifiedFault>(_RegisterFault_QNAME, UsagerUnspecifiedFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnspecifiedFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LibraryService/", name = "BookBorrowingExtensionFault")
    public JAXBElement<UnspecifiedFault> createBookBorrowingExtensionFault(UnspecifiedFault value) {
        return new JAXBElement<UnspecifiedFault>(_BookBorrowingExtensionFault_QNAME, UnspecifiedFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnspecifiedFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LibraryService/", name = "EmailSendingFault")
    public JAXBElement<UnspecifiedFault> createEmailSendingFault(UnspecifiedFault value) {
        return new JAXBElement<UnspecifiedFault>(_EmailSendingFault_QNAME, UnspecifiedFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnspecifiedFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LibraryService/", name = "ForgotPasswordFault")
    public JAXBElement<UnspecifiedFault> createForgotPasswordFault(UnspecifiedFault value) {
        return new JAXBElement<UnspecifiedFault>(_ForgotPasswordFault_QNAME, UnspecifiedFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnspecifiedFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LibraryService/", name = "LoginFault")
    public JAXBElement<UnspecifiedFault> createLoginFault(UnspecifiedFault value) {
        return new JAXBElement<UnspecifiedFault>(_LoginFault_QNAME, UnspecifiedFault.class, null, value);
    }

}
