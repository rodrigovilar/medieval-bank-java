package br.ufpb.dcx.esa.medievalbank.command;

public class ResetTickCommand extends Command {
    @Override
    public String getDescription() {
        return "reset tick";
    }

    @Override
    public Object execute() {
        getAgencyService().resetTick();
        return null;
    }
}
