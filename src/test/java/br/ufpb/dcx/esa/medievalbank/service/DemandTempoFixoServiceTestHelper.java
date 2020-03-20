package br.ufpb.dcx.esa.medievalbank.service;

import java.util.List;

import org.junit.Test;
import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.model.Demand;
import br.ufpb.dcx.esa.medievalbank.model.DemandTempoFixo;

public class DemandTempoFixoServiceTestHelper {
	private DemandTempoFixoService demand;
	private AgencyService agency;
	private AtendeeService atendeeService;
	private DemandTempoFixoService dtfs;
	
	@Test
	public void t1_decreaseDemands() {
		List<DemandTempoFixo>demands = this.demand.getAllDemandUnallocated();
		DemandTempoFixo d = demands.get(0);
		List<Atendee>atendees = this.atendeeService.getAllAtendeesWithoutDemand();
		Atendee atendee = atendees.get(0);
		this.agency.finishDemand(d, atendee);
	}
}
