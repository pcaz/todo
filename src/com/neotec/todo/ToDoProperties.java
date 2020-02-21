package com.neotec.todo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


@SuppressWarnings("serial")
public final class ToDoProperties extends Properties {
	
	static boolean instance=false;

	static public String[][] defaultProperties={
		{"version", ToDo.version},
		{"language","fr"},
		{"country","FR"},
		{"fileFormat","XML"},
		{"fileName",""},
		{"manytasks","false"},
		{"homepath","~"},
		{"debug","false"}
		
	};
	
	static Properties properties=new Properties();
	
	private ToDoProperties(){}
	
	static public void Create(){
		if(instance) return; else instance=true;

		/**
		 * Lit le ficihier de properties		
		 */
		FileInputStream in;		
		Properties oldProperties = new Properties();
		try {
			in = new FileInputStream("ToDo.properties");
			try{
				oldProperties.load(in);
			}
			finally {in.close();}
		} catch (IOException e) {
			//		le fichier Properties n'existe pas ou est illisible, on continue avec les valeurs pas défaut
			//		e.printStackTrace();
					ToDoWrite.writeWarning("File not found : \"ToDo.properties\"");
		}
		/**
		 * Mets à jour les propriétés	
		 */
							
		for(int i=0; i< defaultProperties.length; i++){
				properties.setProperty(defaultProperties[i][0],oldProperties.getProperty(defaultProperties[i][0],defaultProperties[i][1]));
									
		}
	

	}	
	
	static public String get(String key){
		return (String)properties.getProperty(key);
	}
	
	static public void put(String key, String value){
		properties.put(key, value);
	}
}
