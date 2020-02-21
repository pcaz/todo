package com.neotec.todo.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

import com.neotec.todo.controler.StateType;
import com.neotec.todo.controler.Task;
import com.neotec.todo.controler.ToDoList;

public class XMLDriver implements IODriver {

	@Override
	public void write(ToDoList todo, String filename) throws IOException {
		
		Iterator<?> it=null;
		

		Element root= new Element("root");
		
		
		Element todolist=new Element("ToDoList");
		Attribute version=new Attribute("version",todo.getVersion());
		todolist.setAttribute(version);
		todolist.setAttribute("version", todo.getVersion());



		
	    // if RecurringTasks exist, write the recurringTask
	    Element RecurringTasks=new Element("RecurringTasks");
		todolist.addContent(RecurringTasks);
		
	    if(todo.getRecurringTasks().size() != 0){
	    	//TODO: not implemented
	    }
	    Element Projects=new Element("Projects");
		todolist.addContent(Projects);
	
		
	    if(todo.getProjects().size()!=0){
	    	//TODO: not implemented
	    }
	    
	    
	    Element Tasks=new Element("Tasks");
		todolist.addContent(Tasks);
	    
	    it=todo.getTasks().byIdIterator();
	    while(it.hasNext()){
	    	Task thisTask=(Task)it.next();
	    	Element elTask=new Element("Task");

	    	elTask.addContent(new Element("Id").addContent(((Integer)thisTask.getId()).toString()));
	    	elTask.addContent(new Element("Name").addContent(thisTask.getName()));
	    	if(thisTask.getDescription()!="")
	    		elTask.addContent(new Element("Description").addContent(thisTask.getDescription()));
	    	if(thisTask.getNote()!="")
	    		elTask.addContent(new Element("Note").addContent(thisTask.getNote()));
	    	elTask.addContent(new Element("Priority").addContent(((Integer)thisTask.getPriority()).toString()));
	    	if(thisTask.getCategory()!=null)
	    		elTask.addContent(new Element("Category").addContent(thisTask.getCategory().toString()));
	    	if(thisTask.getProject()!=null)
	    		elTask.addContent(new Element("Project").addContent(thisTask.getProject().toString()));
	    	elTask.addContent(new Element("State").addContent(thisTask.getState().toString()));
	    	if(thisTask.getResources()!= null)
	    			writeRessouces();
	    	elTask.addContent(new Element("EstimatedTime").addContent(thisTask.getEstimatedTime().toPeriod().toString()));
	    	elTask.addContent(new Element("DueDateTime").addContent(thisTask.getDueDateTime().toString()));
	    	if(thisTask.getBeginningDateTime()!=null)
	    		elTask.addContent(new Element("BeginningDateTime").addContent(thisTask.getBeginningDateTime().toString()));
	    	if(thisTask.getEndDateTime()!=null)
	    		elTask.addContent(new Element("EndDateTime").addContent(thisTask.getEndDateTime().toString()));
	    	if(thisTask.getPrerequisite()!= null)
	    		writePrerequisite();
	    
	    	Tasks.addContent(elTask);
 	
	    }
	    
		root.addContent(todolist);

	    Element categories=new Element("Categories");
	    for(String thisCategory: todo.getCategories()){
	    	categories.addContent(new Element("Category").addContent(thisCategory)); 	
	    }

	    root.addContent(categories);
	    Document ToDoDocument = new Document(root);
	    
		try{
			FileWriter out=new FileWriter(filename);
			try{
				 XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
				 outputter.output(ToDoDocument, out);
			} finally { out.close();}
			
		} catch (IOException e){
			e.printStackTrace();
		}
	
	    		    		    		    	
}
   	    
	private void writePrerequisite() {
		// TODO Auto-generated method stub
		
	}

	private void writeRessouces() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read(String filename,ToDoList thisTodo ) throws IOException {

		Document document = null;
		
		//On crée une instance de SAXBuilder
	      SAXBuilder sxb = new SAXBuilder();
	      
	         //On crée un nouveau document JDOM avec en argument le fichier XML
	         //Le parsing est terminé ;)
	         try {
				document = sxb.build(new File(filename));
			} catch (JDOMException e) {
				e.printStackTrace();
			}
	  
	      
	      Iterator<Element> it=null;
	      
	      //On initialise un nouvel élément racine avec l'élément racine du document.
	      Element root = document.getRootElement();

	      Element ToDoList= root.getChild("ToDoList");
	     
	      Element  recurringTasks= ToDoList.getChild("RecurringTasks");	      
	      it=recurringTasks.getChildren("RecurringTask").iterator();
	      while(it.hasNext()){
	    	  Element courant=it.next();
	    	  System.out.println(courant.getChild("nom").getText()); 	  
	      }
	      
	      Element  projects= ToDoList.getChild("Projects");	      
	      it=projects.getChildren("Project").iterator();
	      while(it.hasNext()){
	    	  Element courant=it.next();
	    	  System.out.println(courant.getChild("nom").getText()); 	  
	      }
	      
	      Element  tasks= ToDoList.getChild("Tasks");	      
	      it=tasks.getChildren("Task").iterator();
	      while(it.hasNext()){
	    	  Element task=it.next();
	          Task thisTask=new Task();
	          thisTask.setId(Integer.decode(task.getChild("Id").getText()));
	    	  thisTask.setName(task.getChild("Name").getText());
	    	  if(task.getChild("Description")!=null)
	    		  thisTask.setDescription(task.getChild("Description").getText());
	    	  else 
	    		  thisTask.setDescription("");
	    	  if(task.getChild("Note")!=null)
	    		  thisTask.setNote(task.getChild("Note").getText());
	    	  else
	    		  thisTask.setNote("");
	    	  thisTask.setPriority(Integer.decode(task.getChild("Priority").getText()));
	    	  if(task.getChild("Category")!= null)
	    		  thisTask.setCategory(task.getChild("Category").getText());
	    	  if(task.getChild("Project") != null)
	    		  thisTask.setProject(Integer.decode(task.getChild("Project").getText()));
	    	  thisTask.setState(StateType.valueOf(task.getChild("State").getText()));
	    	  //TODO: la lecture des ressources n'est pas implémentée.
	    	  thisTask.setEstimatedTime(DurationParser(task.getChild("EstimatedTime").getText()));
	    	  thisTask.setDueDateTime(DateTimeParser(task.getChild("DueDateTime").getText()));
	    	  if(task.getChild("BeginningDateTime")!=null)
	    		  thisTask.setBeginningDateTime(DateTimeParser(task.getChild("BeginningDateTime").getText()));
	    	  if(task.getChild("EndDateTime")!=null)
	    		  thisTask.setEndDateTime(DateTimeParser(task.getChild("EndDateTime").getText()));
	    	  //TODO: la lecture des prérequis n'est pas implémentée
	    	  thisTodo.put(thisTask);
	      }

		return ;
		
	}
	public static final Duration DurationParser(String text){
		 

		  Period period=new Period();
		  
		  PeriodFormatter pf=ISOPeriodFormat.standard();
		  period=Period.parse(text,pf);
		  return period.toStandardDuration();	  
	  }

	public static DateTime DateTimeParser(String text) {

		  DateTimeFormatter dtp=ISODateTimeFormat.dateTimeParser();
		  return(dtp.parseDateTime(text));
	}

}
