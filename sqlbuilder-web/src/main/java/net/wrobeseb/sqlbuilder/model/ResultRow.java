package  net.wrobeseb.sqlbuilder.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ResultRow implements Serializable {

	private static final long serialVersionUID = -8620618237739511679L;

	private ArrayList<String> columnNames;
	private ArrayList<Object> values;
	
	public void addColumnName(String columnName) {
		if (columnNames == null) {
			columnNames = new ArrayList<String>();
		}
		columnNames.add(columnName);
	}
	
	public void addValue(Object value) {
		if (values == null) {
			values = new ArrayList<Object>();
		}
		values.add(value);
	}
	 
	public ArrayList<String> getColumnNames() {
		return columnNames;
	}
	public void setColumnNames(ArrayList<String> columnNames) {
		this.columnNames = columnNames;
	}
	public ArrayList<Object> getValues() {
		return values;
	}
	public void setValues(ArrayList<Object> values) {
		this.values = values;
	}
}
