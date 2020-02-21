package com.neotec.todo.gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;


public class CategoryGest extends Composite {
	private Table table;

	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CategoryGest(Composite parent, int style) {
		super(parent, style);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setToolTipText("");
		table.setBounds(0, 0, 450, 300);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Categories");
	

	}
}
