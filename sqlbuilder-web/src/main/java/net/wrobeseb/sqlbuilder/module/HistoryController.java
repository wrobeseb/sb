package net.wrobeseb.sqlbuilder.module;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import net.wrobeseb.sqlbuilder.module.command.HistoryCommand;
import net.wrobeseb.sqlbuilder.service.ISqlBuilderService;

@Controller
public class HistoryController {

	@Autowired(required=true)
	private ISqlBuilderService sqlBuilderService;
	
	@Autowired(required=true)
	private HttpSession	session;

	public HistoryCommand initCommand() {
		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
		Authentication auth = context.getAuthentication();
		User principal = (User) auth.getPrincipal();
		return new HistoryCommand(sqlBuilderService.getListOfNamedQueriesByUsername(principal.getUsername()));
	}
	
}
