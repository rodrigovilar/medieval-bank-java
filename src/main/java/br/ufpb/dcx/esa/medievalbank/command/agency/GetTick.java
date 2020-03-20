package br.ufpb.dcx.esa.medievalbank.command.agency;

import br.ufpb.dcx.esa.medievalbank.command.demand.Command;

public class GetTick implements Command {
	private int tick;
	@Override
	public Object run() {
		// TODO Auto-generated method stub
		return this.tick;
	}

}
