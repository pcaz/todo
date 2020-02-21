package com.neotec.todo.model;




public enum ToDoFormat{
   
	XML(".xml");
    private final String value;
   
    ToDoFormat(String v) {
        value = v;
}

   


    public String value() {
        return value;
    }

    public static ToDoFormat fromValue(String v) {
        for (ToDoFormat c: ToDoFormat.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }


}