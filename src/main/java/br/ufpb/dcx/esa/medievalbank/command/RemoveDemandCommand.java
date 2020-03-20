package br.ufpb.dcx.esa.medievalbank.command;

import br.ufpb.dcx.esa.medievalbank.model.Demand;
import br.ufpb.dcx.esa.medievalbank.service.DemandService;

public class RemoveDemandCommand extends Command {
    private  Demand demand;

    public RemoveDemandCommand(Demand demand) {
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
}
