package br.ufpb.dcx.esa.medievalbank.command.atendeeservice;

import java.util.Date;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.command.demand.Command;
import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.repository.AtendeeRepository;
import br.ufpb.dcx.esa.medievalbank.service.AtendeeService;

public class CreateAtendee implements Command {
	private Atendee atendee;
	private AtendeeService as;
	private AtendeeRepository repository;

	@Override
	public Object run() {
		if (atendee.getName() == null)
			throw new MedievalBankException("Name is mandatory");

		this.as.validateDuplicatedName(this.atendee);
		Integer id = atendee.getId();// a conversão de um int null para integer gera um inteiro

		if (id != null && id != 0)
			throw new MedievalBankException("Atendee id cannot be set");

		if (atendee.getCreation() != null)
			throw new MedievalBankException("Atendee creation date cannot be set");
		this.as.validateEmail(this.atendee);

		atendee.setCreation(new Date());
		return repository.save(this.atendee);
	}

}
