//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.11.02 at 07:21:58 PM SAMT 
//


package dbwork.entity;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ����� ������
 * 
 * <p>Java class for ����������� complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="�����������">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="��������">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="19"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="��������">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="10"/>
 *               &lt;enumeration value="��� ������"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "�����������", propOrder = {
    "��������",
    "��������"
})
public class ����������� {

    @XmlElement(name = "��������")
    protected BigDecimal ��������;
    @XmlElement(name = "��������")
    protected String ��������;

    /**
     * Gets the value of the �������� property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal get��������() {
        return ��������;
    }

    /**
     * Sets the value of the �������� property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void set��������(BigDecimal value) {
        this.�������� = value;
    }

    /**
     * Gets the value of the �������� property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String get��������() {
        return ��������;
    }

    /**
     * Sets the value of the �������� property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void set��������(String value) {
        this.�������� = value;
    }

}
