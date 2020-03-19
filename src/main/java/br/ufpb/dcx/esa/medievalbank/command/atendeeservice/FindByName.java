package br.ufpb.dcx.esa.medievalbank.command.atendeeservice;

import br.ufpb.dcx.esa.medievalbank.command.demand.Command;
import br.ufpb.dcx.esa.medievalbank.repository.AtendeeRepository;

public class FindByName implements Command {
	private AtendeeRepository repository; 
	private String name;
	@Override
	public Object run() {
		return repository.findByNameContaining(this.name);

	}

}
