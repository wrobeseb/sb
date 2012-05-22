package net.wrobeseb.sqlbuilder.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;

import net.wrobeseb.sqlbuilder.service.IAccessService;

@Service(value="accessService")
class AccessServiceImpl implements IAccessService {

	@Override
	public Database createDatabase(File file) throws IOException {
		return Database.create(file);
	}

	@Override
	public Table createTable(Database db, String tableName, ResultSetMetaData rsMetaData) throws SQLException, IOException {
		TableBuilder builder = new TableBuilder(tableName);
		for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
			builder.addColumn(new ColumnBuilder(rsMetaData.getColumnName(i)).setSQLType(rsMetaData.getColumnType(i)).toColumn());
		}
		return builder.toTable(db);
	}

	@Override
	public void insertRow(Table table, Object[] values) throws IOException {
		table.addRow(values);
	}

}
