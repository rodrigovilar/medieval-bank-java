package br.ufpb.dcx.esa.medievalbank.command;

public class IncreaseTickCommand extends Command {
    @Override
    public String getDescription() {
        return "increase tick";
    }

    @Override
    public Object execute() {
        getAgencyService().increaseTick();
        return null;
    }
}
