package com.neotec.todo.gui;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.HelpEvent;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;



import com.neotec.todo.ToDoProperties;
import com.neotec.todo.controler.StateType;
import com.neotec.todo.controler.Task;
import com.neotec.todo.controler.ToDoList;
import com.neotec.todo.model.DateUtil;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.MenuItem;




public class TaskGest  {
	private static Label lblId;
	private static Text txtId;
	private static Label lblName;
	private static Text txtName;
	private static Label lblDescription;
	private static Text txtDescription;
	private static Label lblNote;
	private static Text txtNote;
	private static Label lblPriority;
	private static Text txtPriority;
	private static Label lblState;
	private static Combo cbState;
	private static Label lblProject;
	private static Text txtProject;
	private static Label lblCategory;
	private static Combo cbCategory;
	private static Label lblEstimatedTime;
	private static Text txtEstimatedTime;
	private static Label lblDueDate;
	private static Text txtDueDate;
	private static Label lblBeginningDate;
	private static Text txtBeginningDate;
	private static Label lblEndDate;
	private static Text txtEndDate;
	private static Label lblPrerequisites;
	private static Combo cbPrerequisites;
	private static Label lblResources;
	private static Combo cbResources;


	private static int width=650;
	private static int leftMargin=150;
	private static int rightMargin=600;
	
	private static Button button1;
	private static Button button2;
	private static Button button3;
	private static Button button4;
	private static Button button5;
	private static Button button6;
	
	
	final static int NEW=0;
	final static int LIST=1;
	final static int MODIFY=2;
	
	static boolean Editable=false;
		
	
	private static Shell parentShell;
	private static Shell shell;
	private static int mode=0;

	static public Task theTask;
	static public ToDoList theList;
	public static boolean isValid=false; 
	private static Menu menu_1;
	
    
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	private TaskGest(){
		
	}
	
	static public void initialize(Shell parentShell){
		TaskGest.parentShell = parentShell;
				}
	
	static public void create(ToDoList List){
		theList=List;
		theTask=new Task();

		show(NEW);

	}
	
	
	static public void list(ToDoList List, Task task){
		theList=List;
		theTask=task;
		show(LIST);
	}
	
	private static boolean verifyTask(){
		if((txtName.getText() == null) || (txtName.getText().equals(""))){
			ToDoWrite.writeError(shell,ToDo.messages.getString("NameError"));
			txtName.setFocus();
			return false;
		}
		
		if((txtEstimatedTime.getText()==null) || (!DateUtil.verifyDuration(txtEstimatedTime.getText())))
		{
			ToDoWrite.writeError(shell,ToDo.messages.getString("DurationError"));
			txtEstimatedTime.setFocus();
			return false;
		}
		
		if((txtDueDate.getText()==null) || (!DateUtil.verifyDateTime(txtDueDate.getText())))
		{
			ToDoWrite.writeError(shell,ToDo.messages.getString("DateTimeError"));
			txtDueDate.setFocus();
			return false;
		}
		
		return true;
	}
	
	public static void getTask(){

		theTask.setName(txtName.getText());
		theTask.setDescription(txtDescription.getText());
		theTask.setNote(txtNote.getText());
		theTask.setCategory(cbCategory.getText());
		theTask.setPriority(Integer.parseInt(txtPriority.getText()));
		theTask.setState(StateType.getValue(cbState.getSelectionIndex()));
		theTask.setEstimatedTime(DateUtil.GUIDurationParser(txtEstimatedTime.getText()));
		theTask.setDueDateTime(DateUtil.GUIDateTimeParser(txtDueDate.getText()));
		theTask.setBeginningDateTime(DateUtil.GUIDateTimeParser(txtBeginningDate.getText()));
		theTask.setEndDateTime(DateUtil.GUIDateTimeParser(txtEndDate.getText()));

	}
	
