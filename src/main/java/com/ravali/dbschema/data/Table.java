package com.ravali.dbschema.data;

import java.util.List;

public class Table {
	
	private String name;
	private List<Column> columns;
	private PrimaryKey primaryKey;
	private List<ForeignKey> foreignKeys;
	private List<Constraint> constraints;
	
	public Table() {}
	
	public Table(String name, List<Column> columns, PrimaryKey primaryKey,
			            List<ForeignKey> foreignKeys, List<Constraint> constraints) {
		setName(name);
		setColumns(columns);
		setPrimaryKey(primaryKey);
		setForeignKeys(foreignKeys);
		setConstraints(constraints);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public PrimaryKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(PrimaryKey primaryKey) {
		this.primaryKey = primaryKey;
	}

	public List<ForeignKey> getForeignKeys() {
		return foreignKeys;
	}

	public void setForeignKeys(List<ForeignKey> foreignKey) {
		this.foreignKeys = foreignKey;
	}

	public List<Constraint> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<Constraint> constraint) {
		this.constraints = constraint;
	}

}
