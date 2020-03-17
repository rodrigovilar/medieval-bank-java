package br.ufpb.dcx.esa.medievalbank.command;

import br.ufpb.dcx.esa.medievalbank.service.AgencyService;

public abstract class Command {

    private AgencyService agencyService;

    public abstract String getDescription();
    public abstract Object execute();
    public abstract String getPermission();

    public void setAgencyService(AgencyService agencyService) {
        this.agencyService = agencyService;
    }
    public AgencyService getAgencyService(){
        return this.agencyService;
    }

}
