package br.ufpb.dcx.esa.medievalbank.command.atendeeservice;

import javax.persistence.EntityNotFoundException;

import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.command.demand.Command;
import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.repository.AtendeeRepository;

public class GetOne implements Command {
	private AtendeeRepository repository;
	private int id;

	@Override
	public Object run() {
		try {
			Atendee atendee = repository.getOne(this.id);
			atendee.getId();
			return atendee;

		} catch (JpaObjectRetrievalFailureException e) {
			throw new MedievalBankException("Unknown Atendee id: " + id);
		} catch (EntityNotFoundException e) {
			throw new MedievalBankException("Atendee id not found: " + id);
		}

	}

}
