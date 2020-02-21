package com.neotec.todo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
/**
 * <p>Java class for TaskTemplateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TaskTemplateType">
 *   &lt;complexContent>
 *     &lt;restriction base="xsd:anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="xsd:string"/>
  *         &lt;element name="Resources" type="xsd:string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="EstimatedTime" type="xsd:duration" minOccurs="0"/>
 *         &lt;element name="DueDateTime" type="xsd:dateTime" minOccurs="0"/>
  *       &lt;/sequence>
 *       &lt;attribute name="version" type="xsd:integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaskTemplateType", propOrder = {
    "name",
    "category",
    "resources",
    "estimatedTime",
    "dueDateTime"
})

public class TaskTemplate {
	@XmlAttribute(name="version")
	static int version=1;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Category")
    protected String category;
    @XmlElement(name = "Resources")
    protected List<String> resources;
    @XmlElement(name = "EstimatedTime")
    protected Duration estimatedTime;
    @XmlElement(name = "DueDateTime")
    protected XMLGregorianCalendar dueDateTime;
}
