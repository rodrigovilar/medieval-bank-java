package br.ufpb.dcx.esa.medievalbank.command;

import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.service.AtendeeService;

public class RemoveAttendee extends Command {

    Atendee attendee;

    public RemoveAttendee(Atendee attendee) {
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

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}
}
