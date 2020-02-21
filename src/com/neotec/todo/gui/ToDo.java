package com.neotec.todo.gui;


import com.neotec.todo.ToDoProperties;
import com.neotec.todo.controler.*;

import java.io.*;
import java.util.*;

import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;


import com.neotec.todo.controler.ToDoList;
import com.neotec.todo.model.DateUtil;

public class ToDo {


   static	public ToDoList theList=null;
   static public ResourceBundle messages = ResourceBundle.getBundle("com.neotec.todo.gui.messages");
	
	
	static  public String applicationName="ToDo";
	static	public String version="1.0";

	
	static File taskFile=null;
	static String taskFileName=null;
	
	static boolean isModified=false;

	
	static Display display;
	static Shell mainShell;
	
	static private Table table;
	
    class TableColumnHeader {
    	public String Header;
    	public int size;
    	public TableColumnHeader(String Header, int size){
    		this.Header=Header;
    		this.size=size;
    	}
    }
	
	TableColumnHeader columnHeaders[] = {
			 new TableColumnHeader(messages.getString("Id").substring(0,2),40),
			 new TableColumnHeader(messages.getString("Name"),500),
			 new TableColumnHeader(messages.getString("State"),120),
			 new TableColumnHeader(messages.getString("Priority").substring(0, 2),40),
			 new TableColumnHeader(messages.getString("EstimatedTime"),150),
			 new TableColumnHeader(messages.getString("DueDateTime"),150)
			};
	

	

     
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		
	/**
	 * Initialize the application	
	 */
		ToDoProperties.Create();

		
		
		    display= new Display();
		    ToDo application=new ToDo();
			mainShell=new Shell(display);
		    application.open();
		    
		    while(!mainShell.isDisposed()){
				if(!display.readAndDispatch())
					display.sleep();
			}
			display.dispose();
		}
		    
		
	

	/**
	 * Open the window.
	 */
	public void  open() {

		
		
		mainShell.setLayout(new FillLayout());
		mainShell.addShellListener(new ShellAdapter() {
			public void shellClosed(ShellEvent e) {
				e.doit = close();
			}
		});
		mainShell.setText(messages.getString("Title_bar"));
		MenuBar();
		
		
		table = new Table(mainShell, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);	
//		table.setMenu(createPopUpMenu());	
		table.addSelectionListener(new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) {
				TableItem[] items = table.getSelection();
				if (items.length > 0) TaskGest.list(theList,theList.getTasks().getById(Integer.parseInt(items[0].getText(0))));
			}
		});
		
		
		
		for(int i = 0; i < columnHeaders.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(columnHeaders[i].Header);
			column.setWidth(columnHeaders[i].size);
			column.setAlignment(SWT.LEFT);
			final int columnIndex = i;
//			column.addSelectionListener(new SelectionAdapter() {		
//				public void widgetSelected(SelectionEvent e) {
//					sort(columnIndex);
//				}
//			});
		}


		mainShell.setSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT).x, 300);	
		mainShell.open();
	
	}		
	
	




	public boolean close(){
		if(isModified)
			return(ToDoWrite.questionUser(mainShell, ToDo.messages.getString("ConfirmClose")));
		else
			return true;
	}


	public void MenuBar(){
		
		Menu menuBar = new Menu(mainShell, SWT.BAR);
		mainShell.setMenuBar(menuBar);
		
		//create each header and subMenu for the menuBar
		createFileMenu(menuBar);
		createTaskMenu(menuBar);
		createEditMenu(menuBar);
		createSearchMenu(menuBar);
		createHelpMenu(menuBar);
				
	}
	




private void createHelpMenu(Menu menuBar) {
		// TODO Auto-generated method stub
		
	}




private void createSearchMenu(Menu menuBar) {
		// TODO Auto-generated method stub
		
	}




private void createEditMenu(Menu menuBar) {
		// TODO Auto-generated method stub
		
	}




