//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2005.09.05 at 03:09:41 PM IST 
//


package com.sun.xml.wss.saml.internal.saml11.jaxb10;


/**
 * Java content class for SubjectType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/space/combination/jwsdp1.6_tc/jaxb/bin/oasis-sstc-saml-schema-assertion-1.1.xsd line 94)
 * <p>
 * <pre>
 * &lt;complexType name="SubjectType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}NameIdentifier"/>
 *           &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}SubjectConfirmation" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}SubjectConfirmation"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface SubjectType {


    /**
     * Gets the value of the subjectConfirmation property.
     * 
     * @return
     *     possible object is
     *     {@link com.sun.xml.wss.saml.internal.saml11.jaxb10.SubjectConfirmationType}
     *     {@link com.sun.xml.wss.saml.internal.saml11.jaxb10.SubjectConfirmation}
     *     {@link com.sun.xml.wss.saml.internal.saml11.jaxb10.SubjectConfirmationType}
     *     {@link com.sun.xml.wss.saml.internal.saml11.jaxb10.SubjectConfirmation}
     */
    com.sun.xml.wss.saml.internal.saml11.jaxb10.SubjectConfirmationType getSubjectConfirmation();

    /**
     * Sets the value of the subjectConfirmation property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.sun.xml.wss.saml.internal.saml11.jaxb10.SubjectConfirmationType}
     *     {@link com.sun.xml.wss.saml.internal.saml11.jaxb10.SubjectConfirmation}
     *     {@link com.sun.xml.wss.saml.internal.saml11.jaxb10.SubjectConfirmationType}
     *     {@link com.sun.xml.wss.saml.internal.saml11.jaxb10.SubjectConfirmation}
     */
    void setSubjectConfirmation(com.sun.xml.wss.saml.internal.saml11.jaxb10.SubjectConfirmationType value);

    /**
     * Gets the value of the nameIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link com.sun.xml.wss.saml.internal.saml11.jaxb10.NameIdentifier}
     *     {@link com.sun.xml.wss.saml.internal.saml11.jaxb10.NameIdentifierType}
     */
    com.sun.xml.wss.saml.internal.saml11.jaxb10.NameIdentifierType getNameIdentifier();

    /**
     * Sets the value of the nameIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.sun.xml.wss.saml.internal.saml11.jaxb10.NameIdentifier}
     *     {@link com.sun.xml.wss.saml.internal.saml11.jaxb10.NameIdentifierType}
     */
    void setNameIdentifier(com.sun.xml.wss.saml.internal.saml11.jaxb10.NameIdentifierType value);

}