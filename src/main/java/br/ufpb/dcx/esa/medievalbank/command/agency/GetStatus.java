package br.ufpb.dcx.esa.medievalbank.command.agency;

import java.util.List;

import br.ufpb.dcx.esa.medievalbank.command.demand.Command;
import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.model.Demand;
import br.ufpb.dcx.esa.medievalbank.service.AtendeeService;
import br.ufpb.dcx.esa.medievalbank.service.DemandService;

public class GetStatus implements Command {
	private AtendeeService atendeeService;
	private DemandService demandService;
	@Override
	public Object run() {
		List<Atendee> listOfTheAteendes = atendeeService.getAll();
		List<Demand> listOfTheDemands = demandService.getAllUnallocated();

		return "Atendees: " + listOfTheAteendes + "\n" + "Queue: " + listOfTheDemands;
	}

}
