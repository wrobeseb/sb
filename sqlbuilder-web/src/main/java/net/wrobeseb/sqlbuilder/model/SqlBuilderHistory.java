package net.wrobeseb.sqlbuilder.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="history")
@SequenceGenerator(name="sequence", allocationSize=1, sequenceName="history_seq")
public class SqlBuilderHistory {

	private Integer id;
	private String query;
	private String login;
	private String ipAddress;
	private Date executeDt;
	
	@Id
	@Column(name="id")
	@GeneratedValue(generator="sequence", strategy=GenerationType.SEQUENCE)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="query")
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	@Column(name="login")
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Column(name="ip_address")
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	@Column(name="execute_dt")
	public Date getExecuteDt() {
		return executeDt;
	}
	public void setExecuteDt(Date executeDt) {
		this.executeDt = executeDt;
	}
}
