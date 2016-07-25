package com.ravali.dbschema;

import java.sql.Connection;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ravali.dbschema.data.DataModel;
import com.ravali.dbschema.database.DBConnection;
import com.ravali.dbschema.database.DBConnectionException;
import com.ravali.dbschema.database.config.ConfigurationProvider;
import com.ravali.dbschema.database.config.DBConfiguration;
import com.ravali.dbschema.database.config.FileConfiguration;
import com.ravali.dbschema.reader.DBSchemaReader;

public class DBSchemaGenerator {
	
	public static void main(String args[]) {
		ConfigurationProvider configProvider = new FileConfiguration();
		DBConfiguration dbConfig = configProvider.getConfiguration();
		Connection connection = null;
		try {
			Class.forName(dbConfig.getDriver());
			connection = DBConnection.getConnection(dbConfig);
			DBSchemaReader dbSchemaReader = new DBSchemaReader(connection, dbConfig.getDbName());
			DataModel dataModel = dbSchemaReader.readDBSchema();
			Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
			System.out.println(gson.toJson(dataModel));
		} catch (ClassNotFoundException e) {
			throw new DriverNotFoundException("Driver " + dbConfig.getDriver() + " not found", e);
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new DBConnectionException("DB access error while closing connection", e);
			}
		}
	}
}
