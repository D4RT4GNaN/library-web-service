
package generated.libraryservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for generatedStock complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="generatedStock">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="library" type="{http://LibraryService/}generatedLibrary"/>
 *         &lt;element name="referenceBook" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "generatedStock", propOrder = {
    "library",
    "referenceBook",
    "quantity"
})
public class GeneratedStock {

    @XmlElement(required = true)
    protected GeneratedLibrary library;
    @XmlElement(required = true)
    protected String referenceBook;
    protected int quantity;

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
     * Gets the value of the referenceBook property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceBook() {
        return referenceBook;
    }

    /**
     * Sets the value of the referenceBook property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceBook(String value) {
        this.referenceBook = value;
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
