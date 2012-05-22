package net.wrobeseb.sqlbuilder.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;

import net.wrobeseb.sqlbuilder.dao.ISqlBuilderDao;
import net.wrobeseb.sqlbuilder.model.NamedQuery;
import net.wrobeseb.sqlbuilder.model.ResultRow;
import net.wrobeseb.sqlbuilder.model.SqlBuilderHistory;
import net.wrobeseb.sqlbuilder.service.ISqlBuilderService;
import net.wrobeseb.sqlbuilder.utils.DateTime;
import net.wrobeseb.sqlbuilder.utils.IOUtils;

@Service(value="sqlBuilderService")
public class SqlBuilderServiceImpl implements ISqlBuilderService {

	@Autowired(required=true)
	private ISqlBuilderDao sqlBuilderDao;
	
	@Override
	@Transactional(value="dynamic", propagation=Propagation.SUPPORTS, readOnly=true)
	public List<ResultRow> executeQuery(String query) {
		query = query.replaceAll("\n", " ");
		return sqlBuilderDao.executeQuery(query);
	}
	

	@Override
	@Transactional(value="dynamic", propagation=Propagation.SUPPORTS, readOnly=true)
	public int getCount(String query) {
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler();
		sqlBuilderDao.executeImportQuery(query, countCallback);
		return countCallback.getRowCount();
	}

	@Override
	@Transactional(value="static", propagation=Propagation.REQUIRES_NEW, readOnly=false)
	public void saveQuery(NamedQuery query) {
		query.setId(null);
		query.setAddDt(DateTime.getDate());
		sqlBuilderDao.saveQuery(query);
	}

	@Override
	@Transactional(value="static", propagation=Propagation.REQUIRES_NEW, readOnly=false)
	public void updateQuery(NamedQuery query) {
		sqlBuilderDao.updateQuery(query);
	}

	@Override
	@Transactional(value="static", propagation=Propagation.SUPPORTS, readOnly=true)
	public List<NamedQuery> getListOfNamedQueriesByUsername(String username) {
		return sqlBuilderDao.getListOfNamedQueriesByUsername(username);
	}

	@Override
	@Transactional(value="static", propagation=Propagation.SUPPORTS, readOnly=true)
	public NamedQuery getNamedQueryById(Integer queryId) {
		return sqlBuilderDao.getNamedQueryById(queryId);
	}
	
	@Override
	@Transactional(value="dynamic", propagation=Propagation.SUPPORTS, readOnly=true)
	public String createMdbDoc(String query, String queryName) throws SQLException, IOException {
		
		File mdbTempFile = new File(IOUtils.TEMP_DIR + queryName);
		
		Database db = Database.create(mdbTempFile);
		
		//Table table = new TableBuilder("export");
		
		//FileOutputStream ostream = new FileOutputStream(absoluteFilePath, false);
		
		return "";
	}
	
	@Override
	@Transactional(value="dynamic", propagation=Propagation.SUPPORTS, readOnly=true)
	public String createTxtDoc(String query, String queryName) {
		StringBuilder builder = new StringBuilder();
		Integer columnCount = 0;
		
		/*for (int i = 0; i < resultsList.size(); i++) {
			ResultRow resultRow = resultsList.get(i);
			if (i == 0) {
				columnCount = resultRow.getColumnNames().size();
				for (int j = 0; j < columnCount; j++) {
					builder.append("\"" + resultRow.getColumnNames().get(j) + "\"");
					if (j < columnCount - 1) builder.append(";");
				}
			}
			else {
				for (int j = 0; j < resultRow.getValues().size(); j++) {
					Object value = resultRow.getValues().get(j);
					if (value instanceof Integer)
						builder.append(value);
					else builder.append("\"" + value + "\"");
					if (j < columnCount - 1) builder.append(";");
				}
			}
		}
		
		return builder.toString().getBytes();*/
		return null;
	}
	
	@Override
	@Transactional(value="static", propagation=Propagation.REQUIRES_NEW, readOnly=false)
	public void registerHistory(String query, String login, String ipAddress) {
		SqlBuilderHistory sqlBuilderHistory = new SqlBuilderHistory();
		sqlBuilderHistory.setQuery(query);
		sqlBuilderHistory.setLogin(login);
		sqlBuilderHistory.setIpAddress(ipAddress);
		sqlBuilderHistory.setExecuteDt(DateTime.getDate());
		sqlBuilderDao.registerHistory(sqlBuilderHistory);
	}

	@Override
	@Transactional(value="static", propagation=Propagation.REQUIRES_NEW, readOnly=false)
	public void setLastExecuteDate(Integer id) {
		sqlBuilderDao.setLastExecuteDate(id);
	}
}
