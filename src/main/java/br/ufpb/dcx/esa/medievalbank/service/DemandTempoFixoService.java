package br.ufpb.dcx.esa.medievalbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufpb.dcx.esa.medievalbank.model.Demand;
import br.ufpb.dcx.esa.medievalbank.model.DemandTempoFixo;
import br.ufpb.dcx.esa.medievalbank.repository.DemandTempoFixoRepository;

public class DemandTempoFixoService extends DemandService{
	@Autowired
	private DemandTempoFixoRepository repository;
	
	public int decreaseSize(DemandTempoFixo d) {
		return d.getSize()-1;			
	}
	
	public void delete(DemandTempoFixo demand) {
		repository.delete(demand);
	}
	
	public List<Demand> getAllUnallocated() {
		return this.repository.findByAllocatedFalse();
	}
}
