/*
 * Copyright (c) 1997, 2021 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.2-b01-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.08.10 at 02:34:36 PM IST 
//


package com.sun.xml.ws.security.secext11;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.sun.xml.security.core.dsig.runtime.ZeroOneBooleanAdapter;
import com.sun.xml.security.core.xenc.EncryptedDataType;


/**
 * <p>Java class for EncryptedHeaderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="EncryptedHeaderType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element ref="{http://www.w3.org/2001/04/xmlenc#}EncryptedData"/>
 *       </sequence>
 *       <attribute ref="{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Id"/>
 *       <attribute ref="{http://schemas.xmlsoap.org/soap/envelope/}actor"/>
 *       <attribute ref="{http://schemas.xmlsoap.org/soap/envelope/}mustUnderstand"/>
 *       <attribute ref="{http://www.w3.org/2003/05/soap-envelope}mustUnderstand"/>
 *       <attribute ref="{http://www.w3.org/2003/05/soap-envelope}relay"/>
 *       <attribute ref="{http://www.w3.org/2003/05/soap-envelope}role"/>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncryptedHeaderType", propOrder = {
    "encryptedData"
})
public class EncryptedHeaderType {

    @XmlElement(name = "EncryptedData", namespace = "http://www.w3.org/2001/04/xmlenc#", required = true)
    protected EncryptedDataType encryptedData;
    @XmlAttribute(name = "Id", namespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute(namespace = "http://schemas.xmlsoap.org/soap/envelope/")
    protected String actor;
    @XmlAttribute(namespace = "http://schemas.xmlsoap.org/soap/envelope/")
    @XmlJavaTypeAdapter(ZeroOneBooleanAdapter.class)
    protected Boolean mustUnderstand;
    @XmlAttribute(name = "mustUnderstand", namespace = "http://www.w3.org/2003/05/soap-envelope")
    protected Boolean mustUnderstand12;
    @XmlAttribute(namespace = "http://www.w3.org/2003/05/soap-envelope")
    protected Boolean relay;
    @XmlAttribute(namespace = "http://www.w3.org/2003/05/soap-envelope")
    protected String role;

    /**
     * Gets the value of the encryptedData property.
     * 
     * @return
     *     possible object is
     *     {@link EncryptedDataType }
     *     
     */
    public EncryptedDataType getEncryptedData() {
        return encryptedData;
    }

    /**
     * Sets the value of the encryptedData property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptedDataType }
     *     
     */
    public void setEncryptedData(EncryptedDataType value) {
        this.encryptedData = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the actor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActor() {
        return actor;
    }

    /**
     * Sets the value of the actor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActor(String value) {
        this.actor = value;
    }

    /**
     * Gets the value of the mustUnderstand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Boolean isMustUnderstand() {
        return mustUnderstand;
    }

    /**
     * Sets the value of the mustUnderstand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMustUnderstand(Boolean value) {
        this.mustUnderstand = value;
    }

    /**
     * Gets the value of the mustUnderstand12 property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isMustUnderstand12() {
        if (mustUnderstand12 == null) {
            return false;
        } else {
            return mustUnderstand12;
        }
    }

    /**
     * Sets the value of the mustUnderstand12 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMustUnderstand12(Boolean value) {
        this.mustUnderstand12 = value;
    }

    /**
     * Gets the value of the relay property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isRelay() {
        if (relay == null) {
            return false;
        } else {
            return relay;
        }
    }

    /**
     * Sets the value of the relay property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRelay(Boolean value) {
        this.relay = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRole(String value) {
        this.role = value;
    }

}
