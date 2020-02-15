
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
 *         &lt;element name="loan" type="{http://LibraryService/}loan"/>
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
    "loan"
})
@XmlRootElement(name = "extendLoan")
public class ExtendLoan {

    @XmlElement(required = true)
    protected Loan loan;

    /**
     * Gets the value of the loan property.
     * 
     * @return
     *     possible object is
     *     {@link Loan }
     *     
     */
    public Loan getLoan() {
        return loan;
    }

    /**
     * Sets the value of the loan property.
     * 
     * @param value
     *     allowed object is
     *     {@link Loan }
     *     
     */
    public void setLoan(Loan value) {
        this.loan = value;
    }

}