/**
 * Creates all the items located in the File submenu and
 * associate all the menu items with their appropriate
 * functions.
 *
 * @param	menuBar Menu
 *				the <code>Menu</code> that file contain
 *				the File submenu.
 */

	private void createFileMenu(Menu menuBar){
		
			
		//File menu.
		MenuItem item = new MenuItem(menuBar, SWT.CASCADE);
		item.setText(messages.getString("File_menu_title"));
		Menu menu = new Menu(mainShell, SWT.DROP_DOWN);
		item.setMenu(menu);
		/** 
		 * Adds a listener to handle enabling and disabling 
		 * some items in the Edit submenu.
		 */
	menu.addMenuListener(new MenuAdapter() {
			public void menuShown(MenuEvent e) {
				Menu menu = (Menu)e.widget;
				MenuItem[] items = menu.getItems();
				items[0].setEnabled(theList==null); // new 
				items[1].setEnabled(theList==null); // open
				items[2].setEnabled(( theList != null) && (taskFile != null) && isModified); // save
				items[3].setEnabled((theList!= null ) && isModified); // save as
				items[5].setEnabled((theList!=null)); //close
			}
		});

		// Create the menu frame
		final MenuItem newTodoList = new MenuItem(menu, SWT.NONE);
		final MenuItem openTodoList = new MenuItem(menu, SWT.NONE);
		final MenuItem saveTodoList = new MenuItem(menu, SWT.NONE);
		final MenuItem saveAsTodoList = new MenuItem(menu, SWT.NONE);
		final MenuItem sep1= new MenuItem(menu, SWT.SEPARATOR);
		final MenuItem close= new MenuItem(menu, SWT.NONE);
		final MenuItem exit= new MenuItem(menu, SWT.NONE);
	
		//File -> New Todo List

		newTodoList.setText(messages.getString("New_todo_list"));
		newTodoList.setAccelerator(SWT.MOD1 + 'B');
		newTodoList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (theList==null) {
 					theList=new ToDoList();
 					openTodoList.setEnabled(false);
				}
			}
		});

		//File -> Open

		openTodoList.setText(messages.getString("Open_todo_list"));
		openTodoList.setAccelerator(SWT.MOD1 + 'O');
		openTodoList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (theList==null) {
					FileDialog fd = new FileDialog(mainShell, SWT.OPEN);
					fd.setText("Open");
					fd.setFilterPath(ToDoProperties.get("homepath"));
					String[] filterExt = { "*.xml", "*.*" };
					fd.setFilterExtensions(filterExt);
					String selected = fd.open();
					openTodoList(selected);
					drawTable(theList);
				
				}
			}
		});

		//File -> Save.
		saveTodoList.setText(messages.getString("Save_todo_list"));
		saveTodoList.setAccelerator(SWT.MOD1 + 'S');
		saveTodoList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
		
				saveAs(taskFileName);

			}
		});
		
		//File -> Save As.
		saveAsTodoList.setText(messages.getString("Save_todo_list_as"));
		saveAsTodoList.setAccelerator(SWT.MOD1 + 'A');
		saveAsTodoList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(mainShell, SWT.SAVE);
				fd.setText("Save");
				fd.setFilterPath(ToDoProperties.get("homepath"));
				String[] filterExt = { "*.xml", "*.*" };
				fd.setFilterExtensions(filterExt);
				String selected = fd.open();

				saveAs(selected);
			}
		 });
			

			


		//File -> Close.

		close.setText(messages.getString("Close"));
		close.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				mainShell.close();
			}
		});
		
		//File -> Exit.
		exit.setText(messages.getString("Exit"));
		exit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				mainShell.close();
			}
		});
	}
		


	private void createTaskMenu(Menu menuBar) {

		MenuItem item = new MenuItem(menuBar, SWT.CASCADE);
		item.setText(messages.getString("Task_menu_title"));
		Menu menu = new Menu(mainShell, SWT.DROP_DOWN);
		item.setMenu(menu);
		menu.addMenuListener(new MenuAdapter() {
			public void menuShown(MenuEvent e) {
				Menu menu = (Menu)e.widget;
				MenuItem[] items = menu.getItems();
				items[0].setEnabled(theList!=null);
				items[1].setEnabled( theList != null); // save
				items[3].setEnabled( theList != null ); // save as
			}
		});
		
		
		MenuItem subItem = new MenuItem(menu, SWT.NONE);
		subItem.setText(messages.getString("NewTask"));
		subItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (theList!=null) {
					TaskGest.initialize(mainShell);
					TaskGest.create(theList);
			 }
		}
		});
		
		subItem = new MenuItem(menu, SWT.NONE);
		subItem.setText(messages.getString("TaskList"));
		subItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (theList!=null) {
 					ToDo.listTask();
			 }
			}	
	});	

		subItem = new MenuItem(menu, SWT.SEPARATOR);
		subItem = new MenuItem(menu, SWT.NONE);
		subItem.setText(messages.getString("Categories"));
		
	}

	
	/**
	 * called by the gui.
	 * 
	 * @param task
	 */
	public static void newTask(Task task){
		if(task!=null){
			theList.put(task);
			addTableTask(task);
			isModified=true;
		}
	}
	
	
		
	private static void drawTable(ToDoList list){
		
		table.removeAll();
		Iterator<Task> it=list.getTasks().byIdIterator();
		while (it.hasNext()){
			addTableTask(it.next());			
		}
	}
		
		
	private static void addTableTask(Task task) {
        String line[]=new String[6];
        boolean exist=false;
        TableItem item=null;
        
        line[0]=((Integer)task.getId()).toString();
        line[1]=task.getName();
//        line[2]=messages.getString(task.getState().toString());
        line[2]=messages.getString(task.getState().value());
        line[3]=((Integer)task.getPriority()).toString();
        line[4]=DateUtil.GUIDurationFormatter(task.getEstimatedTime());
        line[5]=DateUtil.GUIDateTimeFormatter(task.getDueDateTime());
        
        exist=false;
        for (int i=0; i<table.getItemCount(); i++){
        	item=table.getItem(i);
           	if(item.getText().equals(((Integer)task.getId()).toString())){
        		exist=true;
        		break;
        	}
        }
        if(!exist) item = new TableItem(table, SWT.NONE);
        
		if(task.getPriority() > 5)
			item.setForeground(new Color(display, 255,0,0));
		if(task.getDueBeginningDateTime().isBeforeNow())
			item.setForeground(new Color(display,0,0,255));
		item.setText(line);
//		if(task.getPriority() > 6) 


		
	}


/**
 * Préférence
 * 
 *  la saisie des  tâches s'enchaine ou on ressort à chaque saisie
 *  
 * 
 */


	protected static void listTask() {
		TaskGest.initialize(mainShell);
		Iterator<Task> it=theList.getTasks().byIdIterator();
		if(it.hasNext())
		TaskGest.list(theList,it.next());
	}




	protected void openTodoList(String fileName) {
		theList=new ToDoList();
		if(fileName!=null)
			try {
				theList.read(fileName, "XML");
				taskFileName=fileName;
			} catch (IOException e) {
					e.printStackTrace();
			}
	
}



	protected void saveAs(String fileName) {

	theList.write(fileName,  "XML");
	isModified=false;
}

}



