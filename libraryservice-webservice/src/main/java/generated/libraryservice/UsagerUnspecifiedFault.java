
package generated.libraryservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for usagerUnspecifiedFault complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="usagerUnspecifiedFault">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usager" type="{http://LibraryService/}generatedUsager"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usagerUnspecifiedFault", propOrder = {
    "usager",
    "message"
})
public class UsagerUnspecifiedFault {

    @XmlElement(required = true)
    protected GeneratedUsager usager;
    @XmlElement(required = true)
    protected String message;

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
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

}
