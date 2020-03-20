package br.ufpb.dcx.esa.medievalbank.command.atendeeservice;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.command.demand.Command;
import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.repository.AtendeeRepository;

public class ValidateDuplicatedName implements Command {
	private Atendee atendee;
	private AtendeeRepository repository;
	@Override
	public Object run() {
		if (repository.existsByName(this.atendee.getName())) {
			throw new MedievalBankException("Atendee name cannot be duplicated");
		}		
		return null;
	}

}
