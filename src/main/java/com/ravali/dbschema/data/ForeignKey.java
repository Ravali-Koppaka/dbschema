package com.ravali.dbschema.data;

import java.util.List;

public class ForeignKey {
	
	private String name;
	private List<String> referencedColumns;
	
	public ForeignKey() {}
	
	public ForeignKey(String name, List<String> referencedColumns ) {
		this.setName(name);
		this.setReferencedColumns(referencedColumns);
	}

	public List<String> getReferencedColumns() {
		return referencedColumns;
	}

	public void setReferencedColumns(List<String> referencedColumns) {
		this.referencedColumns = referencedColumns;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