	public static void putTask(){
		ToDo.newTask(theTask);
		theTask=new Task();
		txtName.setText("");
		txtDescription.setText("");
		txtNote.setText("");
		cbCategory.select(0);
		txtPriority.setText("0");
		cbState.select(0);
		txtEstimatedTime.setText("");
		txtDueDate.setText("");
		txtBeginningDate.setText("");
		txtEndDate.setText("");
	}
	

	
	/**
	 * @wbp.parser.entryPoint
	 */
	private static void show(int style) {
//		super(parentShell,0);
		shell= new Shell(parentShell);
//		shell.setSize(650,400);
//		shell.setLocation(parentShell.getLocation());
		
		shell.setLayout(new FormLayout());
		
		if(style==LIST) Editable=false; else Editable=true;
		
		Label ancre0 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		FormData fd_ancre0 = new FormData();
		fd_ancre0.left = new FormAttachment(0,0);
		fd_ancre0.top =  new FormAttachment(0,20);
		fd_ancre0.right = new FormAttachment(0,width);
		fd_ancre0.bottom = fd_ancre0.top;
		ancre0.setLayoutData(fd_ancre0);
		
		
		if(style!=NEW){
		txtId = new Text(shell, SWT.BORDER);
		txtId.setText(((Integer)theTask.getId()).toString());
		txtId.setEditable(false);
		FormData fd_txtId = new FormData();
		fd_txtId.left = new FormAttachment(0,leftMargin );
		fd_txtId.top = new FormAttachment(ancre0, 10, SWT.TOP);
		fd_txtId.right = new FormAttachment(0, leftMargin+30);
		fd_txtId.bottom = new FormAttachment(ancre0,35,SWT.TOP);
		txtId.setLayoutData(fd_txtId);
		lblId = new Label(shell, SWT.NONE);
		FormData fd_LblId = new FormData();
		fd_LblId.right = new FormAttachment(txtId, -10, SWT.LEFT);
		fd_LblId.bottom = new FormAttachment(txtId, 15, SWT.TOP);
		lblId.setLayoutData(fd_LblId);
		lblId.setText(ToDo.messages.getString("Id") + ":");
		}
		
		txtName = new Text(shell, SWT.BORDER);
		txtName.setEditable(Editable);
		txtName.setText(theTask.getName());
		FormData fd_Name = new FormData();
		fd_Name.left = new FormAttachment(0, leftMargin);
 		fd_Name.top = new FormAttachment(ancre0, 40,SWT.TOP);
		fd_Name.right = new FormAttachment(0,rightMargin);
		fd_Name.bottom = new FormAttachment(ancre0,65,SWT.TOP);
		txtName.setLayoutData(fd_Name);
		lblName = new Label(shell, SWT.NONE);
		FormData fd_LblName = new FormData();
		fd_LblName.right = new FormAttachment(txtName, -10, SWT.LEFT);
		fd_LblName.bottom = new FormAttachment(txtName,-5, SWT.BOTTOM);
		lblName.setLayoutData(fd_LblName);
		lblName.setText(ToDo.messages.getString("Name") + ":");
		
			
		txtDescription = new Text(shell, SWT.BORDER);
		txtDescription.setEditable(Editable);
		txtDescription.setText(theTask.getDescription());
		FormData fd_Description = new FormData();
		fd_Description.left = new FormAttachment(0 ,leftMargin);
		fd_Description.top = new FormAttachment(ancre0,70, SWT.TOP);
		fd_Description.right = new FormAttachment(0, rightMargin);
		fd_Description.bottom = new FormAttachment(ancre0, 95,SWT.TOP);
		txtDescription.setLayoutData(fd_Description);
		lblDescription = new Label(shell, SWT.NONE);
		FormData fd_LblDescription = new FormData();
		fd_LblDescription.right = new FormAttachment(txtDescription,-10, SWT.LEFT);
		fd_LblDescription.bottom = new FormAttachment(txtDescription, 20, SWT.TOP);
		lblDescription.setLayoutData(fd_LblDescription);
		lblDescription.setText(ToDo.messages.getString("Description") + ":");
		
			
		txtNote = new Text(shell, SWT.BORDER |SWT.V_SCROLL | SWT.MULTI);
		txtNote.setEditable(Editable);
		txtNote.setText(theTask.getNote());
		FormData fd_Note = new FormData();
		fd_Note.left = new FormAttachment(0, leftMargin);
		fd_Note.top = new FormAttachment(ancre0, 100,SWT.TOP);
		fd_Note.right = new FormAttachment(0,rightMargin);
		fd_Note.bottom = new FormAttachment(ancre0, 175,SWT.TOP);
		txtNote.setLayoutData(fd_Note);
		lblNote = new Label(shell, SWT.NONE);
		FormData fd_LblNote = new FormData();
		fd_LblNote.right = new FormAttachment(txtNote, -10, SWT.LEFT);
		fd_LblNote.bottom = new FormAttachment(txtNote,20, SWT.TOP);
		lblNote.setLayoutData(fd_LblNote);
		lblNote.setText(ToDo.messages.getString("Notes") + ":");
		
		cbCategory = new Combo(shell, SWT.NONE | SWT.READ_ONLY);
		cbCategory.setEnabled(Editable);
		for (String cat: theList.getCategories())
			cbCategory.add(cat);
		if(theList.getCategories().indexOf(theTask.getState())>=0)
			cbCategory.select(theList.getCategories().indexOf(theTask.getState()));
		else
			cbCategory.select(0);
		FormData fd_CbCategory = new FormData();
		fd_CbCategory.left = new FormAttachment(0, leftMargin);
		fd_CbCategory.top = new FormAttachment(ancre0, 180, SWT.TOP); 
		fd_CbCategory.right = new FormAttachment(0, leftMargin+150);
		fd_CbCategory.bottom = new FormAttachment(ancre0, 205,SWT.TOP);
		cbCategory.setLayoutData(fd_CbCategory);
		lblCategory = new Label(shell, SWT.NONE);
		FormData fd_LblCategory = new FormData();
		fd_LblCategory.right = new FormAttachment(cbCategory, -10, SWT.LEFT);
		fd_LblCategory.bottom = new FormAttachment(cbCategory, 20, SWT.TOP);
		lblCategory.setLayoutData(fd_LblCategory);
		lblCategory.setText(ToDo.messages.getString("Category") + ":");
		
		
		
		
		txtPriority = new Text(shell, SWT.BORDER);
		txtPriority.setEditable(Editable);
		txtPriority.setText(theTask.getPriority().toString());
		FormData fd_Priority = new FormData();
		fd_Priority.left = new FormAttachment(0, leftMargin+220);
		fd_Priority.top = new FormAttachment(ancre0, 180,SWT.TOP);
		fd_Priority.right = new FormAttachment(0, leftMargin+250);
		fd_Priority.bottom = new FormAttachment(ancre0, 205, SWT.TOP);
		txtPriority.setLayoutData(fd_Priority);
		lblPriority = new Label(shell, SWT.NONE);
		FormData fd_LblPriority = new FormData();
		fd_LblPriority.right = new FormAttachment(txtPriority, -10, SWT.LEFT);
		fd_LblPriority.bottom = new FormAttachment(txtPriority, 20, SWT.TOP);
		lblPriority.setLayoutData(fd_LblPriority);
		lblPriority.setText(ToDo.messages.getString("Priority") + ":");
		
		
		
		cbState = new Combo(shell, SWT.READ_ONLY );
		cbState.setEnabled(false);
//		for (StateType state: StateType.values())
//			cbState.add(ToDo.messages.getString(state.value()));
		cbState.add(ToDo.messages.getString(theTask.getState().value()));
		cbState.select(StateType.getIndex(theTask.getState().value()));
		cbState.setForeground(shell.getForeground());
		FormData fd_CbState = new FormData();
		fd_CbState.left = new FormAttachment(0, rightMargin-150);
		fd_CbState.top = new FormAttachment(ancre0, 180, SWT.TOP);
		fd_CbState.right = new FormAttachment(0, rightMargin);
		fd_CbState.bottom = new FormAttachment(ancre0, 205,SWT.TOP);
		cbState.setLayoutData(fd_CbState);
		lblState = new Label(shell, SWT.NONE);
		FormData fd_LblState = new FormData();
		fd_LblState.right = new FormAttachment(cbState, -10, SWT.LEFT);
		fd_LblState.bottom = new FormAttachment(cbState, 20, SWT.TOP);
		lblState.setLayoutData(fd_LblState);
		lblState.setText(ToDo.messages.getString("State") + ":");
		

		
		Label ancre1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		FormData fd_ancre1 = new FormData();
		fd_ancre1.left = new FormAttachment(0,0);
		fd_ancre1.top =  new FormAttachment(ancre0,210, SWT.TOP);
		fd_ancre1.right = new FormAttachment(0,width);
		fd_ancre1.bottom = fd_ancre1.top;
		ancre1.setLayoutData(fd_ancre1);
		
		
		if((mode & 1) != 0 ){
			fd_ancre1.top = fd_ancre1.bottom=new FormAttachment (ancre0,235,SWT.TOP);
					
			txtProject = new Text(shell, SWT.BORDER);
			txtProject.setEditable(Editable);
			FormData fd_Project = new FormData();
			fd_Project.left = new FormAttachment(0,leftMargin);
			fd_Project.top = new FormAttachment(0,210);
			fd_Project.right = new FormAttachment(0, rightMargin);
			fd_Project.bottom = new FormAttachment(0,235);
			txtProject.setLayoutData(fd_Project);
			lblProject = new Label(shell, SWT.NONE);
			FormData fd_LblProject = new FormData();
			fd_LblProject.right = new FormAttachment(txtProject, -10, SWT.LEFT);
			fd_LblProject.bottom = new FormAttachment(txtProject, 20, SWT.TOP);
			lblProject.setLayoutData(fd_LblProject);
			lblProject.setText(ToDo.messages.getString("Project") + ":");
				
		}
	
		
		
		txtEstimatedTime = new Text(shell, SWT.BORDER);
		txtEstimatedTime.setEditable(Editable);
		if(theTask.getEstimatedTime()!=null && (theTask.getEstimatedTime().getMillis()!=0)){
			txtEstimatedTime.setText(DateUtil.GUIDurationFormatter(theTask.getEstimatedTime()));
		}
		FormData fd_EstimatedTime = new FormData();
		fd_EstimatedTime.left = new FormAttachment(0,leftMargin);
		fd_EstimatedTime.top = new FormAttachment(ancre1, 10,SWT.TOP);
		fd_EstimatedTime.right = new FormAttachment(0, leftMargin+150);
		fd_EstimatedTime.bottom = new FormAttachment(ancre1, 35, SWT.TOP);
		txtEstimatedTime.setLayoutData(fd_EstimatedTime);
		lblEstimatedTime = new Label(shell, SWT.CENTER);
		FormData fd_LblEstimatedTime = new FormData();
		fd_LblEstimatedTime.right = new FormAttachment(txtEstimatedTime, -10, SWT.LEFT);		
		fd_LblEstimatedTime.bottom = new FormAttachment(txtEstimatedTime, 20, SWT.TOP);
		lblEstimatedTime.setLayoutData(fd_LblEstimatedTime);
		lblEstimatedTime.setText(ToDo.messages.getString("EstimatedTime") + ":");
				
		txtDueDate = new Text(shell, SWT.BORDER);
		txtDueDate.setEditable(Editable);
		if(theTask.getDueDateTime()!=null && (theTask.getDueDateTime().getMillis()!=0)){
			txtDueDate.setText(DateUtil.GUIDateTimeFormatter(theTask.getDueDateTime()));
		}
		FormData fd_DueDate = new FormData();
		fd_DueDate.left = new FormAttachment(0,rightMargin-125);
		fd_DueDate.top = new FormAttachment(ancre1, 10, SWT.TOP);
		fd_DueDate.right = new FormAttachment(0,rightMargin);
		fd_DueDate.bottom = new FormAttachment(ancre1, 35, SWT.TOP);
		txtDueDate.setLayoutData(fd_DueDate);
		Button btnCalendar=new Button(shell, SWT.None);
		btnCalendar.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		try {
			btnCalendar.setImage(SWTResourceManager.getImage(Class.forName("com.neotec.todo.gui.TaskGest"), "calendrier-icon.png"));
		} catch (ClassNotFoundException e1) {
			btnCalendar.setImage(null);
		}
		FormData fd_btnCalendar = new FormData();
		fd_btnCalendar.top = new FormAttachment(ancre1, 10, SWT.TOP);
		fd_btnCalendar.right = new FormAttachment(txtDueDate, 0, SWT.LEFT);
		btnCalendar.setLayoutData(fd_btnCalendar);
		btnCalendar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final Shell dialog = new Shell (shell, SWT.DIALOG_TRIM);
			      dialog.setLayout (new GridLayout (3, false));

			      final DateTime calendar = new DateTime (dialog, SWT.CALENDAR | SWT.BORDER);
			      final Text time = new Text(dialog, SWT.BORDER);

			      new Label (dialog, SWT.NONE);
			      Button ok = new Button (dialog, SWT.PUSH);
			      ok.setText ("OK");
			      ok.setLayoutData(new GridData (SWT.FILL, SWT.CENTER, false, false));
			      ok.addSelectionListener (new SelectionAdapter () {
			        public void widgetSelected (SelectionEvent e) {
			        	if(!DateUtil.verifyDateTime(time.getText())){
			        		ToDoWrite.writeError(shell,ToDo.messages.getString("DateTimeError"));
			        		txtDueDate.setFocus();
			        		return;
			        		}
			        	org.joda.time.DateTime tm=DateUtil.GUIDateTimeParser(time.getText());
			        	org.joda.time.DateTime dt=new org.joda.time.DateTime(calendar.getYear(), calendar.getMonth()+1, calendar.getDay(), tm.getHourOfDay(),tm.getMinuteOfHour());
			           	txtDueDate.setText(DateUtil.GUIDateTimeFormatter(dt));
			            dialog.close ();
			        }
			      });
			 
			      dialog.setDefaultButton (ok);
			      dialog.pack ();
			      dialog.open ();
			    }
	
		});
				
		lblDueDate = new Label(shell, SWT.NONE);
		FormData fd_LblDueDate = new FormData();
		fd_LblDueDate.right = new FormAttachment(txtDueDate, -35, SWT.LEFT);
		fd_LblDueDate.bottom = new FormAttachment(txtDueDate, 20, SWT.TOP);
		txtDueDate.setMenu(menu_1);
		lblDueDate.setLayoutData(fd_LblDueDate);
		lblDueDate.setText(ToDo.messages.getString("DueDateTime") + ":" );
				
		txtBeginningDate = new Text(shell, SWT.BORDER);
		txtBeginningDate.setEnabled(false);
		if(theTask.getBeginningDateTime()!=null && (theTask.getBeginningDateTime().getMillis()!=0)){
			txtBeginningDate.setText(DateUtil.GUIDateTimeFormatter(theTask.getBeginningDateTime()));
		}
		FormData fd_BeginningDate = new FormData();
		fd_BeginningDate.left = new FormAttachment(0,leftMargin);
		fd_BeginningDate.top = new FormAttachment(ancre1, 40, SWT.TOP);
		fd_BeginningDate.right = new FormAttachment(0, leftMargin + 150);
		fd_BeginningDate.bottom = new FormAttachment(ancre1, 65, SWT.TOP);
		txtBeginningDate.setLayoutData(fd_BeginningDate);
		lblBeginningDate = new Label(shell, SWT.NONE);
		FormData fd_LblBeginningDate = new FormData();
		fd_LblBeginningDate.right = new FormAttachment(txtBeginningDate, -10, SWT.LEFT);
		fd_LblBeginningDate.bottom = new FormAttachment(txtBeginningDate, 20, SWT.TOP);
		lblBeginningDate.setLayoutData(fd_LblBeginningDate);
		lblBeginningDate.setText(ToDo.messages.getString("BeginningDateTime") + ":");
		
		
		txtEndDate = new Text(shell, SWT.BORDER);
		txtEndDate.setEnabled(false);
		if(theTask.getEndDateTime()!=null && (theTask.getEndDateTime().getMillis()!=0)){
			txtEndDate.setText(DateUtil.GUIDateTimeFormatter(theTask.getEndDateTime()));
		}
		FormData fd_EndDate = new FormData();
		fd_EndDate.left = new FormAttachment(0, rightMargin-150);
		fd_EndDate.top = new FormAttachment(ancre1, 40, SWT.TOP);
		fd_EndDate.right = new FormAttachment(0, rightMargin);
		fd_EndDate.bottom = new FormAttachment(ancre1, 65, SWT.TOP);
		txtEndDate.setLayoutData(fd_EndDate);
		lblEndDate = new Label(shell, SWT.NONE);
		FormData fd_LblEndDate = new FormData();
		fd_LblEndDate.right = new FormAttachment(txtEndDate,-10, SWT.LEFT);
		fd_LblEndDate.bottom = new FormAttachment(txtEndDate, 20, SWT.TOP);
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
			fd_lblPrerequisites.bottom = new FormAttachment(cbPrerequisites,20, SWT.TOP);
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
			lblResources = new Label(shell, SWT.NONE);
			FormData fd_lblResources = new FormData();
			fd_lblResources.right = new FormAttachment(cbResources, -10, SWT.LEFT);
			fd_lblResources.bottom = new FormAttachment(cbResources, 20, SWT.TOP);
			lblResources.setLayoutData(fd_lblResources);
			lblResources.setText(ToDo.messages.getString("Resources") + ":");
		}
		
		switch (style){
		
		case LIST:{

			shell.setText(ToDo.messages.getString("ListTaskTitle"));
			shell.setTabList(null);
			
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
			button3=btnBegin;
			btnBegin.setText(ToDo.messages.getString("Begin"));
			FormData fd_btnBegin = new FormData();
			fd_btnBegin.bottom = new FormAttachment(ancre2, 40, SWT.TOP);
			fd_btnBegin.right = new FormAttachment(0, leftMargin);
			btnBegin.setLayoutData(fd_btnBegin);
			btnBegin.addSelectionListener(new SelectionAdapter(){
			
				@Override
				public void widgetSelected(SelectionEvent e) {
				
				}
			});
				
			
			Button btnTerminated = new Button(shell, SWT.NONE);
			button4=btnTerminated;
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
			
			Button btnModify = new Button(shell, SWT.NONE);
			button5=btnModify;
			btnModify.setText(ToDo.messages.getString("Modify"));
			FormData fd_btnModify = new FormData();
			fd_btnModify.bottom = new FormAttachment(ancre2, 40, SWT.TOP);
			fd_btnModify.left = new FormAttachment(btnTerminated, 6, SWT.RIGHT);
			btnModify.setLayoutData(fd_btnModify);
			btnModify.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.close();
					shell.dispose();
					shell= new Shell(parentShell);
					shell.setLayout(new FormLayout());
					show(MODIFY);
					return;
				}
			});
			
			Button btnDelete = new Button(shell, SWT.NONE);
			button6=btnDelete;
			btnDelete.setText(ToDo.messages.getString("Delete"));
			FormData fd_btnDelete = new FormData();
			fd_btnDelete.bottom = new FormAttachment(ancre2, 40, SWT.TOP);
			fd_btnDelete.left = new FormAttachment(btnModify, 6, SWT.RIGHT);
			btnDelete.setLayoutData(fd_btnDelete);
			btnDelete.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				
				}
			});
						
			break;
			}
		
		
			

		case NEW:
		{
			shell.setText(ToDo.messages.getString("NewTaskTitle"));
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
						if(!verifyTask()) return;
						getTask();
						putTask();
						if(!Boolean.parseBoolean(ToDoProperties.get("manytasks"))){
							shell.close();
							shell.dispose();
						}
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
		break;
		}
		case MODIFY:	
		{
			shell.setText(ToDo.messages.getString("ModifyTaskTitle"));
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
					
						if(!verifyTask()) return;
						getTask();
						putTask();
						shell.close();
						shell.dispose();
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

	
		
//	shell.setTabList(new Control[]{txtName, txtDescription, txtNote, cbCategory, txtPriority, cbState, txtEstimatedTime, txtDueDate, button1, button2 });
	
	shell.pack();
	
	Menu menu = new Menu(shell, SWT.BAR);
	shell.setMenuBar(menu);
	
	MenuItem mntmNavigation = new MenuItem(menu, SWT.CASCADE);
	mntmNavigation.setText("Navigation");
	
	Menu menu_3 = new Menu(mntmNavigation);
	mntmNavigation.setMenu(menu_3);
	
	MenuItem mntmOrder = new MenuItem(menu_3, SWT.CASCADE);
	mntmOrder.setText("Order");
	
	Menu menu_4 = new Menu(mntmOrder);
	mntmOrder.setMenu(menu_4);
	
	MenuItem mntmById = new MenuItem(menu_4, SWT.RADIO);
	mntmById.setText("By Id");
	
	MenuItem mntmByPriority = new MenuItem(menu_4, SWT.RADIO);
	mntmByPriority.setText("By priority");
	
	MenuItem mntmByDueDate = new MenuItem(menu_4, SWT.RADIO);
	mntmByDueDate.setText("By due date");
	
	MenuItem mntmByBeginningDue = new MenuItem(menu_4, SWT.RADIO);
	mntmByBeginningDue.setText("By beginning due date");
	
	MenuItem mntmSuivant = new MenuItem(menu_3, SWT.NONE);
	mntmSuivant.setText("Suivant");
	
	MenuItem mntmPrcdent = new MenuItem(menu_3, SWT.NONE);
	mntmPrcdent.setText("Précédent");
	
	new MenuItem(menu_3, SWT.SEPARATOR);
	
	MenuItem mntmPremier = new MenuItem(menu_3, SWT.NONE);
	mntmPremier.setText("Premier");
	
	MenuItem mntmDernier = new MenuItem(menu_3, SWT.NONE);
	mntmDernier.setText("Dernier");
	
	MenuItem mntmAction = new MenuItem(menu, SWT.CASCADE);
	mntmAction.setText("Action");
	
	Menu menu_5 = new Menu(mntmAction);
	mntmAction.setMenu(menu_5);
	
	MenuItem mntmModify = new MenuItem(menu_5, SWT.NONE);
	mntmModify.setText("Modify");
	
	MenuItem mntmStart = new MenuItem(menu_5, SWT.NONE);
	mntmStart.setText("Start");
	
	MenuItem mntmTerminate = new MenuItem(menu_5, SWT.NONE);
	mntmTerminate.setText("Terminate");
	
	Menu menu_2 = new Menu(shell);
	shell.setMenu(menu_2);
	

	
	

	shell.open();
}
}
