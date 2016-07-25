package com.ravali.dbschema.database;

public class DBAccessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DBAccessException() {
		super();
	}
	
	public DBAccessException(String message) {
		super(message);
	}
	
	public DBAccessException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DBAccessException(Throwable cause) {
		super(cause);
	}

}
