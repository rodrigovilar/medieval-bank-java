package br.ufpb.dcx.esa.medievalbank.command.demand;

import br.ufpb.dcx.esa.medievalbank.model.Demand;
import br.ufpb.dcx.esa.medievalbank.repository.DemandRepository;

public class CreateDemand implements Command {
	private String name;
	private DemandRepository repository;
	
	public Object run() {
		Demand demand = new Demand(this.name);
		repository.save(demand);
		return null;
	}

}
