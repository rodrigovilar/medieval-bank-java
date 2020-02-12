
package br.ufpb.dcx.esa.medievalbank.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Atendee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "ssn")
	private String ssn;

	@Column(name = "creation")
	private Date creation;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "demand_id", referencedColumnName = "id")
	private Demand demand;

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

	/**
	 * @param demand the demand to set
	 */
	public void setDemand(Demand demand) {
		this.demand = demand;
	}

	/**
	 * @return the demand
	 */
	public Demand getDemand() {
		return demand;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creation == null) ? 0 : creation.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atendee other = (Atendee) obj;
		if (creation == null) {
			if (other.creation != null)
				return false;
		} else if (!creation.equals(other.creation))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (ssn == null) {
			if (other.ssn != null)
				return false;
		} else if (!ssn.equals(other.ssn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String str = this.name;
		if (this.demand != null) {
			str += "->" + demand;
		}
		return str;
	}

}
