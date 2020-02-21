package com.neotec.todo;


public class ToDoWrite {
	private static void writeMessage(String banniere, String corps){
//		try {
//			System.setOut(new PrintStream(System.out, true, "iso8859-1"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		System.out.println(banniere+corps);	
	}
	public static void writeInfo(String message){
		writeMessage("INFO:",message);
	}
	public static void writeWarning(String message){
		writeMessage("WARNING:",message);
	}
	public static void writeError(String message){
		StackTraceElement[] out=Thread.currentThread().getStackTrace();
		writeMessage("ERROR: ",message);
		writeMessage("...",out[2].toString());
	}
	public static void writeDebug(String message){
		writeMessage("DEBUG: ",message);
	}
	

}
