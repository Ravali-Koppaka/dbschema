package com.ravali.dbschema.database.config;

public class DBConfiguration {
	
	private String driver;
	private String url;
	private String username;
	private String password;
	private String dbName;
	
	public DBConfiguration(String driver, String url, String username, String password, String dbName) {
		this.setDriver(driver);
		this.setUrl(url);
		this.setUsername(username);
		this.setPassword(password);
		this.setDbName(dbName);
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

}
