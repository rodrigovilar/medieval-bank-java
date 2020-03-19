package br.ufpb.dcx.esa.medievalbank.command.agency;

import br.ufpb.dcx.esa.medievalbank.command.demand.Command;
import br.ufpb.dcx.esa.medievalbank.model.Demand;
import br.ufpb.dcx.esa.medievalbank.service.DemandService;

public class RemoveDemandOfTheAtendee implements Command {
	private DemandService demandService;
	private Demand demand;
	@Override
	public Object run() {
		this.demandService.delete(this.demand);
		return null;
	}

}
