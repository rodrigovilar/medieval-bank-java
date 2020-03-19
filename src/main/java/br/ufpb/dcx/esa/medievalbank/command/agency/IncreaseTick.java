package br.ufpb.dcx.esa.medievalbank.command.agency;

import java.util.List;

import br.ufpb.dcx.esa.medievalbank.command.demand.Command;
import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.model.Demand;
import br.ufpb.dcx.esa.medievalbank.service.AtendeeService;
import br.ufpb.dcx.esa.medievalbank.service.DemandService;

public class IncreaseTick implements Command{
	private int tick;
	private DemandService demandService;
	private AtendeeService atendeeService;
	@Override
	public Object run() {
		this.tick++;
		List<Demand> unllocatedDemands = this.demandService.getAllUnallocated();
		List<Atendee> atendees = this.atendeeService.getAllAtendeesWithoutDemand();
		for (Atendee atendee : atendees) {
			if (!unllocatedDemands.isEmpty()) {
				Demand demand = unllocatedDemands.remove(0);
				demand.setAllocated(true);
				demand.setAtendee(atendee);
				atendee.setDemand(demand);

				this.atendeeService.update(atendee);
				this.demandService.update(demand);
			}
		}		
		return null;
	}

}
