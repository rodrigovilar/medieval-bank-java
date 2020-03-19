package br.ufpb.dcx.esa.medievalbank.command.atendeeservice;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.command.demand.Command;
import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.service.AtendeeService;


public class UpdateAtendee implements Command {
	private Atendee atendee;
	private AtendeeService as;
	@Override
	public Object run() {
		Atendee oldAtendee = as.getOne(atendee.getId());

		this.as.nullSafeEquals(oldAtendee.getSSN(), atendee.getSSN(), "Atendee SSN is immutable");
		this.as.nullSafeEquals(oldAtendee.getCreation(), atendee.getCreation(), "Atendee creation date cannot be changed");

		as.validateEmail(atendee);

		if (atendee.getName() == null) {
			throw new MedievalBankException("Name is mandatory");

		} 
		else {
			if (!(oldAtendee.getName().equals(atendee.getName()))) {
				as.validateDuplicatedName(atendee);
			}		
		return null;
		}

	}

}
