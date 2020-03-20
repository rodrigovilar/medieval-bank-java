package br.ufpb.dcx.esa.medievalbank.command;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.model.Atendee;

public class InsertAttendee extends Command {

    private Atendee attendee;
    private List<Object> permissions = new ArrayList<>();

    public InsertAttendee(Atendee attendee) {
        this.attendee = attendee;
        this.permissions.add("ROLE_MANAGER");
    }

    @Override
    public String getDescription() {
        return "insert attendee";
    }

    @Override
    public Atendee execute() {
    	if(!getAgencyService().getRoles().toString().equals(permissions.toString())) {
    		throw new MedievalBankException("User is not allowed");
    	}
        return getAgencyService().addAttendee(this.attendee);
    }
}
