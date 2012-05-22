package net.wrobeseb.sqlbuilder.model;

import java.io.Serializable;

import javax.faces.model.SelectItem;

public class ListItem extends SelectItem implements Serializable {

	private static final long serialVersionUID = 2714305984594809989L;
	
	private String value;
	private String value1;
	private Boolean checked = false;
	private Boolean dictionaryValue = false;
	private Integer dictionaryGroupId;
	private String joinClause;
	
	public ListItem() { super(); }
	
	public ListItem(String value) {
		super(value, value);
		this.value = value;
	}
	
	public ListItem(String value, Boolean dictionaryValue, Integer dictionaryGroupId) {
		super(value, value);
		this.value = value;
		this.dictionaryValue = dictionaryValue;
		this.dictionaryGroupId = dictionaryGroupId;
	}
	
	public ListItem(String value, String value1, Boolean dictionaryValue, String joinClause) {
		super(value, value);
		this.value = value;
		this.value1 = value1;
		this.dictionaryValue = dictionaryValue;
		this.joinClause = joinClause;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public Boolean getChecked() {
		return checked;
	}
	public Boolean getDictionaryValue() {
		return dictionaryValue;
	}
	public Integer getDictionaryGroupId() {
		return dictionaryGroupId;
	}
	public String getJoinClause() {
		return joinClause;
	}
}
