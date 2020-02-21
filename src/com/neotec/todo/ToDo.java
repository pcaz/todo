package com.neotec.todo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.eclipse.swt.widgets.Table;

import com.neotec.todo.controler.Task;
import com.neotec.todo.controler.ToDoList;



public class ToDo {
	static  public String applicationName="ToDo";
	static	public String version="1.0";
	public ToDo(){}
		
		/**
		 * Variables globale		
		 */
	
			
	static ToDoList theList=null;

			
	static ResourceBundle messages;
	

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ToDoProperties.Create();

		FileInputStream in;		
		

			
/**
* Nationalisation de l'application		
*/

	
		try
		{
			String fileName= "ToDo."+ToDoProperties.get("language")+"_"+ToDoProperties.get("country");
			in = new FileInputStream(fileName);
			messages = new PropertyResourceBundle(in);
		}catch(IOException e){
			try {
				in = new FileInputStream("ToDo.fr_FR");
				messages = new PropertyResourceBundle(in);
			}catch (IOException ex){
				ex.printStackTrace();
			}
		}
/**
 * Ouverture du fichier de tâche
 */
		ToDoList theList=new ToDoList();
		try {
				theList.read(ToDoProperties.get("fileName"),ToDoProperties.get("fileFormat"));
		} catch (IOException e) {
				ToDoWrite.writeWarning("File not found : \""+ToDoProperties.get("fileName")+"\"");
		}
	
		Iterator<Task> it2=null;
	
		it2 = theList.getTasks().byNameIterator();
	
	while(it2.hasNext())
		{
			System.out.println(it2.next().toString());
	}
		
//	Task task1= new Task();
//	task1.setName("Tâche 1");
//	task1.setDescription("Description de la tâche 1");
//	task1.setNote("Ceci est la tâche 1 de test");
//	task1.setPriority(3);
//	task1.setCategory(theList.getCategories().get(0));
//	task1.setProject(null);
//	task1.setState(StateType.NOT_STARTED);
//	task1.setResources(null);
//	task1.setEstimatedTime(new Duration(1000*60*60*8));
//	task1.setDueDateTime(new DateTime(2012,12,25,0,0,0,0));
//	task1.setBeginningDateTime(null);
//	task1.setEndDateTime(null);
//	task1.setPrerequisite(null);
//	
//	Task task2= new Task();
//	task2.setName("Tâche 2");
//	task2.setDescription("Description de la tâche ");
//	task2.setNote("Ceci est la tâche 2 de test");
//	task2.setPriority(5);
//	task2.setCategory("Famille");
//	task2.setProject(null);
//	task2.setState(StateType.NOT_STARTED);
//	task2.setResources(null);
//	task2.setEstimatedTime(new Duration(1000*60*60*2));
//	task2.setDueDateTime(new DateTime(2013,1,1,0,0,0,0));
//	task2.setBeginningDateTime(null);
//	task2.setEndDateTime(null);
//	task2.setPrerequisite(null);
//	
//	Task task3= new Task();
//	task3.setName("Tâche 1");
//	task3.setDescription("Description de la tâche");
//	task3.setNote("Ceci est la tâche 2 de test");
//	task3.setPriority(5);
//	task3.setCategory("Famille");
//	task3.setProject(null);
//	task3.setState(StateType.NOT_STARTED);
//	task3.setResources(null);
//	task3.setEstimatedTime(new Duration(0));
//	task3.setDueDateTime(new DateTime(0));
//	task3.setBeginningDateTime(null);
//	task3.setEndDateTime(null);
//	task3.setPrerequisite(null);
//	
//	Task task4= new Task();
//	
//	
//	TaskList todo=theList.getTasks();
//	todo.put(task1);
//	todo.put(task2);
//	todo.put(task3);
//	todo.put(task4);
//
//	
//	
//	Iterator<Task> it2=null;
//	
//	it2 = todo.byNameIterator();
//	
//	while(it2.hasNext())
//	{
//		System.out.println(it2.next().toString());
//	}
//
//	
//	System.out.println("---- By Name ---");
//	
//	it2=todo.getByName("Tâche 1").iterator();
//	while(it2!=null && it2.hasNext()){
//		System.out.println(it2.next().toString());
//	}
//	
//	System.out.println("--- By Due DateTime ---");
//	
//	it2=todo.byDueDateTimeIterator();// new DateTime(201,12,26,0,0,0,0));
//	while(it2.hasNext())
//	{
//		System.out.println(it2.next().toString());
//	}
//	System.out.println("--- Avant Remove ---");
//	System.out.println("Size = "+todo.size());
//	todo.removeTask(task2);
//	
//		
//
//	System.out.println("--- Après Remove ---");
//	System.out.println("Size = "+todo.size());
//
//	it2 = todo.byNameIterator();
//	
//	while(it2.hasNext())
//	{
//		System.out.println(it2.next().toString());
//	}
//	
//	System.out.println("--- by Id ---");
//	it2 = todo.byIdIterator();
//	
//	while(it2.hasNext())
//	{
//		System.out.println(it2.next().toString());
//	}
//	
//	todo.put(task2);
//	
//	System.out.println("--- Modify task 1 ---");
//	
//	task1.setName("Nouvelle tâche 1");
//	task1.setDueDateTime(new DateTime());
//	task1.setState(StateType.STARTED);
//	
//	System.out.println("--- By name ---");
//	it2=todo.byNameIterator();
//	while(it2!=null && it2.hasNext()){
//		System.out.println(it2.next().toString());
//	}
//	System.out.println("--- Modify by dueDateTime ---");
//	it2=todo.byDueDateTimeIterator();
//	while(it2.hasNext())
//	{
//		System.out.println(it2.next().toString());
//	}
//	
//	System.out.println("--- Modify by BbginningDueDateTime ---");
//	it2 = todo.byDueBeginningDateTimeIterator();
//	while(it2.hasNext())
//	{
//		System.out.println(it2.next().toString());
//	}
//	
//	theList.write(ToDoProperties.get("fileName"),ToDoProperties.get("fileFormat"));
//	
   }
}


