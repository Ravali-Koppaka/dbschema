package com.ravali.dbschema.data;

import java.util.List;

public class PrimaryKey {
	
	private String name;
	private List<String> columns;
	
	public PrimaryKey() {}
	
	public PrimaryKey(String name, List<String> columns) {
		this.setName(name);
		this.setColumns(columns);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
}
