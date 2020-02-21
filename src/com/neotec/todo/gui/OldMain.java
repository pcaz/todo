package com.neotec.todo.gui;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import com.neotec.todo.ToDoProperties;

public class OldMain {
	
	//static public ResourceBundle messages;
    public static ResourceBundle messages = ResourceBundle.getBundle("com.neotec.todo.gui.messages");
	
	
	public static void main(String[] args) {
		
		ToDoProperties.Create();

		FileInputStream in;	
		
		/**
		* Nationalisation de l'application		
		*/

			
				try
				{
					String fileName= "messages_"+ToDoProperties.get("language")+"_"+ToDoProperties.get("country")+".properties";
					in = new FileInputStream(fileName);
					messages = new PropertyResourceBundle(in);
				}catch(IOException e){
					try {
						in = new FileInputStream("messages_fr_FR.properties");
						messages = new PropertyResourceBundle(in);
					}catch (IOException ex){
						ex.printStackTrace();
					}
				}
		
		
		
		Display display=new Display();
		OldMain todo=new OldMain();
		Shell mainShell=todo.createMainWindow();
		mainShell.open();
		
		while (!mainShell.isDisposed()){
			if (!display.readAndDispatch())
				display.sleep();
			}
		display.dispose();
	
	}
	
	/**
	 * Create contents of the window.
	 */
	protected Shell createMainWindow() {
		
		final Shell shell;
		
		shell = new Shell();
//		shell.setSize(450, 300);
		shell.setText("ToDo");
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("&File");
		
		Menu menu_2 = new Menu(mntmFile);
		mntmFile.setMenu(menu_2);
		
		MenuItem mntmNew = new MenuItem(menu_2, SWT.NONE);
		mntmNew.setText("&New");
		
		MenuItem mntmOpen = new MenuItem(menu_2, SWT.NONE);
		mntmOpen.setText("&Open");
		
		MenuItem mntmSaveAs = new MenuItem(menu_2, SWT.NONE);
		mntmSaveAs.setText("Save &as...");
		
		MenuItem mntmSave = new MenuItem(menu_2, SWT.NONE);
		mntmSave.setText("&Save");
		
		new MenuItem(menu_2, SWT.SEPARATOR);
		
		MenuItem mntmPrintPreview = new MenuItem(menu_2, SWT.NONE);
		mntmPrintPreview.setText("Print pre&view...");
		
		MenuItem mntmPrint = new MenuItem(menu_2, SWT.NONE);
		mntmPrint.setText("&Print");
		
		new MenuItem(menu_2, SWT.SEPARATOR);
		
		MenuItem mntmExit = new MenuItem(menu_2, SWT.NONE);
		mntmExit.setText("E&xit");
		mntmExit.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent arg0){
			shell.close();
			shell.dispose();}
			
		});
		
		MenuItem mntmTask = new MenuItem(menu, SWT.CASCADE);
		mntmTask.setText("&Task");
		
		Menu menu_1 = new Menu(mntmTask);
		mntmTask.setMenu(menu_1);
		
		MenuItem mntmNew_1 = new MenuItem(menu_1, SWT.NONE);
		mntmNew_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
        		new TaskScreen(shell,1,0);     		
					
			}
		});
		mntmNew_1.setText("&New");
		
		MenuItem mntmDelete = new MenuItem(menu_1, SWT.NONE);
		mntmDelete.setText("&Delete");

		return shell;
	}
}

