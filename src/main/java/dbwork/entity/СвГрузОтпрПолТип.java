//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.11.02 at 07:21:58 PM SAMT 
//


package dbwork.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * �������� � ���������������� (���������������)
 * 
 * <p>Java class for ���������������� complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="����������������">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="�������">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="�������">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="1"/>
 *                         &lt;maxLength value="1000"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="�����" type="{}������"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="�����" type="{}��������"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "����������������", propOrder = {
    "�������",
    "�����"
})
public class ���������������� {

    @XmlElement(name = "�������", required = true)
    protected ����������������.������� �������;
    @XmlElement(name = "�����", required = true)
    protected �������� �����;

    /**
     * Gets the value of the ������� property.
     *
     * @return
     *     possible object is
     *     {@link ����������������.������� }
     *
     */
    public ����������������.������� get�������() {
        return �������;
    }

    /**
     * Sets the value of the ������� property.
     *
     * @param value
     *     allowed object is
     *     {@link ����������������.������� }
     *
     */
    public void set�������(����������������.������� value) {
        this.������� = value;
    }

    /**
     * Gets the value of the ����� property.
     *
     * @return
     *     possible object is
     *     {@link �������� }
     *
     */
    public �������� get�����() {
        return �����;
    }

    /**
     * Sets the value of the ����� property.
     *
     * @param value
     *     allowed object is
     *     {@link �������� }
     *
     */
    public void set�����(�������� value) {
        this.����� = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice>
     *         &lt;element name="�������">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="1"/>
     *               &lt;maxLength value="1000"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="�����" type="{}������"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "�������",
        "�����"
    })
    public static class ������� {

        @XmlElement(name = "�������")
        protected String �������;
        @XmlElement(name = "�����")
        protected ������ �����;

        /**
         * Gets the value of the ������� property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String get�������() {
            return �������;
        }

        /**
         * Sets the value of the ������� property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void set�������(String value) {
            this.������� = value;
        }

        /**
         * Gets the value of the ����� property.
         * 
         * @return
         *     possible object is
         *     {@link ������ }
         *     
         */
        public ������ get�����() {
            return �����;
        }

        /**
         * Sets the value of the ����� property.
         * 
         * @param value
         *     allowed object is
         *     {@link ������ }
         *     
         */
        public void set�����(������ value) {
            this.����� = value;
        }

    }

}
