
package br.ufpb.dcx.esa.medievalbank.service;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Atendee implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String email;
	private String ssn;
	private Date creation;
	
	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return this.id;
	}

	public Date getCreation() {
		return this.creation;
	}

	public String getName() {
		return this.name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCreation(Date date) {
		this.creation = date;
		
	}

	public void setEmail(String email) {
		this.email = email;
		
	}

	public String getEmail() {
		return this.email;
	}

	public void setSSN(String ssn) {
		this.ssn = ssn;
		
	}
	public String getSSN() {
		return this.ssn;
	}

}
