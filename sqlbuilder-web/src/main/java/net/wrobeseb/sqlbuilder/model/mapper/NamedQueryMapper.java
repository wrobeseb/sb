package net.wrobeseb.sqlbuilder.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.wrobeseb.sqlbuilder.model.NamedQuery;

public class NamedQueryMapper implements RowMapper<NamedQuery> {
	@Override
	public NamedQuery mapRow(ResultSet rs, int rowNum) throws SQLException {
		NamedQuery namedQuery = new NamedQuery();
		namedQuery.setId(rs.getInt("id"));
		namedQuery.setName(rs.getString("name"));
		namedQuery.setQuery(rs.getString("query"));
		namedQuery.setUsername(rs.getString("username"));
		return namedQuery;
	}

}
