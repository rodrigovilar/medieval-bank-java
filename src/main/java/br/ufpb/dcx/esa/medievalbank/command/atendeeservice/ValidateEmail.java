package br.ufpb.dcx.esa.medievalbank.command.atendeeservice;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.command.demand.Command;
import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.service.AtendeeService;

public class ValidateEmail implements Command {
	private Atendee atendee;
	private AtendeeService as;
	@Override
	public Object run() {
		if (this.atendee.getEmail() != null) {
			if (!as.matchersRegex(atendee.getEmail())) {
				throw new MedievalBankException("Atendee e-mail format is invalid");
			}
		}
		return null;

	}
}
