package com.neotec.todo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;


import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Test;

import com.neotec.todo.controler.StateType;
import com.neotec.todo.controler.Task;
import com.neotec.todo.controler.ToDoList;


public class XMLSaveTest {

	Task	tasks[]=new Task[4];
    ToDoList theList;
	
	private void newTask(){
		
		
		tasks[0]= new Task();
		tasks[0].setName("Tâche 1");
		tasks[0].setDescription("Description de la tâche 1");
		tasks[0].setNote("Ceci est la tâche 1 de test");
		tasks[0].setPriority(3);
		tasks[0].setCategory("Famille");
		tasks[0].setProject(null);
		tasks[0].setState(StateType.NOT_STARTED);
		tasks[0].setResources(null);
		tasks[0].setEstimatedTime(new Duration(1000*60*60*8));
		tasks[0].setDueDateTime(new DateTime(2012,12,25,0,0));
		tasks[0].setBeginningDateTime(null);
		tasks[0].setEndDateTime(null);
		tasks[0].setPrerequisite(null);
		
		tasks[1]= new Task();
		tasks[1].setName("Tâche 2");
		tasks[1].setDescription("Description de la tâche");
		tasks[1].setNote("Ceci est la tâche 2 de test");
		tasks[1].setPriority(5);
		tasks[1].setCategory("Famille");
		tasks[1].setProject(null);
		tasks[1].setState(StateType.NOT_STARTED);
		tasks[1].setResources(null);
		tasks[1].setEstimatedTime(new Duration(1000*60*60*2));
		tasks[1].setDueDateTime(new DateTime(2013,1,1,0,0));
		tasks[1].setBeginningDateTime(null);
		tasks[1].setEndDateTime(null);
		tasks[1].setPrerequisite(null);
		
		tasks[2]= new Task();
		tasks[2].setName("Tâche 1");
		tasks[2].setDescription("Description de la tâche");
		tasks[2].setNote("Ceci est la tâche 2 de test");
		tasks[2].setPriority(5);
		tasks[2].setCategory("Famille");
		tasks[2].setProject(null);
		tasks[2].setState(StateType.NOT_STARTED);
		tasks[2].setResources(null);
		tasks[2].setEstimatedTime(new Duration(0));
		tasks[2].setDueDateTime(new DateTime((long)0));
		tasks[2].setBeginningDateTime(null);
		tasks[2].setEndDateTime(null);
		tasks[2].setPrerequisite(null);
		
		tasks[3]= new Task();
		
//		for(int i=0; i< 4; i++) System.out.println(tasks[i].toString());
		
		
	}
	
	@Test
	public void SaveReadTest() {
		
		ToDoProperties.Create();
		
		File testFile= new File("test.xml");
		testFile.delete();
		assertFalse("Le fichier n'existe pas", testFile.exists());

		
		theList=new ToDoList();
		
		try {
				theList.read("test.xml","XML");
		} catch (IOException e) {
			    assertTrue(e instanceof FileNotFoundException);
		}
	    
				
		newTask();
		
		theList.put(tasks[0]);
		theList.put(tasks[1]);
		theList.put(tasks[2]);
		theList.put(tasks[3]);
			
		assertEquals(theList.getTasks().size(), 4);
		
		Iterator<Task> it;
		
		it = theList.getTasks().byIdIterator();
		int ix=0;
		while(it.hasNext())
		{
			ix++;
			assertTrue( it.next().getId()==ix);
		}
		
		theList.write("test.xml","XML");
		
		it = theList.getTasks().byIdIterator();
		while(it.hasNext())
		{
			it.next();
			it.remove();
		}
		
		theList=null;
		
		theList=new ToDoList();
		
		
		try {
				theList.read("test.xml","XML");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(theList.getTasks().size(),4);
		
		
		it = theList.getTasks().byIdIterator();
		ix=0;
		while(it.hasNext())
		{
			Task courant=it.next();
			assertTrue  (courant.equals(tasks[ix]));
			ix++;
			assertEquals (courant.getId(),ix);
		}
		
}

}
