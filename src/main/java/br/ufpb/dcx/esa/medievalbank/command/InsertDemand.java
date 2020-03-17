package br.ufpb.dcx.esa.medievalbank.command;

import br.ufpb.dcx.esa.medievalbank.model.Demand;
import br.ufpb.dcx.esa.medievalbank.service.DemandService;

public class InsertDemand extends Command {
    private Demand demand;

    public InsertDemand(Demand demand) {
        this.demand = demand;
    }

    @Override
    public String getDescription() {
        return "insert demand";
    }

    @Override
    public Object execute() {
        return getAgencyService().createDemand(this.demand);
    }

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}
}
