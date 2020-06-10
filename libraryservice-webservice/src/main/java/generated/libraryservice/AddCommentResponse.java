
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
 *         &lt;element name="savedComment" type="{http://LibraryService/}generatedComment"/>
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
    "savedComment"
})
@XmlRootElement(name = "addCommentResponse")
public class AddCommentResponse {

    @XmlElement(required = true)
    protected GeneratedComment savedComment;

    /**
     * Gets the value of the savedComment property.
     * 
     * @return
     *     possible object is
     *     {@link GeneratedComment }
     *     
     */
    public GeneratedComment getSavedComment() {
        return savedComment;
    }

    /**
     * Sets the value of the savedComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeneratedComment }
     *     
     */
    public void setSavedComment(GeneratedComment value) {
        this.savedComment = value;
    }

}
