package net.wrobeseb.sqlbuilder.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

import net.wrobeseb.sqlbuilder.service.IAccessService;
import net.wrobeseb.sqlbuilder.utils.IOUtils;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;

import static org.junit.Assert.*;


@RunWith(JMock.class)
public class AccessServiceImplTest {

	private Mockery context = new JUnit4Mockery();
	private IAccessService accessService = new AccessServiceImpl();
	
	@Test
	public void createDatabaseFailTest() {
		
		AccessServiceImpl accessService = new AccessServiceImpl();
		File file = new File("");
		
		try {
			accessService.createDatabase(file);
			fail();
		}
		catch(IOException ex) { }
	}
	
	@Test
	public void createDatabaseTest() {
		File file = new File(IOUtils.CLASSPATH + "/test.mdb");
		
		try {
			Database db = accessService.createDatabase(file);
			assertEquals(0, db.getTableNames().size());
			db.close();
		}
		catch(IOException ex) { 
			fail(ex.getMessage());
		}
		finally {
			file.delete();
		}
	}
	
	

	@Test
	public void createTableTest()  {
		
		File file = new File(IOUtils.CLASSPATH + "/test.mdb");
		
		try {
		
			final ResultSetMetaData mockedResultSetMetaData = context.mock(ResultSetMetaData.class);
			
			context.checking(new Expectations() {
				{
					allowing(mockedResultSetMetaData).getColumnCount();
					will(returnValue(3));
					
					allowing(mockedResultSetMetaData).getColumnName(1);
					will(returnValue("testColumnName1"));
					
					allowing(mockedResultSetMetaData).getColumnName(2);
					will(returnValue("testColumnName2"));
					
					allowing(mockedResultSetMetaData).getColumnName(3);
					will(returnValue("testColumnName3"));
					
					allowing(mockedResultSetMetaData).getColumnType(1);
					will(returnValue(Types.INTEGER));
					
					allowing(mockedResultSetMetaData).getColumnType(2);
					will(returnValue(Types.VARCHAR));
					
					allowing(mockedResultSetMetaData).getColumnType(3);
					will(returnValue(Types.TIMESTAMP));
				}
			});
			Database db = accessService.createDatabase(file);
			
			Table table = accessService.createTable(db, "testTableName", mockedResultSetMetaData);
			
			/* fail test
			
			assertEquals(0, db.getTableNames().size());
			assertEquals(2, table.getColumnCount());

			*/
			
			assertEquals(1, db.getTableNames().size());
			assertEquals(3, table.getColumnCount());
			
			db.close();
		}
		catch (SQLException e) {
			fail(e.getMessage());
		} 
		catch (IOException e) {
			fail(e.getMessage());
		}
		finally {
			file.delete();
		}
	}

	@Test
	public void insertRowTest() {
		File file = new File(IOUtils.CLASSPATH + "/test.mdb");
		
		try {
		
			final ResultSetMetaData mockedResultSetMetaData = context.mock(ResultSetMetaData.class);
			
			context.checking(new Expectations() {
				{
					allowing(mockedResultSetMetaData).getColumnCount();
					will(returnValue(3));
					
					allowing(mockedResultSetMetaData).getColumnName(1);
					will(returnValue("testColumnName1"));
					
					allowing(mockedResultSetMetaData).getColumnName(2);
					will(returnValue("testColumnName2"));
					
					allowing(mockedResultSetMetaData).getColumnName(3);
					will(returnValue("testColumnName3"));
					
					allowing(mockedResultSetMetaData).getColumnType(1);
					will(returnValue(Types.INTEGER));
					
					allowing(mockedResultSetMetaData).getColumnType(2);
					will(returnValue(Types.VARCHAR));
					
					allowing(mockedResultSetMetaData).getColumnType(3);
					will(returnValue(Types.TIMESTAMP));
				}
			});
			Database db = accessService.createDatabase(file);
			
			Table table = accessService.createTable(db, "testTableName", mockedResultSetMetaData);
			
			Object[] values = new Object[] {1, "testValue", new Date() };
			
			accessService.insertRow(table, values);
			
			/* fail test
			
			assertEquals(0, table.getRowCount());

			*/
			
			assertEquals(1, table.getRowCount());
			
			db.close();
		}
		catch (SQLException e) {
			fail(e.getMessage());
		} 
		catch (IOException e) {
			fail(e.getMessage());
		}
		finally {
			file.delete();
		}
	}
	
}
