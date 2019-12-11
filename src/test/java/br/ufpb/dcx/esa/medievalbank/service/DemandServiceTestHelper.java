package br.ufpb.dcx.esa.medievalbank.service;

import br.ufpb.dcx.esa.medievalbank.model.Demand;

public class DemandServiceTestHelper {

	static Demand createDemand(DemandService service, String aName) {

		Demand demand1 = new Demand();
		demand1.setName(aName);
		return service.create(demand1);
	}
}
