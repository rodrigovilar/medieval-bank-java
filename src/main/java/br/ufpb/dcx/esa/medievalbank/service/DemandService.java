package br.ufpb.dcx.esa.medievalbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.dcx.esa.medievalbank.model.Demand;
import br.ufpb.dcx.esa.medievalbank.repository.DemandRepository;

@Service
public class DemandService {

	@Autowired
	private DemandRepository repository;

	public Demand create(Demand demand) {

		return repository.save(demand);
	}

	public List<Demand> getAll() {
		return repository.findAll();
	}

	public void delete(Demand demand) {
		repository.delete(demand);
	}

	public Demand update(Integer demandID, Demand demand) {
		Demand searchedDemand = this.repository.getOne(demandID);
		searchedDemand = demand;
		return repository.save(searchedDemand);
	}

}
