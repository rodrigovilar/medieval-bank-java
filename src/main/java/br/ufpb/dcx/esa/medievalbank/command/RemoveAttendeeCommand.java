package br.ufpb.dcx.esa.medievalbank.command;

import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.service.AtendeeService;

public class RemoveAttendeeCommand extends Command {

    Atendee attendee;

    public RemoveAttendeeCommand(Atendee attendee) {
        this.attendee = attendee;
    }

    @Override
    public String getDescription() {
        return "remove attendee";
    }

    @Override
    public Object execute() {
        getAgencyService().removeAttendee(this.attendee);
        return null;
    }
}
