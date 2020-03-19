package br.ufpb.dcx.esa.medievalbank.command.demand;

import br.ufpb.dcx.esa.medievalbank.repository.DemandRepository;

public class GetAllUnallocated implements Command {

	private DemandRepository repository;
	public Object run() {
		return repository.findByAllocatedFalse();
	}
	
}
