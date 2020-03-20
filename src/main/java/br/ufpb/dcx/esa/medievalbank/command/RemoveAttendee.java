package br.ufpb.dcx.esa.medievalbank.command;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.model.Atendee;

public class RemoveAttendee extends Command {

    Atendee attendee;
    private List<Object> permissions = new ArrayList<>();

    public RemoveAttendee(Atendee attendee) {
        this.attendee = attendee;
        this.permissions.add("ROLE_MANAGER");
    }

    @Override
    public String getDescription() {
        return "remove attendee";
    }

    @Override
    public Object execute() {
    	if(!getAgencyService().getRoles().toString().equals(permissions.toString())) {
    		throw new MedievalBankException("User is not allowed");
    	}
        getAgencyService().removeAttendee(this.attendee);
        return null;
    }
}
