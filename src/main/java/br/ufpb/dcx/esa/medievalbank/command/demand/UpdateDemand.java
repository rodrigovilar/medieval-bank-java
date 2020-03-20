package br.ufpb.dcx.esa.medievalbank.command.demand;

import br.ufpb.dcx.esa.medievalbank.model.Demand;
import br.ufpb.dcx.esa.medievalbank.repository.DemandRepository;
import br.ufpb.dcx.esa.medievalbank.service.DemandService;

public class UpdateDemand implements Command{
	private Demand demand;
	private DemandRepository repository;
	private DemandService ds;

	@Override
	public Object run() {
		this.ds.validadeDemand(this.demand);
		return repository.save(this.demand);
	}

}
