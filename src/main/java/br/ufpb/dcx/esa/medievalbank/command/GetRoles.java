package br.ufpb.dcx.esa.medievalbank.command;

import java.util.List;

public class GetRoles extends Command {
	
	@Override
	public String getDescription() {
		return "Return Roles";
	}

	@Override
	public List<Object> execute() {
		return getAgencyService().getRoles();
	}

}
