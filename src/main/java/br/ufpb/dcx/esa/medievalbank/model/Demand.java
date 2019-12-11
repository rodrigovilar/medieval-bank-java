package br.ufpb.dcx.esa.medievalbank.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Demand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
<<<<<<< HEAD
	public Demand() {
	
	}
	public Demand(String name) {
		this.name = name;
	}
=======
	public Demand(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Demand() {
	}

>>>>>>> 3b0ecb461b8ad56fe2ead3b1c8776728f37aaad3
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return name;
	}
	
	

	@Override
	public String toString() {
		return name ;
	}

}
