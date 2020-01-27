
package generated.libraryservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="libraryIds" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded"/>
 *         &lt;element name="bookReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "libraryIds",
    "bookReference"
})
@XmlRootElement(name = "getBookAvailability")
public class GetBookAvailability {

    @XmlElement(type = Integer.class)
    protected List<Integer> libraryIds;
    @XmlElement(required = true)
    protected String bookReference;

    /**
     * Gets the value of the libraryIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the libraryIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLibraryIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getLibraryIds() {
        if (libraryIds == null) {
            libraryIds = new ArrayList<Integer>();
        }
        return this.libraryIds;
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

}
