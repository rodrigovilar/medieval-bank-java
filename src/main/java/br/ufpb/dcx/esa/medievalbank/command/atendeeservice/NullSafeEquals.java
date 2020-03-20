package br.ufpb.dcx.esa.medievalbank.command.atendeeservice;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.command.demand.Command;

public class NullSafeEquals implements Command {
	private Object obj1;
	private Object obj2;
	private String message;
	@Override
	public Object run() {
		if (obj1 == null) {
			if (obj2 != null) {
				throw new MedievalBankException(message);
			}

		} else { // obj1 != null
			if (obj2 == null) {
				throw new MedievalBankException(message);

			} else { // oldSsn != null && newSsn != null
				if (!(obj1.equals(obj2))) {
					throw new MedievalBankException(message);
				}
			}
		}
		return null;
	}

}
