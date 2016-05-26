package DAO.pojo;


import java.util.Date;

// default package
// Generated 2016-5-25 23:44:00 by Hibernate Tools 3.4.0.CR1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Benchrecord generated by hbm2java
 */
@Entity
@Table(name = "benchrecord", catalog = "superquant")
public class Benchrecord implements java.io.Serializable {

	private Date date;

	public Benchrecord() {
	}

	public Benchrecord(Date date) {
		this.date = date;
	}

	@Id

	@Temporal(TemporalType.DATE)
	@Column(name = "date", unique = true, nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}