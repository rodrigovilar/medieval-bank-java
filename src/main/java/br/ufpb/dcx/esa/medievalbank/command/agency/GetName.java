package br.ufpb.dcx.esa.medievalbank.command.agency;

import br.ufpb.dcx.esa.medievalbank.command.demand.Command;

public class GetName implements Command {
private String name;
	@Override
	public Object run() {
		return this.name;
	}

}
