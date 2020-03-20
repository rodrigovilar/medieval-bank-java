package br.ufpb.dcx.esa.medievalbank.command;

public class GetStatusCommand extends Command {
    @Override
    public String getDescription() {
        return "get status";
    }

    @Override
    public Object execute() {
        return this.getAgencyService().getStatus();
    }
}
