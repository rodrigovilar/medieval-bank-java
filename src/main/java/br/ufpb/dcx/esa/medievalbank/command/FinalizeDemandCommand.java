package br.ufpb.dcx.esa.medievalbank.command;

public class FinalizeDemandCommand extends Command {
    private String demandName;

    FinalizeDemandCommand(String demandName) {
        this.demandName = demandName;
    }

    @Override
    public String getDescription() {
        return "finalize command";
    }

    @Override
    public Object execute() {
        this.getAgencyService().finalizeDemandAtTheNextTick(this.demandName);
        return null;
    }
}
