package br.ufpb.dcx.esa.medievalbank.command.demand;

import br.ufpb.dcx.esa.medievalbank.model.Demand;
import br.ufpb.dcx.esa.medievalbank.repository.DemandRepository;

public class DeleteDemand implements Command {
	private DemandRepository repository;
	private Demand demand;

	@Override
	public Object run() {
		repository.delete(this.demand);
		return null;
	}
	
}
