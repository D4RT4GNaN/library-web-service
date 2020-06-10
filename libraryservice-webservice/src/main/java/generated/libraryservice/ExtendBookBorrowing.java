
package generated.libraryservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="borrowingDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="libraryId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="bookReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usager" type="{http://LibraryService/}generatedUsager"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "borrowingDate",
    "libraryId",
    "bookReference",
    "usager"
})
@XmlRootElement(name = "extendBookBorrowing")
public class ExtendBookBorrowing {

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar borrowingDate;
    protected int libraryId;
    @XmlElement(required = true)
    protected String bookReference;
    @XmlElement(required = true)
    protected GeneratedUsager usager;

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
     * Gets the value of the libraryId property.
     * 
     */
    public int getLibraryId() {
        return libraryId;
    }

    /**
     * Sets the value of the libraryId property.
     * 
     */
    public void setLibraryId(int value) {
        this.libraryId = value;
    }

    /**
     * Gets the value of the bookReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookReference() {
        return bookReference;
    }

    /**
     * Sets the value of the bookReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookReference(String value) {
        this.bookReference = value;
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

}
