package net.wrobeseb.sqlbuilder.module;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlStrategy;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SessionAuthenticationStrategy extends ConcurrentSessionControlStrategy implements ServletContextAware {

	private ServletContext servletContext;
	
	public SessionAuthenticationStrategy(SessionRegistry sessionRegistry) {
		super(sessionRegistry);
		setExceptionIfMaximumExceeded(true);
	}

	@Override
	public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		super.onAuthentication(authentication, request, response);
		
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		BasicDataSource dataSource = (BasicDataSource) applicationContext.getBean("dataSource");
		
		Object object = authentication.getPrincipal();
		String credentials = "";
		String username = "";
		
		if (object instanceof User) {
			credentials = ((User)object).getPassword();
			username = ((User)object).getUsername();
		}
		
		dataSource.setUsername(username);
		dataSource.setPassword(credentials);
		
		try {
			Connection connection = dataSource.getConnection();
			connection.close();
		} 
		catch (SQLException e) {
			throw new BadCredentialsException("Bledny login i/lub haslo.");
		}
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
