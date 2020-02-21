package com.neotec.todo.model;

import java.io.IOException;

import com.neotec.todo.controler.ToDoList;

public interface IODriver {
	
	public void write(ToDoList todo, String fileName) throws IOException;
	public void read(String fileName, ToDoList todo) throws IOException;

}
