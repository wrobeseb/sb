package net.wrobeseb.sqlbuilder.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.wrobeseb.sqlbuilder.model.NamedQuery;
import net.wrobeseb.sqlbuilder.model.ResultRow;

public interface ISqlBuilderService {

	public List<ResultRow> executeQuery(String query);
	
	public int getCount(String query);
	
	public void saveQuery(NamedQuery query);
	
	public void updateQuery(NamedQuery query);
	
	public void setLastExecuteDate(Integer id);
	
	public List<NamedQuery> getListOfNamedQueriesByUsername(String username);
	
	public NamedQuery getNamedQueryById(Integer queryId);
	
	public void registerHistory(String query, String login, String ipAddress);

	String createMdbDoc(String query, String queryName) throws SQLException, IOException;

	String createTxtDoc(String query, String queryName);
	
}
