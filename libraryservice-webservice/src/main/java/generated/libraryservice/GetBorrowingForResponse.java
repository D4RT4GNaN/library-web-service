
package generated.libraryservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="booksBorrowed" type="{http://LibraryService/}generatedBorrowing" maxOccurs="unbounded" minOccurs="0"/>
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
    "booksBorrowed"
})
@XmlRootElement(name = "getBorrowingForResponse")
public class GetBorrowingForResponse {

    protected List<GeneratedBorrowing> booksBorrowed;

    /**
     * Gets the value of the booksBorrowed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the booksBorrowed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBooksBorrowed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GeneratedBorrowing }
     * 
     * 
     */
    public List<GeneratedBorrowing> getBooksBorrowed() {
        if (booksBorrowed == null) {
            booksBorrowed = new ArrayList<GeneratedBorrowing>();
        }
        return this.booksBorrowed;
    }

}
