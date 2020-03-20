package br.ufpb.dcx.esa.medievalbank.command;

import br.ufpb.dcx.esa.medievalbank.model.Demand;
import br.ufpb.dcx.esa.medievalbank.service.DemandService;

public class InsertDemandCommand extends Command {
    private Demand demand;

    public InsertDemandCommand(Demand demand) {
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
}
