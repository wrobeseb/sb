package net.wrobeseb.sqlbuilder.module;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.RequestContext;

import net.wrobeseb.sqlbuilder.module.command.WindCommand;
import net.wrobeseb.sqlbuilder.service.IWindService;
import net.wrobeseb.sqlbuilder.utils.DateTime;
import net.wrobeseb.sqlbuilder.utils.IOUtils;

@Controller
public class WindController {
	
	@Autowired(required=true)
	private IWindService windService;
	
	public WindCommand initCommand() {
		WindCommand command = new WindCommand();
		return command;
	}
	
	public void generate(WindCommand command, MessageContext messageContext, RequestContext context) throws IOException, SQLException {
		
		String mdbTempFileAbsolutePath = IOUtils.createFile(command.getMdbFile().getBytes(), command.getMdbFile().getName());
		
		File mdbTempFile = new File(mdbTempFileAbsolutePath);
		
		Hashtable<String, ArrayList<Integer>> deviceHash = new Hashtable<String, ArrayList<Integer>>(windService.createRecoveryExcludeDeviceHash());
		
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			Connection con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" + mdbTempFileAbsolutePath);
			
			DatabaseMetaData meta = con.getMetaData();
			
			ResultSet set = meta.getTables(null, null, null, new String[]{"TABLE"});
			
			String stacjeName = null;
			String syntetykaName = null;
			
			while (set.next()) {
				String tableName = set.getString("TABLE_NAME");
				if (tableName.contains("stacje")) {
					stacjeName = tableName;
				}
				if (tableName.contains("syntetyka")) {
					syntetykaName = tableName;
				}
			}
			set.close();
			set = null;
			
			for (String key : deviceHash.keySet()) {
				PreparedStatement stat = con.prepareStatement("SELECT id_platnika FROM " + stacjeName + " WHERE \"numer stacji\" = '" + key + "'");
				set = stat.executeQuery();
			
				if (set.next()){
					String value = set.getString("id_platnika");
					PreparedStatement updateStat = con.prepareStatement("UPDATE " + syntetykaName + " SET Weryfikacja = 'Nie windykowaæ' WHERE id_platnika = '" + value + "'");
					updateStat.executeUpdate();
					updateStat.close();
				}
				set.close();
				
				stat.close();
			}
			
			con.commit();
			con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			
		}
		
		HttpServletResponse resp = (HttpServletResponse) context.getExternalContext().getNativeResponse();
		OutputStream respOutStream = resp.getOutputStream();
		
		resp.setContentType("application/mdb");
		resp.setHeader("Content-disposition", "attachment; filename=daneg_" + DateTime.format(DateTime.getDate(), "yyyy-MM-dd") + ".mdb");
		
		FileInputStream fis = new FileInputStream(mdbTempFile);
		
		byte fileContent[] = new byte[(int)mdbTempFile.length()];
		
		fis.read(fileContent);
		
		fis.close();
		
	    respOutStream.write(fileContent);
		respOutStream.flush();
		respOutStream.close();
		context.getExternalContext().recordResponseComplete();
		
		IOUtils.deleteFile(mdbTempFileAbsolutePath);
	}
}
