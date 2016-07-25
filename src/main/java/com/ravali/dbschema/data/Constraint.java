package com.ravali.dbschema.data;

import java.util.List;

public class Constraint {
	
	private String name;
	private String type;
	private List<String> columns;
	
	public Constraint() {}
	
	public Constraint(String name, String type, List<String> columns) {
		this.setName(name);
		this.setType(type);
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
