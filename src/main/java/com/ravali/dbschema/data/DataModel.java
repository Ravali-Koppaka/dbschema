package com.ravali.dbschema.data;

import java.util.List;

public class DataModel {
	private List<Table> tables;
	
	public DataModel() {}
	
	public DataModel(List<Table> tables) {
		this.setTables(tables);
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}
	
}
