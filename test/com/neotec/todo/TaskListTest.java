package com.neotec.todo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.ListIterator;


import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Test;

import com.neotec.todo.controler.StateType;
import com.neotec.todo.controler.Task;
import com.neotec.todo.controler.TaskList;



public class TaskListTest {


	TaskList todo;
	Task task1=null;
	Task task2=null;
	Task task3=null;
	Task task4=null;
	
	Iterator<Task> it2;
	
	private void newTask(){
		
		todo=new TaskList();
		
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
		task3.setEstimatedTime(new Duration(1));
		task3.setDueDateTime(new DateTime(1));
		task3.setBeginningDateTime(null);
		task3.setEndDateTime(null);
		task3.setPrerequisite(null);
		
		task4= new Task();
		
		todo.put(task1);
		todo.put(task2);
		todo.put(task3);
		todo.put(task4);
		
	}
	
	@Test
	public void testById(){
		newTask();
		assertEquals(todo.size(), 4);
		
			it2 = todo.byIdIterator();
			while(it2.hasNext())
			{
				assertTrue( it2.next().getId()==1);
				assertTrue( it2.next().getId()==2);
				assertTrue( it2.next().getId()==3);
				assertTrue( it2.next().getId()==4);
			}
	}
	
	
	@Test
	public void testByName() {	
		
		newTask();

		assertEquals(todo.size(), 4);
		
		it2 = todo.byNameIterator();
		while(it2.hasNext())
		{
			assertTrue( it2.next().getName().equals(""));
			assertTrue( it2.next().getName().equals("Tâche 1"));
			assertTrue( it2.next().getName().equals("Tâche 1"));
			assertTrue( it2.next().getName().equals("Tâche 2"));
		}
		
	}
	@Test
	public void testByDueDateTime(){
		newTask();

		assertEquals(todo.size(), 4);
		
		it2 = todo.byDueDateTimeIterator();
		while(it2.hasNext())
		{
			assertTrue( it2.next().getDueDateTime().equals(new DateTime(0)));
			assertTrue( it2.next().getDueDateTime().equals(new DateTime(1)));
			assertTrue( it2.next().getDueDateTime().equals(new DateTime(2012,12,25,0,0)));
			assertTrue( it2.next().getDueDateTime().equals(new DateTime(2013,1,1,0,0)));


		}
	}
	
	@Test
	public void testByDueBeginningDateTime(){
		newTask();

		assertEquals(todo.size(), 4);
		
		it2 = todo.byDueBeginningDateTimeIterator();
		while(it2.hasNext())
		{
			assertTrue( it2.next().getDueBeginningDateTime().equals(new DateTime(0)));
			assertTrue( it2.next().getDueBeginningDateTime().equals(new DateTime(0)));
			assertTrue( it2.next().getDueBeginningDateTime().equals(new DateTime(2012,12,24,16,0)));
			assertTrue( it2.next().getDueBeginningDateTime().equals(new DateTime(2012,12,31,22,0)));

		}
	}
		@Test
		public void testRemove(){

			newTask();
			assertEquals(todo.size(), 4);
			todo.removeTask(task2);
			
				
			assertEquals(todo.size(), 3);
			
			it2 = todo.byIdIterator();
			while(it2.hasNext())
			{
				assertTrue( it2.next().getId()==1);
				assertTrue( it2.next().getId()==3);
				assertTrue( it2.next().getId()==4);
			}			
		}
		
		@Test
		public void testModify(){
			newTask();
			assertEquals(todo.size(), 4);
			
			task3.setName("Nouvelle Tâche 3");
			task3.setDueDateTime(new DateTime(2016,01,01,0,0));
			task3.setState(StateType.STARTED);
			
			todo.modify(task3);
			
			it2 = todo.byIdIterator();
			while(it2.hasNext())
			{
				assertTrue( it2.next().getName().equals("Tâche 1"));
				assertTrue( it2.next().getName().equals("Tâche 2"));
				assertTrue( it2.next().getName().equals("Nouvelle Tâche 3"));
				assertTrue( it2.next().getName().equals(""));

			}	
			
			while(((ListIterator<Task>)it2).hasPrevious())
			{
				assertTrue( ((ListIterator<Task>) it2).previous().getName().equals(""));
				assertTrue( ((ListIterator<Task>) it2).previous().getName().equals("Nouvelle Tâche 3"));
				assertTrue( ((ListIterator<Task>) it2).previous().getName().equals("Tâche 2"));
				assertTrue( ((ListIterator<Task>) it2).previous().getName().equals("Tâche 1"));
			}
			
			it2=todo.byNameIterator();
			while(it2!=null && it2.hasNext()){
				assertTrue( it2.next().getName().equals(""));
				assertTrue( it2.next().getName().equals("Nouvelle Tâche 3"));
				assertTrue( it2.next().getName().equals("Tâche 1"));
				assertTrue( it2.next().getName().equals("Tâche 2"));
			}

			it2=todo.byDueDateTimeIterator();
			while(it2.hasNext())
			{
				assertTrue( it2.next().getName().equals(""));
				assertTrue( it2.next().getName().equals("Tâche 1"));
				assertTrue( it2.next().getName().equals("Tâche 2"));
				assertTrue( it2.next().getName().equals("Nouvelle Tâche 3"));
			}
			
			it2=todo.byDueBeginningDateTimeIterator();
			while(it2.hasNext())
			{

				assertTrue( it2.next().getName().equals(""));
				assertTrue( it2.next().getName().equals("Tâche 1"));
				assertTrue( it2.next().getName().equals("Tâche 2"));
				assertTrue( it2.next().getName().equals("Nouvelle Tâche 3"));
			}
		
		}


	
			
		
		


}
