package com.neotec.todo.controler;

import java.util.ArrayList;


@SuppressWarnings("serial")
public class Category extends ArrayList<String>{

	String defaultCategories[]={
			"none"
			
	};
	
	Category(){
		super();
		for(int i=0; i<defaultCategories.length; i++)
		{
			this.add(defaultCategories[i]);
		}
		
	}

		
}
