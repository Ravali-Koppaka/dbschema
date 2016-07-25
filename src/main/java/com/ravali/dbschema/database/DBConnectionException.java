package com.ravali.dbschema.database;

public class DBConnectionException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DBConnectionException() {
		super();
	}
	
	public DBConnectionException(String message) {
		super(message);
	}
	
	public DBConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DBConnectionException(Throwable cause) {
		super(cause);
	}

}
