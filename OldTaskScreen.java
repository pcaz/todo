package com.neotec.todo.gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;

public class TaskScreen extends Composite {
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


	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TaskScreen(Composite parent, int style, int mode) {
		super(parent, style);
		setLayout(new FormLayout());
		

		
		lblId = new Label(this, SWT.NONE);
		FormData fd_LblId = new FormData();
		lblId.setLayoutData(fd_LblId);
		lblId.setText(Messages.getString("Id")); //$NON-NLS-1$
		
		txtId = new Text(this, SWT.BORDER);
		fd_LblId.right = new FormAttachment(txtId, -11);
		fd_LblId.bottom = new FormAttachment(txtId, 0, SWT.BOTTOM);
		txtId.setEditable(false);
		FormData fd_txtId = new FormData();
		fd_txtId.top = new FormAttachment(0, 10);
		fd_txtId.left = new FormAttachment(0, 84);
		fd_txtId.right = new FormAttachment(100, -512);
		txtId.setLayoutData(fd_txtId);
		
		txtName = new Text(this, SWT.BORDER);
		FormData fd_Name = new FormData();
		fd_Name.right = new FormAttachment(100, -44);
		txtName.setLayoutData(fd_Name);
		
		lblName = new Label(this, SWT.NONE);
		fd_Name.left = new FormAttachment(lblName, 5);
		FormData fd_LblName = new FormData();
		fd_LblName.right = new FormAttachment(100, -512);
		fd_LblName.bottom = new FormAttachment(txtName, 0, SWT.BOTTOM);
		lblName.setLayoutData(fd_LblName);
		lblName.setText(Messages.getString("Name")); //$NON-NLS-1$
		
		txtDescription = new Text(this, SWT.BORDER);
		fd_Name.bottom = new FormAttachment(txtDescription, -6);
		FormData fd_Description = new FormData();
		fd_Description.right = new FormAttachment(100, -44);
		fd_Description.top = new FormAttachment(0, 87);
		txtDescription.setLayoutData(fd_Description);
		
		lblDescription = new Label(this, SWT.NONE);
		fd_Description.left = new FormAttachment(0, 104);
		FormData fd_LblDescription = new FormData();
		fd_LblDescription.right = new FormAttachment(txtDescription, -5);
		fd_LblDescription.top = new FormAttachment(lblName, 16);
		fd_LblDescription.bottom = new FormAttachment(txtDescription, 0, SWT.BOTTOM);
		lblDescription.setLayoutData(fd_LblDescription);
		lblDescription.setText(Messages.getString("Description")); //$NON-NLS-1$
		
		txtNote = new Text(this, SWT.BORDER | SWT.MULTI);
		fd_Description.bottom = new FormAttachment(100, -356);
		FormData fd_Note = new FormData();
		fd_Note.right = new FormAttachment(100, -44);
		fd_Note.bottom = new FormAttachment(txtDescription, 63, SWT.BOTTOM);
		fd_Note.top = new FormAttachment(0, 118);
		txtNote.setLayoutData(fd_Note);
		
		lblNote = new Label(this, SWT.NONE);
		fd_Note.left = new FormAttachment(lblNote, 5);
		FormData fd_LblNote = new FormData();
		fd_LblNote.right = new FormAttachment(100, -512);
		fd_LblNote.top = new FormAttachment(0, 117);
		lblNote.setLayoutData(fd_LblNote);
		lblNote.setText(Messages.getString("Notes")); //$NON-NLS-1$
		
		lblCategory = new Label(this, SWT.NONE);
		FormData fd_LblCategory = new FormData();
		fd_LblCategory.top = new FormAttachment(txtNote, 29);
		fd_LblCategory.left = new FormAttachment(0, 10);
		lblCategory.setLayoutData(fd_LblCategory);
		lblCategory.setText(Messages.getString("Category")); //$NON-NLS-1$
		
		cbCategory = new Combo(this, SWT.NONE);
		fd_LblCategory.right = new FormAttachment(cbCategory, -5);
		fd_LblCategory.bottom = new FormAttachment(cbCategory, 0, SWT.BOTTOM);
		FormData fd_CbCategory = new FormData();
		fd_CbCategory.top = new FormAttachment(txtNote, 17);
		fd_CbCategory.left = new FormAttachment(0, 79);
		cbCategory.setLayoutData(fd_CbCategory);
		
		lblPriority = new Label(this, SWT.NONE);
		FormData fd_LblPriority = new FormData();
		fd_LblPriority.left = new FormAttachment(cbCategory, 4);
		fd_LblPriority.top = new FormAttachment(txtNote, 29);
		fd_LblPriority.bottom = new FormAttachment(cbCategory, 0, SWT.BOTTOM);
		lblPriority.setLayoutData(fd_LblPriority);
		lblPriority.setText(Messages.getString("Priority")); //$NON-NLS-1$
		
		txtPriority = new Text(this, SWT.BORDER);
		fd_LblPriority.right = new FormAttachment(txtPriority, -6);
		FormData fd_Priority = new FormData();
		fd_Priority.right = new FormAttachment(0, 365);
		fd_Priority.top = new FormAttachment(0, 194);
		fd_Priority.left = new FormAttachment(0, 327);
		txtPriority.setLayoutData(fd_Priority);
		
		lblProject = new Label(this, SWT.NONE);
		FormData fd_LblProject = new FormData();
		fd_LblProject.right = new FormAttachment(100, -502);
		lblProject.setLayoutData(fd_LblProject);
		lblProject.setText(Messages.getString("Project")); //$NON-NLS-1$
		
		txtProject = new Text(this, SWT.BORDER);
		txtProject.setEditable(false);
		txtProject.setEnabled(false);
		fd_LblProject.bottom = new FormAttachment(txtProject, 0, SWT.BOTTOM);
		FormData fd_Project = new FormData();
		fd_Project.left = new FormAttachment(txtName, 13, SWT.LEFT);
		fd_Project.right = new FormAttachment(100, -31);
		txtProject.setLayoutData(fd_Project);
		
		lblState = new Label(this, SWT.NONE);
		FormData fd_LblState = new FormData();
		fd_LblState.bottom = new FormAttachment(lblCategory, 0, SWT.BOTTOM);
		fd_LblState.left = new FormAttachment(txtPriority, 6);
		lblState.setLayoutData(fd_LblState);
		lblState.setText(Messages.getString("State")); //$NON-NLS-1$
		
		lblEstimatedTime = new Label(this, SWT.CENTER);
		FormData fd_LblEstimatedTime = new FormData();
		fd_LblEstimatedTime.right = new FormAttachment(100, -446);
		fd_LblEstimatedTime.top = new FormAttachment(txtProject, 22);
		lblEstimatedTime.setLayoutData(fd_LblEstimatedTime);
		lblEstimatedTime.setText(Messages.getString("EstimatedTime")); //$NON-NLS-1$
		
		txtEstimatedTime = new Text(this, SWT.BORDER);
		FormData fd_EstimatedTime = new FormData();
		fd_EstimatedTime.left = new FormAttachment(lblEstimatedTime, 19);
		fd_EstimatedTime.right = new FormAttachment(lblEstimatedTime, 120, SWT.RIGHT);
		fd_EstimatedTime.top = new FormAttachment(txtProject, 12);
		txtEstimatedTime.setLayoutData(fd_EstimatedTime);
		
		lblDueDate = new Label(this, SWT.NONE);
		FormData fd_LblDueDate = new FormData();
		fd_LblDueDate.right = new FormAttachment(100, -193);
		fd_LblDueDate.bottom = new FormAttachment(lblEstimatedTime, 0, SWT.BOTTOM);
		lblDueDate.setLayoutData(fd_LblDueDate);
		lblDueDate.setText(Messages.getString("DueDateTime")); //$NON-NLS-1$
		
		txtDueDate = new Text(this, SWT.BORDER);
		FormData fd_DueDate = new FormData();
		fd_DueDate.left = new FormAttachment(txtEstimatedTime, 153);
		fd_DueDate.right = new FormAttachment(100, -31);
		fd_DueDate.bottom = new FormAttachment(lblEstimatedTime, 0, SWT.BOTTOM);
		txtDueDate.setLayoutData(fd_DueDate);
		
		lblBeginningDate = new Label(this, SWT.NONE);
		FormData fd_LblBeginningDate = new FormData();
		fd_LblBeginningDate.right = new FormAttachment(lblEstimatedTime, 0, SWT.RIGHT);
		lblBeginningDate.setLayoutData(fd_LblBeginningDate);
		lblBeginningDate.setText(Messages.getString("BeginningDateTime")); //$NON-NLS-1$
		
		txtBeginningDate = new Text(this, SWT.BORDER);
		txtBeginningDate.setEditable(false);
		txtBeginningDate.setEnabled(false);
		fd_LblBeginningDate.bottom = new FormAttachment(txtBeginningDate, 0, SWT.BOTTOM);
		FormData fd_BeginningDate = new FormData();
		fd_BeginningDate.left = new FormAttachment(txtEstimatedTime, 13, SWT.LEFT);
		fd_BeginningDate.right = new FormAttachment(100, -285);
		fd_BeginningDate.top = new FormAttachment(txtEstimatedTime, 6);
		txtBeginningDate.setLayoutData(fd_BeginningDate);
		
		lblEndDate = new Label(this, SWT.NONE);
		FormData fd_LblEndDate = new FormData();
		fd_LblEndDate.top = new FormAttachment(lblDueDate, 13);
		lblEndDate.setLayoutData(fd_LblEndDate);
		lblEndDate.setText(Messages.getString("EndDateTime")); //$NON-NLS-1$
		
		txtEndDate = new Text(this, SWT.BORDER);
		fd_LblEndDate.right = new FormAttachment(txtEndDate, -33);
		txtEndDate.setEditable(false);
		txtEndDate.setEnabled(false);
		FormData fd_EndDate = new FormData();
		fd_EndDate.left = new FormAttachment(txtDueDate, 13, SWT.LEFT);
		fd_EndDate.right = new FormAttachment(100, -31);
		fd_EndDate.top = new FormAttachment(txtDueDate, 6);
		txtEndDate.setLayoutData(fd_EndDate);
		
		lblPrerequisites = new Label(this, SWT.NONE);
		FormData fd_lblPrerequisites = new FormData();
		lblPrerequisites.setLayoutData(fd_lblPrerequisites);
		lblPrerequisites.setText(Messages.getString("Prerequisites")); //$NON-NLS-1$
		
		cbPrerequisites = new Combo(this, SWT.NONE);
		cbPrerequisites.setEnabled(false);
		FormData fd_cbPrerequisites = new FormData();
		fd_cbPrerequisites.top = new FormAttachment(txtBeginningDate, 22);
		fd_cbPrerequisites.left = new FormAttachment(lblPrerequisites, 6);
		cbPrerequisites.setLayoutData(fd_cbPrerequisites);
		
		cbResources = new Combo(this, SWT.NONE);
		fd_LblEndDate.bottom = new FormAttachment(cbResources, -25);
		fd_cbPrerequisites.bottom = new FormAttachment(cbResources, 0, SWT.BOTTOM);
		fd_lblPrerequisites.bottom = new FormAttachment(cbResources, 0, SWT.BOTTOM);
		cbResources.setEnabled(false);
		FormData fd_cbResources = new FormData();
		fd_cbResources.top = new FormAttachment(txtEndDate, 22);
		fd_cbResources.right = new FormAttachment(100, -10);
		cbResources.setLayoutData(fd_cbResources);
	
		Button btnNext = new Button(this, SWT.NONE);
		fd_cbResources.bottom = new FormAttachment(btnNext, -19);
		fd_EndDate.bottom = new FormAttachment(btnNext, -68);
		fd_BeginningDate.bottom = new FormAttachment(btnNext, -68);
		fd_LblEstimatedTime.bottom = new FormAttachment(btnNext, -99);
		fd_Project.bottom = new FormAttachment(btnNext, -136);
		btnNext.setText(Messages.getString("Next")); //$NON-NLS-1$
		btnNext.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		FormData fd_btnNext = new FormData();
		fd_btnNext.bottom = new FormAttachment(100, -32);
		fd_btnNext.right = new FormAttachment(100, -54);
		fd_btnNext.left = new FormAttachment(100, -118);
		btnNext.setLayoutData(fd_btnNext);
		
		Button btnPrevious = new Button(this, SWT.NONE);
		btnPrevious.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnPrevious.setText(Messages.getString("Previous")); //$NON-NLS-1$
		FormData fd_btnPrevious = new FormData();
		fd_btnPrevious.bottom = new FormAttachment(btnNext, 0, SWT.BOTTOM);
		fd_btnPrevious.right = new FormAttachment(btnNext, -6);
		btnPrevious.setLayoutData(fd_btnPrevious);
		
		Label lblResources = new Label(this, SWT.NONE);
		fd_LblDueDate.left = new FormAttachment(lblResources, 0, SWT.LEFT);
		fd_cbResources.left = new FormAttachment(lblResources, 6);
		fd_cbPrerequisites.right = new FormAttachment(lblResources, -17);
		fd_lblPrerequisites.right = new FormAttachment(lblResources, -210);
		FormData fd_lblResources = new FormData();
		fd_lblResources.bottom = new FormAttachment(btnPrevious, -19);
		fd_lblResources.right = new FormAttachment(100, -203);
		lblResources.setLayoutData(fd_lblResources);
		lblResources.setText(Messages.getString("Resources")); //$NON-NLS-1$
		
		cbState = new Combo(this, SWT.NONE);
		FormData fd_cbState = new FormData();
		fd_cbState.top = new FormAttachment(0, 192);
		fd_cbState.left = new FormAttachment(0, 414);
		cbState.setLayoutData(fd_cbState);
		
		setTabList(new Control[]{txtId, txtName, txtDescription, txtNote, cbCategory, txtPriority, cbState, txtProject, txtEstimatedTime, txtDueDate, txtBeginningDate, txtEndDate, cbPrerequisites, cbResources, btnNext, btnPrevious});
	
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
