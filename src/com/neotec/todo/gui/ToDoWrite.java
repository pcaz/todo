package com.neotec.todo.gui;



import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;


public class ToDoWrite {
	private static void writeMessage(Shell shell,int Icon, String banniere, String corps){

		MessageBox box=new MessageBox(shell);
		box.setMessage(banniere+corps);
		box.open();
	}
	public static void writeInfo(Shell shell,String message){
		writeMessage(shell,SWT.ICON_INFORMATION,ToDo.messages.getString("Info"),message);
	}
	public static void writeWarning(Shell shell,String message){
		writeMessage(shell,SWT.ICON_WARNING,ToDo.messages.getString("Warning"),message);
	}
	public static void writeError(Shell shell,String message){
		writeMessage(shell,SWT.ICON_ERROR,ToDo.messages.getString("Error"),message);
	}
	public static void writeFatalError(Shell shell,String message){
		StackTraceElement[] out=Thread.currentThread().getStackTrace();
		writeMessage(shell,SWT.ICON_ERROR,ToDo.messages.getString("Error"),message);
		writeMessage(shell,SWT.NONE,"...",out[2].toString());
	}
	public static void writeDebug(String message){
		
	}
	
	public static boolean questionUser(Shell shell,String message){

        MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES | SWT.NO);
	    messageBox.setMessage(message);
	    if(messageBox.open()==SWT.YES)return true; else return false; 
	}
	

}
