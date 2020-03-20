package br.ufpb.dcx.esa.medievalbank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class DemandTempoFixo extends Demand {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "initialSize")
	private int tamanhoInicial;
	@Column(name = "size")
	private int restante;
	
	public void setInitialSize(int size) {
		this.tamanhoInicial = size;
		this.restante = size;
	}
	public int getInitialSize() {
		return this.tamanhoInicial;
	}
	
	public int getSize() {
		return this.restante;
	}
	public void setSize(int restante) {
		this.restante = restante;
	}
	
}
