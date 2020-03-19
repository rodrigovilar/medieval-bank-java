package br.ufpb.dcx.esa.medievalbank.command.agency;

import br.ufpb.dcx.esa.medievalbank.command.demand.Command;

public class ResetTick implements Command {
	private int tick;
	@Override
	public Object run() {
		this.tick= 0;
		return tick;
	}

}
