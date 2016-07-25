package com.ravali.dbschema.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ravali.dbschema.database.config.DBConfiguration;

public class DBConnection {
	
	private static Connection connection;
	
	private DBConnection() {
	}
	
	public static Connection getConnection(DBConfiguration dbConfiguration) {
		if(connection == null) {
			try {
				connection = DriverManager.getConnection(dbConfiguration.getUrl(), 
						dbConfiguration.getUsername(), dbConfiguration.getPassword());
			} catch (SQLException e) {
				throw new DBConnectionException("DB access error while connecting", e);
			}
		}
		return connection;
	}
	
}
