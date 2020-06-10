
package generated.libraryservice;

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
 *         &lt;element name="borrowing" type="{http://LibraryService/}generatedBorrowing"/>
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
    "borrowing"
})
@XmlRootElement(name = "borrowABookResponse")
public class BorrowABookResponse {

    @XmlElement(required = true)
    protected GeneratedBorrowing borrowing;

    /**
     * Gets the value of the borrowing property.
     * 
     * @return
     *     possible object is
     *     {@link GeneratedBorrowing }
     *     
     */
    public GeneratedBorrowing getBorrowing() {
        return borrowing;
    }

    /**
     * Sets the value of the borrowing property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeneratedBorrowing }
     *     
     */
    public void setBorrowing(GeneratedBorrowing value) {
        this.borrowing = value;
    }

}
