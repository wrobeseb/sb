package net.wrobeseb.sqlbuilder.dao.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import oracle.sql.CLOB;
import oracle.sql.TIMESTAMP;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import net.wrobeseb.sqlbuilder.dao.ISqlBuilderDao;
import net.wrobeseb.sqlbuilder.model.NamedQuery;
import net.wrobeseb.sqlbuilder.model.ResultRow;
import net.wrobeseb.sqlbuilder.model.SqlBuilderHistory;
import net.wrobeseb.sqlbuilder.utils.DateTime;

@Repository(value="sqlBuilderDao")
class SqlBuilderDaoImpl extends HibernateDaoSupport implements ISqlBuilderDao {

	private JdbcTemplate template;
	
	private JdbcTemplate largeTemplate;
	
	@Autowired(required=true)
	public void setDataSource(BasicDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
		this.template.setMaxRows(100);
		this.largeTemplate = new JdbcTemplate(dataSource);
		this.largeTemplate.setFetchSize(1000);
	}
	
	@Autowired(required=true)
	public SqlBuilderDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	@Override
	public void executeImportQuery(String query, RowCallbackHandler rch) {
		largeTemplate.query(query, rch);
	}
	
	@Override
	public List<ResultRow> executeQuery(String query) {
		return template.query(query, new RowMapper<ResultRow>() {
			@Override
			public ResultRow mapRow(ResultSet rs, int rowNum) throws SQLException {
				ResultRow resultRow = new ResultRow();
				
				ResultSetMetaData metaData = rs.getMetaData();
				
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					resultRow.addColumnName(metaData.getColumnName(i));
					if (rs.getObject(i) instanceof TIMESTAMP) {
						resultRow.addValue(rs.getTimestamp(i));
					}
					else {
						if (rs.getObject(i) instanceof CLOB) {
							resultRow.addValue(((CLOB)rs.getObject(i)).stringValue());
						}
						else {
							resultRow.addValue(rs.getObject(i));
						}
					}
				}
				
				return resultRow;
			}
		});
	}

	@Override
	public void saveQuery(NamedQuery query) {
		getSession().saveOrUpdate(query);
	}
	
	@Override
	public void updateQuery(NamedQuery query) {
		saveQuery(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NamedQuery> getListOfNamedQueriesByUsername(String username) {
		return getSession().createCriteria(NamedQuery.class).add(Restrictions.eq("username", username)).list();
	}

	@Override
	public NamedQuery getNamedQueryById(Integer queryId) {
		return 	(NamedQuery) getSession().get(NamedQuery.class, queryId);
	}

	@Override
	public void registerHistory(SqlBuilderHistory sqlBuilderHistory) {
		getSession().saveOrUpdate(sqlBuilderHistory);
	}

	@Override
	public void setLastExecuteDate(Integer id) {
		SQLQuery query = getSession().createSQLQuery("UPDATE named_query SET last_used = ? WHERE id = ?");
		query.setTimestamp(0, DateTime.getDate());
		query.setInteger(1, id);
		query.executeUpdate();
	}

	@Override
	public ScrollableResults getScrollableResults(SQLQuery query) {
		return query.scroll(ScrollMode.FORWARD_ONLY);
	}

}
