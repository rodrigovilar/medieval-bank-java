package br.ufpb.dcx.esa.medievalbank.command.agency;

import br.ufpb.dcx.esa.medievalbank.command.demand.Command;
import br.ufpb.dcx.esa.medievalbank.service.DemandService;

public class GetDemand implements Command {
	private DemandService demandService;
	@Override
	public Object run() {
		return this.demandService;
	}

}
