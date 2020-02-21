package com.neotec.todo.gui;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class TaskScreen {
	private Label lblId;
	private Text txtId;
	private Label lblName;
	private Text txtName;
	private Label lblDescription;
	private Text txtDescription;
	private Label lblNote;
	private Text txtNote;
	private Label lblPriority;
	private Text txtPriority;
	private Label lblState;
	private Label lblProject;
	private Text txtProject;
	private Label lblCategory;
	private Combo cbCategory;
	private Label lblEstimatedTime;
	private Text txtEstimatedTime;
	private Label lblDueDate;
	private Text txtDueDate;
	private Label lblBeginningDate;
	private Text txtBeginningDate;
	private Label lblEndDate;
	private Text txtEndDate;
	private Label lblPrerequisites;
	private Combo cbPrerequisites;
	private Combo cbState;
	private Combo cbResources;


	private int width=650;
	private int leftMargin=150;
	private int rightMargin=600;
	
	private Button button1;
	private Button button2;
	
	final int NEW=0;
	final int LIST=1;
	
	boolean Editable=false;
			
	
	
	final private Shell shell;
	
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TaskScreen(Shell parentShell, int style, int mode) {
//		super(parentShell,0);
		shell= new Shell(parentShell);
//		shell.setSize(650,400);
//		shell.setLocation(parentShell.getLocation());
		
		shell.setLayout(new FormLayout());
		
		if(style==LIST) Editable=false; else Editable=true;
		
		if(style!=NEW){
		txtId = new Text(shell, SWT.BORDER);
		txtId.setEnabled(false);
		FormData fd_txtId = new FormData();
		fd_txtId.left = new FormAttachment(0,leftMargin );
		fd_txtId.top = new FormAttachment(0, 10);
		fd_txtId.right = new FormAttachment(0, leftMargin+30);
		fd_txtId.bottom = new FormAttachment(0,35);
		txtId.setLayoutData(fd_txtId);
		lblId = new Label(shell, SWT.NONE);
		FormData fd_LblId = new FormData();
		fd_LblId.right = new FormAttachment(txtId, -10, SWT.LEFT);
		fd_LblId.bottom = new FormAttachment(txtId, -5, SWT.BOTTOM);
		lblId.setLayoutData(fd_LblId);
		lblId.setText(ToDo.messages.getString("Id") + ":");
		}
		
		txtName = new Text(shell, SWT.BORDER);
		txtName.setEnabled(Editable);
		FormData fd_Name = new FormData();
		fd_Name.left = new FormAttachment(0, leftMargin);
 		fd_Name.top = new FormAttachment(0, 40);
		fd_Name.right = new FormAttachment(0,rightMargin);
		fd_Name.bottom = new FormAttachment(0, 65);
		txtName.setLayoutData(fd_Name);
		lblName = new Label(shell, SWT.NONE);
		FormData fd_LblName = new FormData();
		fd_LblName.right = new FormAttachment(txtName, -10, SWT.LEFT);
		fd_LblName.bottom = new FormAttachment(txtName,-5, SWT.BOTTOM);
		lblName.setLayoutData(fd_LblName);
		lblName.setText(ToDo.messages.getString("Name") + ":");
		
			
		txtDescription = new Text(shell, SWT.BORDER);
		txtDescription.setEnabled(Editable);
		FormData fd_Description = new FormData();
		fd_Description.left = new FormAttachment(0 ,leftMargin);
		fd_Description.top = new FormAttachment(0,70);
		fd_Description.right = new FormAttachment(0, rightMargin);
		fd_Description.bottom = new FormAttachment(0, 95);
		txtDescription.setLayoutData(fd_Description);
		lblDescription = new Label(shell, SWT.NONE);
		FormData fd_LblDescription = new FormData();
		fd_LblDescription.right = new FormAttachment(txtDescription,-10, SWT.LEFT);
		fd_LblDescription.bottom = new FormAttachment(txtDescription, -5, SWT.BOTTOM);
		lblDescription.setLayoutData(fd_LblDescription);
		lblDescription.setText(ToDo.messages.getString("Description") + ":");
		
			
		txtNote = new Text(shell, SWT.BORDER | SWT.MULTI);
		txtNote.setEnabled(Editable);
		FormData fd_Note = new FormData();
		fd_Note.left = new FormAttachment(0, leftMargin);
		fd_Note.top = new FormAttachment(0, 100);
		fd_Note.right = new FormAttachment(0,rightMargin);
		fd_Note.bottom = new FormAttachment(0, 175);
		txtNote.setLayoutData(fd_Note);
		lblNote = new Label(shell, SWT.NONE);
		FormData fd_LblNote = new FormData();
		fd_LblNote.right = new FormAttachment(txtNote, -10, SWT.LEFT);
		fd_LblNote.bottom = new FormAttachment(txtNote,-5, SWT.BOTTOM);
		lblNote.setLayoutData(fd_LblNote);
		lblNote.setText(ToDo.messages.getString("Notes") + ":");
		
		cbCategory = new Combo(shell, SWT.NONE | SWT.READ_ONLY);
		cbCategory.setEnabled(Editable);
		FormData fd_CbCategory = new FormData();
		fd_CbCategory.left = new FormAttachment(0, leftMargin);
		fd_CbCategory.top = new FormAttachment(0, 180);
		fd_CbCategory.right = new FormAttachment(0, leftMargin+150);
		fd_CbCategory.bottom = new FormAttachment(0, 205);
		cbCategory.setLayoutData(fd_CbCategory);
		lblCategory = new Label(shell, SWT.NONE);
		FormData fd_LblCategory = new FormData();
		fd_LblCategory.right = new FormAttachment(cbCategory, -10, SWT.LEFT);
		fd_LblCategory.bottom = new FormAttachment(cbCategory, -5, SWT.BOTTOM);
		lblCategory.setLayoutData(fd_LblCategory);
		lblCategory.setText(ToDo.messages.getString("Category") + ":");
		
		cbCategory.add("Famille");
		cbCategory.add("Travail");
		
		
		txtPriority = new Text(shell, SWT.BORDER);
		txtPriority.setEnabled(Editable);
		FormData fd_Priority = new FormData();
		fd_Priority.left = new FormAttachment(0, leftMargin+220);
		fd_Priority.top = new FormAttachment(0, 180);
		fd_Priority.right = new FormAttachment(0, leftMargin+250);
		fd_Priority.bottom = new FormAttachment(0, 205);
		txtPriority.setLayoutData(fd_Priority);
		lblPriority = new Label(shell, SWT.NONE);
		FormData fd_LblPriority = new FormData();
		fd_LblPriority.right = new FormAttachment(txtPriority, -10, SWT.LEFT);
		fd_LblPriority.bottom = new FormAttachment(txtPriority, -5, SWT.BOTTOM);
		lblPriority.setLayoutData(fd_LblPriority);
		lblPriority.setText(ToDo.messages.getString("Priority") + ":");
		
		
		
		cbState = new Combo(shell, SWT.NONE | SWT.READ_ONLY);
		cbState.setEnabled(false);
		FormData fd_CbState = new FormData();
		fd_CbState.left = new FormAttachment(0, rightMargin-150);
		fd_CbState.top = new FormAttachment(0, 180);
		fd_CbState.right = new FormAttachment(0, rightMargin);
		fd_CbState.bottom = new FormAttachment(0, 205);
		cbState.setLayoutData(fd_CbState);
		lblState = new Label(shell, SWT.NONE);
		FormData fd_LblState = new FormData();
		fd_LblState.right = new FormAttachment(cbState, -10, SWT.LEFT);
		fd_LblState.bottom = new FormAttachment(cbState, -5, SWT.BOTTOM);
		lblState.setLayoutData(fd_LblState);
		lblState.setText(ToDo.messages.getString("State") + ":");
		

	
		Label ancre1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		FormData fd_ancre1 = new FormData();
		fd_ancre1.left = new FormAttachment(0,0);
		fd_ancre1.top =  new FormAttachment(0,210);
		fd_ancre1.right = new FormAttachment(0,width);
		fd_ancre1.bottom = fd_ancre1.top;
		ancre1.setLayoutData(fd_ancre1);
		

		
		if((mode & 1) != 0 ){
			fd_ancre1.top = fd_ancre1.bottom=new FormAttachment (0,235);
					
			txtProject = new Text(shell, SWT.BORDER);
			txtProject.setEnabled(Editable);
			FormData fd_Project = new FormData();
			fd_Project.left = new FormAttachment(0,leftMargin);
			fd_Project.top = new FormAttachment(0,210);
			fd_Project.right = new FormAttachment(0, rightMargin);
			fd_Project.bottom = new FormAttachment(0,235);
			txtProject.setLayoutData(fd_Project);
			lblProject = new Label(shell, SWT.NONE);
			FormData fd_LblProject = new FormData();
			fd_LblProject.right = new FormAttachment(txtProject, -10, SWT.LEFT);
			fd_LblProject.bottom = new FormAttachment(txtProject, -5, SWT.BOTTOM);
			lblProject.setLayoutData(fd_LblProject);
			lblProject.setText(ToDo.messages.getString("Project") + ":");
				
		}
	
		
		
		txtEstimatedTime = new Text(shell, SWT.BORDER);
		txtEstimatedTime.setEnabled(Editable);
		FormData fd_EstimatedTime = new FormData();
		fd_EstimatedTime.left = new FormAttachment(0,leftMargin);
		fd_EstimatedTime.top = new FormAttachment(ancre1, 10,SWT.TOP);
		fd_EstimatedTime.right = new FormAttachment(0, leftMargin+150);
		fd_EstimatedTime.bottom = new FormAttachment(ancre1, 35, SWT.TOP);
		txtEstimatedTime.setLayoutData(fd_EstimatedTime);
		lblEstimatedTime = new Label(shell, SWT.CENTER);
		FormData fd_LblEstimatedTime = new FormData();
		fd_LblEstimatedTime.right = new FormAttachment(txtEstimatedTime, -10, SWT.LEFT);
		fd_LblEstimatedTime.bottom = new FormAttachment(txtEstimatedTime, -5, SWT.BOTTOM);
		lblEstimatedTime.setLayoutData(fd_LblEstimatedTime);
		lblEstimatedTime.setText(ToDo.messages.getString("EstimatedTime") + ":");
				
		txtDueDate = new Text(shell, SWT.BORDER);
		txtDueDate.setEnabled(Editable);
		FormData fd_DueDate = new FormData();
		fd_DueDate.left = new FormAttachment(0,rightMargin-150);
		fd_DueDate.top = new FormAttachment(ancre1, 10, SWT.TOP);
		fd_DueDate.right = new FormAttachment(0,rightMargin);
		fd_DueDate.bottom = new FormAttachment(ancre1, 35, SWT.TOP);
		txtDueDate.setLayoutData(fd_DueDate);
		lblDueDate = new Label(shell, SWT.NONE);
		FormData fd_LblDueDate = new FormData();
		fd_LblDueDate.right = new FormAttachment(txtDueDate, -10, SWT.LEFT);
		fd_LblDueDate.bottom = new FormAttachment(txtDueDate, -5, SWT.BOTTOM);
		lblDueDate.setLayoutData(fd_LblDueDate);
		lblDueDate.setText(ToDo.messages.getString("DueDateTime") + ":" );
		
		
		txtBeginningDate = new Text(shell, SWT.BORDER);
		txtBeginningDate.setEnabled(false);
		FormData fd_BeginningDate = new FormData();
		fd_BeginningDate.left = new FormAttachment(0,leftMargin);
		fd_BeginningDate.top = new FormAttachment(ancre1, 40, SWT.TOP);
		fd_BeginningDate.right = new FormAttachment(0, leftMargin + 150);
		fd_BeginningDate.bottom = new FormAttachment(ancre1, 65, SWT.TOP);
		txtBeginningDate.setLayoutData(fd_BeginningDate);
		lblBeginningDate = new Label(shell, SWT.NONE);
		FormData fd_LblBeginningDate = new FormData();
		fd_LblBeginningDate.right = new FormAttachment(txtBeginningDate, -10, SWT.LEFT);
		fd_LblBeginningDate.bottom = new FormAttachment(txtBeginningDate, -5, SWT.BOTTOM);
		lblBeginningDate.setLayoutData(fd_LblBeginningDate);
		lblBeginningDate.setText(ToDo.messages.getString("BeginningDateTime") + ":");
		
		
		txtEndDate = new Text(shell, SWT.BORDER);
		txtEndDate.setEnabled(false);
		FormData fd_EndDate = new FormData();
		fd_EndDate.left = new FormAttachment(0, rightMargin-150);
		fd_EndDate.top = new FormAttachment(ancre1, 40, SWT.TOP);
		fd_EndDate.right = new FormAttachment(0, rightMargin);
		fd_EndDate.bottom = new FormAttachment(ancre1, 65, SWT.TOP);
		txtEndDate.setLayoutData(fd_EndDate);
		lblEndDate = new Label(shell, SWT.NONE);
		FormData fd_LblEndDate = new FormData();
		fd_LblEndDate.right = new FormAttachment(txtEndDate,-10, SWT.LEFT);
		fd_LblEndDate.bottom = new FormAttachment(txtEndDate, -5, SWT.BOTTOM);
		lblEndDate.setLayoutData(fd_LblEndDate);
		lblEndDate.setText(ToDo.messages.getString("EndDateTime") + ":");
		
		Label ancre2 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		FormData fd_ancre2 = new FormData();
		fd_ancre2.left = new FormAttachment(0,0);
		fd_ancre2.top =  new FormAttachment(ancre1, 70, SWT.TOP);
		fd_ancre2.right = new FormAttachment(0,width);
		fd_ancre2.bottom = fd_ancre2.top;
		ancre2.setLayoutData(fd_ancre2);
		
		if((mode & 2 ) != 0){

			fd_ancre2.top = fd_ancre2.bottom= new FormAttachment(ancre1,105, SWT.TOP);
		
		
		
			cbPrerequisites = new Combo(shell, SWT.NONE| SWT.READ_ONLY);
			cbPrerequisites.setEnabled(Editable);
			FormData fd_cbPrerequisites = new FormData();
			fd_cbPrerequisites.left = new FormAttachment(0, leftMargin);
			fd_cbPrerequisites.top = new FormAttachment(ancre1, 70, SWT.TOP);
			fd_cbPrerequisites.right = new FormAttachment(0, leftMargin+170);
			fd_cbPrerequisites.bottom = new FormAttachment(ancre1, 95, SWT.TOP);
			cbPrerequisites.setLayoutData(fd_cbPrerequisites);
			lblPrerequisites = new Label(shell, SWT.NONE);
			FormData fd_lblPrerequisites = new FormData();
			fd_lblPrerequisites.right = new FormAttachment(cbPrerequisites, -10, SWT.LEFT);
			fd_lblPrerequisites.bottom = new FormAttachment(cbPrerequisites,-5, SWT.BOTTOM);
			lblPrerequisites.setLayoutData(fd_lblPrerequisites);
			lblPrerequisites.setText(ToDo.messages.getString("Prerequisites") + ":");
		
			cbResources = new Combo(shell, SWT.NONE);
			cbResources.setEnabled(Editable);
			FormData fd_cbResources = new FormData();
			fd_cbResources.left = new FormAttachment(0,rightMargin-170);
			fd_cbResources.top = new FormAttachment(ancre1, 70, SWT.TOP);
			fd_cbResources.right = new FormAttachment(0, rightMargin);
			fd_cbResources.bottom = new FormAttachment(ancre1, 95, SWT.TOP);
			cbResources.setLayoutData(fd_cbResources);
			Label lblResources = new Label(shell, SWT.NONE);
			FormData fd_lblResources = new FormData();
			fd_lblResources.right = new FormAttachment(cbResources, -10, SWT.LEFT);
			fd_lblResources.bottom = new FormAttachment(cbResources, -5, SWT.BOTTOM);
			lblResources.setLayoutData(fd_lblResources);
			lblResources.setText(ToDo.messages.getString("Resources") + ":");
		}
		
		switch (style){
		
		case LIST:{

			shell.setText(ToDo.messages.getString("Task"));
			
			Button btnReturn = new Button(shell, SWT.NONE);
			button1=btnReturn;
			btnReturn.setText(ToDo.messages.getString("Return"));
			FormData fd_btnReturn = new FormData();
			fd_btnReturn.bottom = new FormAttachment(ancre2, 40, SWT.TOP);
			fd_btnReturn.right = new FormAttachment(0, rightMargin);
			btnReturn.setLayoutData(fd_btnReturn);
			btnReturn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.close();
					shell.dispose();
				}
			});
			
			Button btnNext = new Button(shell, SWT.NONE);
			button1=btnNext;
			btnNext.setText(ToDo.messages.getString("Next"));
			FormData fd_btnNext = new FormData();
			fd_btnNext.bottom = new FormAttachment(ancre2, 40, SWT.TOP);
			fd_btnNext.right = new FormAttachment(btnReturn, -6);
			btnNext.setLayoutData(fd_btnNext);
			btnNext.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});

			Button btnPrevious = new Button(shell, SWT.NONE);
			button2=btnPrevious;
			btnPrevious.setText(ToDo.messages.getString("Previous"));
			FormData fd_btnPrevious = new FormData();
			fd_btnPrevious.bottom = new FormAttachment(btnNext, 0, SWT.BOTTOM);
			fd_btnPrevious.right = new FormAttachment(btnNext, -6);
			btnPrevious.setLayoutData(fd_btnPrevious);
			btnPrevious.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			
			Button btnBegin = new Button(shell, SWT.NONE);
			button1=btnBegin;
			btnBegin.setText(ToDo.messages.getString("Begin"));
			FormData fd_btnBegin = new FormData();
			fd_btnBegin.bottom = new FormAttachment(ancre2, 40, SWT.TOP);
			fd_btnBegin.right = new FormAttachment(0, leftMargin);
			btnBegin.setLayoutData(fd_btnBegin);
			btnBegin.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			
			Button btnTerminated = new Button(shell, SWT.NONE);
			button1=btnTerminated;
			btnTerminated.setText(ToDo.messages.getString("Terminated"));
			FormData fd_btnTerminated = new FormData();
			fd_btnTerminated.bottom = new FormAttachment(ancre2, 40, SWT.TOP);
			fd_btnTerminated.left = new FormAttachment(btnBegin, 6, SWT.RIGHT);
			btnTerminated.setLayoutData(fd_btnTerminated);
			btnTerminated.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				
				}
			});
						
			break;
			}
		
		
			

		case NEW:{
			shell.setText(ToDo.messages.getString("NewTask"));
			Button btnEnter = new Button(shell, SWT.NONE);
			button1=btnEnter;
			btnEnter.setText(ToDo.messages.getString("Enter"));
			FormData fd_btnEnter = new FormData();
			fd_btnEnter.bottom = new FormAttachment(ancre2, 40, SWT.TOP);
			fd_btnEnter.right = new FormAttachment(0, rightMargin);
			btnEnter.setLayoutData(fd_btnEnter);
			btnEnter.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
		
	
		
			Button btnCancel = new Button(shell, SWT.NONE);
			button2=btnCancel;
			btnCancel.setText(ToDo.messages.getString("Cancel"));
			FormData fd_btnCancel = new FormData();
			fd_btnCancel.bottom = new FormAttachment(btnEnter, 0, SWT.BOTTOM);
			fd_btnCancel.right = new FormAttachment(btnEnter, -6);
			btnCancel.setLayoutData(fd_btnCancel);
			btnCancel.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.close();
					shell.dispose();
				}
			});
		}
	}
		
		Label ancre3 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		FormData fd_ancre3 = new FormData();
		fd_ancre3.left = new FormAttachment(0,0);
		fd_ancre3.top =  new FormAttachment(ancre2, 70, SWT.TOP);
		fd_ancre3.right = new FormAttachment(0,width);
		fd_ancre3.bottom = fd_ancre3.top;
		ancre3.setLayoutData(fd_ancre3);
	
		
			
		shell.setTabList(new Control[]{txtName, txtDescription, txtNote, cbCategory, txtPriority, cbState, txtEstimatedTime, txtDueDate, button1, button2 });
		
		shell.pack();
		shell.open();
	}
}
