package br.ufpb.dcx.esa.medievalbank.command.agency;

import br.ufpb.dcx.esa.medievalbank.command.demand.Command;

public class GetName implements Command {
private String name;
	@Override
	public Object run() {
		// TODO Auto-generated method stub
		return this.name;
	}

}
