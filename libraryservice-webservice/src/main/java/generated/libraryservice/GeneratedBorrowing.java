
package generated.libraryservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for generatedBorrowing complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="generatedBorrowing">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="borrowingDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="book" type="{http://LibraryService/}generatedBook"/>
 *         &lt;element name="usager" type="{http://LibraryService/}generatedUsager"/>
 *         &lt;element name="library" type="{http://LibraryService/}generatedLibrary"/>
 *         &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="extended" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "generatedBorrowing", propOrder = {
    "borrowingDate",
    "book",
    "usager",
    "library",
    "expiryDate",
    "extended",
    "status",
    "quantity"
})
public class GeneratedBorrowing {

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar borrowingDate;
    @XmlElement(required = true)
    protected GeneratedBook book;
    @XmlElement(required = true)
    protected GeneratedUsager usager;
    @XmlElement(required = true)
    protected GeneratedLibrary library;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expiryDate;
    protected boolean extended;
    @XmlElement(required = true)
    protected String status;
    protected int quantity;

    /**
     * Gets the value of the borrowingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBorrowingDate() {
        return borrowingDate;
    }

    /**
     * Sets the value of the borrowingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBorrowingDate(XMLGregorianCalendar value) {
        this.borrowingDate = value;
    }

    /**
     * Gets the value of the book property.
     * 
     * @return
     *     possible object is
     *     {@link GeneratedBook }
     *     
     */
    public GeneratedBook getBook() {
        return book;
    }

    /**
     * Sets the value of the book property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeneratedBook }
     *     
     */
    public void setBook(GeneratedBook value) {
        this.book = value;
    }

    /**
     * Gets the value of the usager property.
     * 
     * @return
     *     possible object is
     *     {@link GeneratedUsager }
     *     
     */
    public GeneratedUsager getUsager() {
        return usager;
    }

    /**
     * Sets the value of the usager property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeneratedUsager }
     *     
     */
    public void setUsager(GeneratedUsager value) {
        this.usager = value;
    }

    /**
     * Gets the value of the library property.
     * 
     * @return
     *     possible object is
     *     {@link GeneratedLibrary }
     *     
     */
    public GeneratedLibrary getLibrary() {
        return library;
    }

    /**
     * Sets the value of the library property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeneratedLibrary }
     *     
     */
    public void setLibrary(GeneratedLibrary value) {
        this.library = value;
    }

    /**
     * Gets the value of the expiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the value of the expiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpiryDate(XMLGregorianCalendar value) {
        this.expiryDate = value;
    }

    /**
     * Gets the value of the extended property.
     * 
     */
    public boolean isExtended() {
        return extended;
    }

    /**
     * Sets the value of the extended property.
     * 
     */
    public void setExtended(boolean value) {
        this.extended = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(int value) {
        this.quantity = value;
    }

}
