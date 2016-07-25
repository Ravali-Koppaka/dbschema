package com.ravali.dbschema.reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ravali.dbschema.data.Column;
import com.ravali.dbschema.data.Constraint;
import com.ravali.dbschema.data.DataModel;
import com.ravali.dbschema.data.ForeignKey;
import com.ravali.dbschema.data.PrimaryKey;
import com.ravali.dbschema.data.Table;
import com.ravali.dbschema.database.DBAccessException;
import com.ravali.dbschema.database.DBConnectionException;

public class DBSchemaReader {
	
	private final String QUERY_TABLE_NAMES = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema=?";
	private final String QUERY_TABLE_COLUMNS = "SELECT column_name,data_type,character_maximum_length,numeric_precision,"
			                                   + "numeric_scale,column_default,is_nullable FROM"
			                                   + " INFORMATION_SCHEMA.COLUMNS WHERE table_name=?";
	private final String QUERY_TABLE_CONSTRAINTS = "SELECT constraint_name, constraint_type "
			                                   + "FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE table_name=? "
			                                   + "and constraint_type NOT IN ('PRIMARY KEY','FOREIGN KEY')";
	private final String QUERY_TABLE_CONSTRAINT_COLUMNS = "SELECT column_name FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE"
			                                   + " WHERE table_name=? AND constraint_name=?";
	private final String QUERY_TABLE_KEYS = "SELECT constraint_name FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE table_name=?"
			                                   + " and constraint_type=?";

	private Connection connection;
	private String dbName;
	private PreparedStatement preparedStatement;
	
	public DBSchemaReader(Connection connection, String dbName) {
		this.connection = connection;
		this.dbName = dbName;
	}
	
	private List<String> readConstraintColumns(String tableName, String constraintName) {
		try {
			preparedStatement = connection.prepareStatement(QUERY_TABLE_CONSTRAINT_COLUMNS);
			preparedStatement.setString(1, tableName);
			preparedStatement.setString(2, constraintName);
			ResultSet constraintedColumns = preparedStatement.executeQuery();
			List<String> columnNames = new ArrayList<String>();
			while(constraintedColumns.next()) {
				columnNames.add(constraintedColumns.getString(1));
			}
			return columnNames;
		} catch(SQLException e) {
			throw new DBAccessException("Database access error",e);
		}
	}
	
	private List<ForeignKey> readTableForeignKey(String tableName) {
		try {
			preparedStatement = connection.prepareStatement(QUERY_TABLE_KEYS);
			preparedStatement.setString(1, tableName);
			preparedStatement.setString(2, "FOREIGN KEY");
			ResultSet foreignKeys = preparedStatement.executeQuery();
			List<ForeignKey> foreignKeyList = new ArrayList<ForeignKey>();
			while(foreignKeys.next()) {
				String foreignKeyName = foreignKeys.getString(1);
				foreignKeyList.add(new ForeignKey(foreignKeyName, readConstraintColumns(tableName, foreignKeyName)));
			}
			return foreignKeyList;
		} catch (SQLException e) {
			throw new DBAccessException("Database access error",e);
		}
	}
	
	private List<Constraint> readTableConstraints(String tableName) {
		try {
			preparedStatement = connection.prepareStatement(QUERY_TABLE_CONSTRAINTS);
			preparedStatement.setString(1, tableName);
			ResultSet constraints = preparedStatement.executeQuery();
			List<Constraint> constraintsList = new ArrayList<Constraint>();
			while(constraints.next()) {
				String constraint_name = constraints.getString(1);
				constraintsList.add(new Constraint(constraint_name, constraints.getString(2), 
						readConstraintColumns(tableName, constraint_name)));
			}
			return constraintsList;
		} catch(SQLException e) {
			throw new DBAccessException("Database access error",e);
		}
	}
	
	private PrimaryKey readTablePrimaryKey(String tableName) {
		try {
			preparedStatement = connection.prepareStatement(QUERY_TABLE_KEYS);
			preparedStatement.setString(1, tableName);
			preparedStatement.setString(2, "PRIMARY KEY");
			ResultSet primaryKeyResultSet = preparedStatement.executeQuery();
			PrimaryKey primaryKey = new PrimaryKey();
			if(primaryKeyResultSet.next()) {
				String primaryKeyName = primaryKeyResultSet.getString(1);
				primaryKey.setName(primaryKeyName);
				primaryKey.setColumns(readConstraintColumns(tableName, primaryKeyName));
			}
			return primaryKey;
		} catch (SQLException e) {
			throw new DBAccessException("Database access error",e);
		}
	}
	
	private List<Column> readTableColumns(String tableName) {
		try {
			preparedStatement = connection.prepareStatement(QUERY_TABLE_COLUMNS);
			preparedStatement.setString(1, tableName);
			ResultSet columns = preparedStatement.executeQuery();
			List<Column> columnsList = new ArrayList<Column>();
			while(columns.next()) {
				columnsList.add(new Column(columns.getString(1), columns.getString(2), 
						columns.getInt(3),columns.getInt(4), columns.getInt(5),
						columns.getString(6), columns.getBoolean(7)));
			}
			return columnsList;
		} catch (SQLException e) {
			throw new DBAccessException("Database access error",e);
		}
	}
	
	private Table readTable(String tableName) {
		return new Table(tableName, readTableColumns(tableName), readTablePrimaryKey(tableName), 
				                       readTableForeignKey(tableName), readTableConstraints(tableName));
	}
	
	public DataModel readDBSchema() {
		try {
			preparedStatement = connection.prepareStatement(QUERY_TABLE_NAMES);
			preparedStatement.setString(1, dbName);
			ResultSet tables = preparedStatement.executeQuery();
			List<Table> tablesList = new ArrayList<Table>();
			while(tables.next()) {
				tablesList.add(readTable(tables.getString(1)));
			}
			return new DataModel(tablesList);
		} catch (SQLException e) {
			throw new DBAccessException("Database access error",e);
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new DBConnectionException("DB access error while closing statement", e);
			}
		}
	}
	
}
