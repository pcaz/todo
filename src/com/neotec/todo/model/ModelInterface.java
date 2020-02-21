package com.neotec.todo.model;

import java.io.IOException;

import com.neotec.todo.controler.ToDoList;

public interface ModelInterface{
	public void write(ToDoList list, String format) throws IOException ;
	
	public ToDoList read(String filename, String format) throws IOException;
}
