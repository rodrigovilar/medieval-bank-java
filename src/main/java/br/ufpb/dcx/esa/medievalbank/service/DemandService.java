package br.ufpb.dcx.esa.medievalbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
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

	public List<Demand> getAllUnallocated() {
		return this.repository.findByAllocatedFalse();
	}

	public void delete(Demand demand) {
		repository.delete(demand);
	}

	public Demand update(Demand demand) throws MedievalBankException {
		this.validadeDemand(demand);
		return repository.save(demand);
	}

	private void validadeDemand(Demand demand) throws MedievalBankException {
		if (demand.getId() == null) {
			throw new MedievalBankException("Demand with null id");
		}
		if (demand.getName() == null) {
			throw new MedievalBankException("Demand with null name");
		}
	}
}
