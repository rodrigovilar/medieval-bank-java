package br.ufpb.dcx.esa.medievalbank.command;

import br.ufpb.dcx.esa.medievalbank.model.Demand;
import br.ufpb.dcx.esa.medievalbank.service.DemandService;

public class RemoveDemand extends Command {
    private  Demand demand;

    public RemoveDemand(Demand demand) {
        this.demand = demand;
    }

    @Override
    public String getDescription() {
        return "remove demand";
    }

    @Override
    public Object execute() {
        getAgencyService().removeDemand(this.demand);
        return null;
    }

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}
}
