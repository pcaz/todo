//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.12.10 at 11:04:46 PM CET 
//


package com.neotec.todo.controler;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StateType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StateType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NotStarted"/>
 *     &lt;enumeration value="Started"/>
 *     &lt;enumeration value="Waiting"/>
 *     &lt;enumeration value="Finished"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StateType")
@XmlEnum
public enum StateType {
    NOT_STARTED("Not Started"),
    STARTED("Started"),
    WAITING("Waiting"),
    FINISHED("Finished");

    private final String value;


   
    StateType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StateType fromValue(String v) {
        for (StateType c: StateType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
    

    public static int getIndex(String value){ 
    	int ind=0;
    	for( StateType st: StateType.values()){
    		if(st.value.equals(value)) return ind;
    		ind++;
    	}
    	return -1;
    }
    
    public static StateType getValue(int index){
    	for( StateType st: StateType.values()){
    		if(st.ordinal()== index) 
    			return st;
    	}
    	return NOT_STARTED;
    }
    
    public static String getDefault(){
    	return NOT_STARTED.value;
    }
}