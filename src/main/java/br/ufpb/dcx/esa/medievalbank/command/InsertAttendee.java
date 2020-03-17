package br.ufpb.dcx.esa.medievalbank.command;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.dcx.esa.medievalbank.model.Atendee;

public class InsertAttendee extends Command {

    private Atendee attendee;
    
    private List<String> permissions = new ArrayList<String>();

    public InsertAttendee(Atendee attendee) {
        this.attendee = attendee;
        this.permissions.add("MANAGER");
    }

    @Override
    public String getDescription() {
        return "insert attendee";
    }

    @Override
    public Atendee execute() {
        return getAgencyService().addAttendee(this.attendee);
    }

	@Override
	public String getPermission() {
		return this.permissions.toString();
	}
}
