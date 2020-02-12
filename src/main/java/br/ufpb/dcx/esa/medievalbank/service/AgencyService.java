package br.ufpb.dcx.esa.medievalbank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.model.Demand;

@Service
public class AgencyService {
	private String name;
	private String manager;
	private int tick = 0;

	@Autowired
	private AtendeeService atendeeService;

	@Autowired
	private DemandService demandService;

	public DemandService getDemandService() {
		return demandService;
	}

	public void setDemandService(DemandService demandService) {
		this.demandService = demandService;
	}

	public void resetTick() {
		this.tick = 0;
	}

	public void increaseTick() {
		this.tick++;
	}

	public int getTick() {
		return this.tick;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManager() {
		return this.manager;
	}

	public void createDemand(Demand demand) {
		this.demandService.create(demand);
	}

	public void removeDemandOfTheAtendee(Demand demand) {
		this.demandService.delete(demand);
	}

	public void setDemandToAtendee(Demand demand, int atendeeID) {
		Atendee atendee = atendeeService.getOne(atendeeID);
		atendee.setDemand(demand);
		atendeeService.update(atendee);

		demand.setAllocated(true);
		demand.setAtendee(atendee);
		demandService.update(demand);
	}

	public String getStatusWhithTicks() {
		List<Atendee> listOfTheAteendes = atendeeService.getAll();
		List<Demand> listOfTheDemands = demandService.getAll();

		return "Atendees: " + listOfTheAteendes + "\n" + "Queue: " + listOfTheDemands + "\n" + "Tick must return: "
				+ this.getTick();
	}

	public String getStatus() {
		List<Atendee> listOfTheAteendes = atendeeService.getAll();

		List<Demand> listOfTheDemands = demandService.getAllUnallocated();

		return "Atendees: " + listOfTheAteendes + "\n" + "Queue: " + listOfTheDemands;
	}
}
