package br.ufpb.dcx.esa.medievalbank.command.atendeeservice;

import br.ufpb.dcx.esa.medievalbank.command.demand.Command;
import br.ufpb.dcx.esa.medievalbank.repository.AtendeeRepository;

public class GetAllAtendeesWithoutDemand implements Command {
	private AtendeeRepository repository;
	@Override
	public Object run() {
		return repository.findByDemandIsNull();
	}

}
