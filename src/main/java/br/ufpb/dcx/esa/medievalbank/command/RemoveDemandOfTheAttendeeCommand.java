package br.ufpb.dcx.esa.medievalbank.command;

import br.ufpb.dcx.esa.medievalbank.model.Demand;

public class RemoveDemandOfTheAttendeeCommand extends Command {
    private Demand demand;

    RemoveDemandOfTheAttendeeCommand(Demand demand) {
        this.demand = demand;
    }

    @Override
    public String getDescription() {
        return "remove demand of the attendee";
    }

    @Override
    public Object execute() {
        getAgencyService().removeDemandOfTheAtendee(this.demand);
        return null;
    }
}
