package com.neotec.todo.model;

import java.io.IOException;

import com.neotec.todo.ToDoProperties;
import com.neotec.todo.controler.ToDoList;


public class storageFactory {
	
	static protected IODriver driver;
	protected String filename;
	
	public void create(String filename, String format){

		if(format==null) throw new IllegalArgumentException();
		switch (format){
		case "XML":
			if(filename.contains(".xml")) this.filename=filename;
			else this.filename=filename.concat(".xml");	
			ToDoProperties.put("fileName",this.filename);
			driver= new XMLDriver();
			return;
	
		
		
		default:
			throw new IllegalArgumentException();
		}
		
	}
	
	public void write(ToDoList tt) throws IOException {
		driver.write(tt,this.filename);
	}
	
	public void read(ToDoList todo)throws IOException {
		
		driver.read(this.filename,todo);
		return;
	}
	
}
