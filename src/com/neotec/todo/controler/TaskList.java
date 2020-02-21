package com.neotec.todo.controler;



import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.joda.time.DateTime;


import com.neotec.collection.treemapcontainer.Cell;
import com.neotec.collection.treemapcontainer.NeoTreeMap;
import com.neotec.collection.treemapcontainer.TreeMapContainer;
import com.neotec.todo.ToDoProperties;
import com.neotec.todo.ToDoWrite;





public class TaskList {
/**
 *  valeur de l'identifiant maximum servant de base à la création de nouveaux identifiants
 *  Les identifiants sont croissants mais non sériralisés.  
 */
	
	
	int	modCount=0;
	int byNameIndex=0;
	int size=0;
	int	NextId=0;
	
	
	
	NeoTreeMap<Integer,Task>	byId=null;
	TreeMapContainer<DateTime,Task>    byDueDateTime=null;
	TreeMapContainer<DateTime,Task>	byDueBeginningDateTime=null;
	TreeMapContainer<String,Task>    byName=null;
	
	
	public TaskList(){
		byId=new NeoTreeMap<Integer,Task>();
		byDueDateTime=new TreeMapContainer<DateTime,Task>();
		byDueBeginningDateTime=new TreeMapContainer<DateTime,Task>();
		byName=new TreeMapContainer<String,Task>();
	}
	
	public int size(){
		return size;
	}
	
	public void read(String fileName){
		
	}
/**
 * 
 * Put a new element in the list, if the task is already in the list, the task is modified, else, a new task is add.
 * The nextId is the mas of, nextId and the task Id.
 * 
 * @param task
 */
	public void put(Task task){
		
		/**
		 * if the task is a new one (without Id), just increment the next Id, else, nextId is the max of NextId and the 
		 * task Id 
		 */
		
		if(task.id==0) {
			NextId++; 
			task.id=NextId;
		}
		else
		{
			NextId=task.id>NextId?task.id:NextId;	
		}
		
		/**
		 * 
		 * if the task is already in the list, modify the task in the list
		 */
		if(getById(task.id) != null){
			modify(task);
			return;
		}
		
		/**
		 * else, add a new task.
		 * 
		 */
		modCount++;
		size++;
		byId.put(task.getId(),task);
		byDueDateTime.put(task.getDueDateTime(), task);
		byDueBeginningDateTime.put(task.getDueBeginningDateTime(), task);
		byName.put(task.getName(), task);
		if(ToDoProperties.get("debug")=="true") ToDoWrite.writeDebug("Write\n" + task.toString());
	}
	
	public Task getById(int id){
		return byId.get(id);
	}
	
	public Cell<Task> getByName(String key){
		return byName.getCell(key);
	}
	
	public Cell<Task> getByDueDateTime(DateTime key){
		return byDueDateTime.getCell(key);
	}
	
	public Cell<Task> getByDueBeginningDateTime(DateTime key){
		return byDueBeginningDateTime.getCell(key);
	}
	
	public ListIterator<Task> byIdIterator(){
		return  byId.valueIterator();
	}
	
	public Iterator<Task> byNameIterator(){
		return byName.iterator(); 
    }
	
	public Iterator<Task> byNameIterator(String name){
		return byName.iterator(name); 
    }
	

	public Iterator<Task> byDueDateTimeIterator(){
		return byDueDateTime.iterator();
	}

	public Iterator<Task> byDueBeginningDateTimeIterator(){
		return byDueBeginningDateTime.iterator();
	}
	
	public Iterator<Task> byDueDateTimeIterator(DateTime dt){
		   
			return byDueDateTime.iterator(dt);
		}
	
	public Iterator<Task> byDueBeginningDateTimeIterator(DateTime dt){
		return byDueBeginningDateTime.iterator();
	}
	
	public void removeTask(Task task){

//TODO revoir cette méthode, l'égalité des valeurs pose problème. 		
		Iterator<Task> it;
		Boolean found;
		
		modCount++;
		
		
		this.byId.remove(task.id);
		
		it=this.byNameIterator(task.name);
		found=false;
		while(it.hasNext()){
			Task thisTask=it.next();
			if(task.getId()== thisTask.getId()){
				found=true;
				byName.remove(task.name, thisTask);
				break; // Attention, le remove interdit de poursuivre avec l'itérateur
			}
		}
		if (found==false) throw new IllegalStateException();
		
		it=this.byDueDateTimeIterator(task.dueDateTime);
		found=false;
		while(it.hasNext()){
			Task thisTask=it.next();
			if(task.getId()== thisTask.getId()){
				found=true;
				byDueDateTime.remove(task.getDueDateTime(), thisTask);
				break;  // Attention, le remove interdit de poursuivre avec l'itérateur
			}
		}
		if (found==false) throw new IllegalStateException();
		
		it=this.byDueBeginningDateTimeIterator(task.getDueBeginningDateTime());
		found=false;
		while(it.hasNext()){
			Task thisTask=it.next();
			if(task.getId()== thisTask.getId()){
				found=true;
				byDueBeginningDateTime.remove(task.getDueBeginningDateTime(), thisTask);
				break;  // Attention, le remove interdit de poursuivre avec l'itérateur
			}
		}
		if (found==false) throw new IllegalStateException();

		size--;
	}
	
	public void modify(Task task){
		
		Iterator<Task> it;
		boolean found;
		
		
		if(byId.containsKey(task.id)) 
			 byId.put(task.id,task);
		else
			 throw new NoSuchElementException();

		found=false;
		it=byNameIterator();
		while(it.hasNext()){
				if((it.next()).id==task.id){
					it.remove();
					byName.put(task.name, task);
					found=true;
					break;
				}
			}
		if(found==false) throw new IllegalStateException();
		
		found=false;
		it=byDueDateTimeIterator();
		while(it.hasNext()){
				if((it.next()).id==task.id){
					it.remove();
					byDueDateTime.put(task.dueDateTime, task);
					found=true;
					break;
				}
			}
		if(found==false) throw new IllegalStateException();
		
		
		found=false;
		it=byDueBeginningDateTimeIterator();
		while(it.hasNext()){
				if((it.next()).id==task.id){
					it.remove();
					byDueBeginningDateTime.put(task.getDueBeginningDateTime(), task);
					found=true;
					break;
				}
			}
		if(found==false) throw new IllegalStateException();
		
		
		}
}		
				

	
	