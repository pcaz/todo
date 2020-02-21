package com.neotec.todo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Test;

import com.neotec.todo.controler.StateType;
import com.neotec.todo.controler.Task;


public class TaskTest {
	Task task1;
	Task task2;
	Task task3;
	Task task4;
	
	private void newTask(){
		
				
		task1= new Task();
		task1.setName("Tâche 1");
		task1.setDescription("Description de la tâche 1");
		task1.setNote("Ceci est la tâche 1 de test");
		task1.setPriority(3);
		task1.setCategory("Famille");
		task1.setProject(null);
		task1.setState(StateType.NOT_STARTED);
		task1.setResources(null);
		task1.setEstimatedTime(new Duration(1000*60*60*8));
		task1.setDueDateTime(new DateTime(2012,12,25,0,0));
		task1.setBeginningDateTime(null);
		task1.setEndDateTime(null);
		task1.setPrerequisite(null);
		
		task2= new Task();
		task2.setName("Tâche 2");
		task2.setDescription("Description de la tâche ");
		task2.setNote("Ceci est la tâche 2 de test");
		task2.setPriority(5);
		task2.setCategory("Famille");
		task2.setProject(null);
		task2.setState(StateType.NOT_STARTED);
		task2.setResources(null);
		task2.setEstimatedTime(new Duration(1000*60*60*2));
		task2.setDueDateTime(new DateTime(2013,1,1,0,0));
		task2.setBeginningDateTime(null);
		task2.setEndDateTime(null);
		task2.setPrerequisite(null);
		
		task3= new Task();
		task3.setName("Tâche 1");
		task3.setDescription("Description de la tâche");
		task3.setNote("Ceci est la tâche 2 de test");
		task3.setPriority(5);
		task3.setCategory("Famille");
		task3.setProject(null);
		task3.setState(StateType.NOT_STARTED);
		task3.setResources(null);
		task3.setEstimatedTime(new Duration(0));
		task3.setDueDateTime(new DateTime(0));
		task3.setBeginningDateTime(null);
		task3.setEndDateTime(null);
		task3.setPrerequisite(null);
		
		task4= new Task();
		
		
	}
	
	
	@Test
	public void ConstructorTest() {
		 
		newTask();
		
		assertEquals(task4.getName(), "");
		assertEquals(task4.getDescription(),"");
		assertEquals(task4.getNote(),"");
		assertTrue(task4.getPriority()==0);
		assertNull(task4.getCategory());
		assertNull(task4.getProject());
		assertEquals(task4.getState(),StateType.NOT_STARTED);
		assertNull(task4.getResources());
		assertEquals(task4.getDueDateTime(),new DateTime(0));
		assertEquals(task4.getEstimatedTime(),new Duration(0));
		assertEquals(task4.getDueBeginningDateTime(),new DateTime(0));
		assertNull(task4.getBeginningDateTime());
		assertNull(task4.getEndDateTime());
		assertNull(task4.getPrerequisite());
	}

}
