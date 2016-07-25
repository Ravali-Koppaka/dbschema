package com.ravali.dbschema.database.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileConfiguration implements ConfigurationProvider {
	
	public DBConfiguration getConfiguration() {
		try {
			InputStream ipStream = this.getClass().getClassLoader()
					                  .getResource("db.properties").openStream();
			Properties properties = new Properties();
			properties.load(ipStream);
			String driverClass = properties.getProperty("driverClass");
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			String dbName = properties.getProperty("DBName");
			return new DBConfiguration(driverClass, url, username, password, dbName);
		} catch (IOException e) {
			throw new ConfigurationException("I/O Error on reading file",e);
		}
	}
	
}
