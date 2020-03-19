package br.ufpb.dcx.esa.medievalbank.command.demand;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.model.Demand;

public class ValidateDemand implements Command{
	private Demand demand;
	@Override
	public Object run() {
		if (this.demand.getId() == null) {
			throw new MedievalBankException("Demand with null id");
		}
		if (this.demand.getName() == null) {
			throw new MedievalBankException("Demand with null name");
		}
		return null;
	}

}
