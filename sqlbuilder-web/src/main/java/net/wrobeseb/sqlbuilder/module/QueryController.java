package net.wrobeseb.sqlbuilder.module;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.mvc.servlet.MvcExternalContext;

import net.wrobeseb.sqlbuilder.module.command.QueryCommand;
import net.wrobeseb.sqlbuilder.service.ISqlBuilderService;
import net.wrobeseb.sqlbuilder.utils.DateTime;

@Controller
public class QueryController {

	@Autowired(required=true)
	private ISqlBuilderService sqlBuilderService;

	@Autowired(required=true)
	private HttpSession	session;
	
	public QueryCommand initCommand(Integer queryId) {
		QueryCommand command = new QueryCommand();
		if (queryId == null) {
			command.getQuery().setUsername(((User)((SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication().getPrincipal()).getUsername());
		}
		else {
			command.setQuery(sqlBuilderService.getNamedQueryById(queryId));
		}
		return command;
	}
	
	public void setQueryFromBuilder(QueryCommand queryCommand, String queryBody) {
		if (queryBody != null) {
			queryCommand.getQuery().setQuery(queryBody);
		}
	}
	
	public void contentAssist(QueryCommand queryCommand) {
		
	}
	
	public void execute(QueryCommand command, MessageContext messageContext, MvcExternalContext externalContext) {
		try {
			if (command.getQuery().getQuery() != null) {
				command.setResultCount(sqlBuilderService.getCount(command.getQuery().getQuery()));
				command.setResult(sqlBuilderService.executeQuery(command.getQuery().getQuery()));
				if (command.getQuery().getId() != null && !command.getQuery().getId().equals(0)) {
					sqlBuilderService.setLastExecuteDate(command.getQuery().getId());
				}
			}
			if (command.getResult().size() == 0) {
				messageContext.addMessage(new MessageBuilder().warning().defaultText("Brak wyników...").build());
			}
			sqlBuilderService.registerHistory(command.getQuery().getQuery(), command.getQuery().getUsername(), ((HttpServletRequest)externalContext.getNativeRequest()).getRemoteAddr());
		}
		catch (Exception ex) {
			if (ex.getCause() != null) {
				messageContext.addMessage(new MessageBuilder().error().defaultText(ex.getCause().getMessage()).build());
			}
			else {
				messageContext.addMessage(new MessageBuilder().error().defaultText(ex.toString()).build());
			}
		}
	}
	
	public void save(QueryCommand command, MessageContext messageContext) {
		try {
			if (command.getQuery().getId() != null && !command.getQuery().getId().equals(0)) {
				sqlBuilderService.updateQuery(command.getQuery());
			}
			else {
				sqlBuilderService.saveQuery(command.getQuery());
			}
			messageContext.addMessage(new MessageBuilder().info().defaultText("Zapytanie zapisane...").build());
		}
		catch (Exception ex) {
			messageContext.addMessage(new MessageBuilder().error().defaultText(ex.getCause().getMessage()).build());
		}
	}
	
	public void export(QueryCommand command, MessageContext messageContext, RequestContext context) {
		try {
			byte[] oStream = null;
			
			HttpServletResponse resp = (HttpServletResponse) context.getExternalContext().getNativeResponse();
			OutputStream respOutStream = resp.getOutputStream();
			
			String postFix = "txt";
			
			switch (command.getExportInfo().getExportFileType()) {
				case 1:
					//postFix = "mdb"; oStream = sqlBuilderService.createXlsDoc(command.getResult());break;
				default:
					//oStream = sqlBuilderService.createTxtDoc(command.getResult()); break;
			}
			
			resp.setContentType("application/" + postFix);
			resp.setHeader("Content-disposition", "attachment; filename= sqlBuilder-export-" + DateTime.format(DateTime.getDate(), "yyMMddHHmmss") + "." + postFix);
			
			respOutStream.write(oStream);
			respOutStream.flush();
			respOutStream.close();
			context.getExternalContext().recordResponseComplete();
			
			messageContext.addMessage(new MessageBuilder().info().defaultText("Dane pomyœlnie wyeksportowane...").build());
		}
		catch (Exception ex) {
			messageContext.addMessage(new MessageBuilder().error().defaultText(ex.getCause().getMessage()).build());
		}
	}
}
