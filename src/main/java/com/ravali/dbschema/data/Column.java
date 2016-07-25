package com.ravali.dbschema.data;

public class Column {
	
	private String name;
	private String type;
	private int length;
	private int precision;
	private int scale;
	private String defaultValue;
	private boolean nullable;
	
	public Column() {}
	
	public Column(String name, String type, int length, int precision, int scale, 
			             String defaultValue, boolean nullable) {
		this.setName(name);
		this.setType(type);
		this.setLength(length);
		this.setPrecision(precision);
		this.setScale(scale);
		this.setDefaultValue(defaultValue);
		this.setNullable(nullable);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	
}
