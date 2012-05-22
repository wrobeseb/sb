package  net.wrobeseb.sqlbuilder.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="named_query")
@SequenceGenerator(name="sequence", allocationSize=1, sequenceName="named_query_seq")
public class NamedQuery implements Serializable {

	private static final long serialVersionUID = -6543491475069473194L;
	
	private Integer id;
	private String username;
	private String name;
	private String query;
	private Date lastUsed;
	private Date addDt;
	
	@Id
	@Column(name="id")
	@GeneratedValue(generator="sequence", strategy=GenerationType.SEQUENCE)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="query")
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	@Column(name="last_used")
	public Date getLastUsed() {
		return lastUsed;
	}
	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}
	@Column(name="add_dt")
	public Date getAddDt() {
		return addDt;
	}
	public void setAddDt(Date addDt) {
		this.addDt = addDt;
	}
}
