package net.wrobeseb.sqlbuilder.module.command;

import java.io.Serializable;
import java.util.List;

import net.wrobeseb.sqlbuilder.model.ExportInfo;
import net.wrobeseb.sqlbuilder.model.NamedQuery;
import net.wrobeseb.sqlbuilder.model.ResultRow;

public class QueryCommand implements Serializable {
	
	private static final long serialVersionUID = -1711720625023339197L;
	
	private NamedQuery query;
	
	private ExportInfo exportInfo;
	
	private List<ResultRow> result;
	private Integer resultCount;
	private List<String> columnNames;
	private boolean moreThenHundred;

	public QueryCommand() {
		query = new NamedQuery();
		exportInfo = new ExportInfo();
	}
	public List<ResultRow> getResult() {
		return result;
	}
	public void setResult(List<ResultRow> result) {
		this.result = result;
		if (result.size() != 0) {
			this.columnNames = result.get(0).getColumnNames();
		}
		if (result.size() < resultCount)
			moreThenHundred = true;
		else
			moreThenHundred = false;
	}
	public List<String> getColumnNames() {
		return columnNames;
	}
	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}
	public Integer getResultCount() {
		return resultCount;
	}
	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}
	public NamedQuery getQuery() {
		return query;
	}
	public void setQuery(NamedQuery query) {
		this.query = query;
	}
	public ExportInfo getExportInfo() {
		return exportInfo;
	}
	public void setExportInfo(ExportInfo exportInfo) {
		this.exportInfo = exportInfo;
	}
	public boolean isMoreThenHundred() {
		return moreThenHundred;
	}
	public void setMoreThenHundred(boolean moreThenHundred) {
		this.moreThenHundred = moreThenHundred;
	}
	
}
