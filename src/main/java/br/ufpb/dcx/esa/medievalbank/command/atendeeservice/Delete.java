package br.ufpb.dcx.esa.medievalbank.command.atendeeservice;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.command.demand.Command;
import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.repository.AtendeeRepository;

public class Delete implements Command {
	private Atendee atendee;
	private AtendeeRepository repository;
	@Override
	public Object run() {
		if (this.atendee == null) {
			throw new MedievalBankException("Null atendee");
		}

		if (!(repository.existsById(this.atendee.getId()))) {
			throw new MedievalBankException("Atendee id not found: " + this.atendee.getId());
		}

		repository.delete(this.atendee);
		return null;
	}

}
