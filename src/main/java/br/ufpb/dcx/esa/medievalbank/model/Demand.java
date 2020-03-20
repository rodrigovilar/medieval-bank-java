package br.ufpb.dcx.esa.medievalbank.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Demand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "allocated")
	private boolean allocated;

	@OneToOne(mappedBy = "demand")
	private Atendee atendee;
	
	

	public Demand() {

	}

	public Demand(String name) {
		this.name = name;
	}

	public Demand(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Atendee getAtendee() {
		return atendee;
	}

	public void setAtendee(Atendee atendee) {
		this.atendee = atendee;
	}

	public boolean isAllocated() {
		return allocated;
	}

	public void setAllocated(boolean allocated) {
		this.allocated = allocated;
	}

	@Override
	public String toString() {
		return name;
	}

}
