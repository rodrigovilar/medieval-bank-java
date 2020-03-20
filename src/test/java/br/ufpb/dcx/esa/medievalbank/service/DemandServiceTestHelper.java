package br.ufpb.dcx.esa.medievalbank.service;

import org.junit.Test;

import br.ufpb.dcx.esa.medievalbank.model.Demand;



public class DemandServiceTestHelper {


	@Test
	public static void createDemand(DemandService service, String aName) {
		Demand demand = new Demand();
		demand.setName(aName);
		service.create(demand);
	}
	
}
