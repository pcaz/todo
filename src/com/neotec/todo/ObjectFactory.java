//
//This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.12.10 at 11:04:46 PM CET 
//


package com.neotec.todo;

import com.neotec.todo.controler.Project;
import com.neotec.todo.controler.RecurringTask;
import com.neotec.todo.controler.Task;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.neotec.todo package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */

public class ObjectFactory  {

/**
 * ObjectFactory is a singleton	
 */
	transient static private ObjectFactory Instance=null;
/**
 * ObjectFactory counts created objects	
 */
	transient private int nbTask=0;
    transient private int nbRecurringTask=0;
	transient private int nbProject=0;
	
    

	/**
     * Private Constructor to preserve the singleton
     * 
     */
    private ObjectFactory(){
    	nbTask=0;
    	nbRecurringTask=0;
    	nbProject=0;
    }
    
    public static ObjectFactory getInstance(){
    	
    	if(null==Instance) Instance=new ObjectFactory();
    	return Instance;
    	
    }

    public int getNbTask() {
		return nbTask;
	}

	public int getNbRecurringTask() {
		return nbRecurringTask;
	}

	public int getNbProject() {
		return nbProject;
	}
    /**
     * Create an instance of Task
     * 
     */
    public Task createTask() {
        Task tt=new Task();
        nbTask++;
        return tt;
    }
    
    /**
     * Delete an instance of Task
     * 
     */
    public Task deleteTask(Task tt) {
    	
        nbTask++;
        return tt;
    }
    /**
     * Create an instance of RecurringTask 
     * 
     */
    public RecurringTask createRecurringTask() {
        return new RecurringTask();
    }
    
    /**
     * Create an instance of Project
     * 
     */
    public Project createProject() {
        return new Project();
    }

   

   
}