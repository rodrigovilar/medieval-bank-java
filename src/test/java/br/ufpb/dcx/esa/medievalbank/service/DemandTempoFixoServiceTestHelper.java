package br.ufpb.dcx.esa.medievalbank.service;

import java.util.List;

import org.junit.Test;
import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.model.DemandTempoFixo;

public class DemandTempoFixoServiceTestHelper {
	private DemandTempoFixoService demand;
	private AgencyService agency;
	private AtendeeService atendeeService;
	
	@Test
	public void t1_decreaseDemands() {
		List<DemandTempoFixo>demands = this.demand.getAllDemandUnallocated();
		DemandTempoFixo d = demands.get(0);
		List<Atendee>atendees = this.atendeeService.getAllAtendeesWithoutDemand();
		Atendee atendee = atendees.get(0);
		while(d.getSize()>0) this.agency.finishDemand(d, atendee);
		
	}
	@Test
	public void t2_demandsw2Atendees() {
		List<DemandTempoFixo>demands = this.demand.getAllDemandUnallocated();
		DemandTempoFixo d1 = demands.get(0);
		DemandTempoFixo d2 = demands.get(1);
		List<Atendee>atendees = this.atendeeService.getAllAtendeesWithoutDemand();
		Atendee atendee1 = atendees.get(0);
		Atendee atendee2 = atendees.get(1);
		while(d1.getSize()>0) this.agency.finishDemand(d1, atendee1);
		while(d2.getSize()>0) this.agency.finishDemand(d2, atendee2);

	}
}
