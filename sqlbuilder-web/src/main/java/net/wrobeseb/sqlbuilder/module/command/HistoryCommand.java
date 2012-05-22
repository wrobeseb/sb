package net.wrobeseb.sqlbuilder.module.command;

import java.io.Serializable;
import java.util.List;

import net.wrobeseb.sqlbuilder.model.NamedQuery;

public class HistoryCommand implements Serializable {

	private static final long serialVersionUID = 2205589516791099719L;
	
	private List<NamedQuery> listOfNamedQueries;
	private Integer listOfNamedQueriesCount;
	private NamedQuery selected;
	
	public HistoryCommand() {}
	public HistoryCommand(List<NamedQuery> listOfNamedQueries) {
		setListOfNamedQuaries(listOfNamedQueries);
	}
	
	public List<NamedQuery> getListOfNamedQueries() {
		return listOfNamedQueries;
	}
	public void setListOfNamedQuaries(List<NamedQuery> listOfNamedQueries) {
		this.listOfNamedQueries = listOfNamedQueries;
		this.listOfNamedQueriesCount = listOfNamedQueries.size();
	}
	public Integer getListOfNamedQueriesCount() {
		return listOfNamedQueriesCount;
	}
	public void setListOfNamedQueriesCount(Integer listOfNamedQueriesCount) {
		this.listOfNamedQueriesCount = listOfNamedQueriesCount;
	}
	public NamedQuery getSelected() {
		return selected;
	}
	public void setSelected(NamedQuery selected) {
		this.selected = selected;
	}
}
