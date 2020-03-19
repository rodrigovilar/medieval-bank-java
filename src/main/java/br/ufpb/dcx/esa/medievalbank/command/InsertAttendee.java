package br.ufpb.dcx.esa.medievalbank.command;

import br.ufpb.dcx.esa.medievalbank.model.Atendee;

public class InsertAttendee extends Command {

    private Atendee attendee;

    public InsertAttendee(Atendee attendee) {
        this.attendee = attendee;
    }

    @Override
    public String getDescription() {
        return "insert attendee";
    }

    @Override
    public Atendee execute() {
        return getAgencyService().addAttendee(this.attendee);
    }
}
