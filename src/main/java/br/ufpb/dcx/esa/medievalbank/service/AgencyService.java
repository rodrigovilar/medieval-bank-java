package br.ufpb.dcx.esa.medievalbank.service;

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
	public String getStatus() {
		List<Atendee> listOfTheAteendes = atendeeService.getAll();
		System.out.println(listOfTheAteendes);
		List<Demand> listOfTheDemands = demandService.getAll();
		System.out.println(listOfTheDemands);
		
		return "Atendees: "+ listOfTheAteendes+"\n" + 
		"Queue: "+ listOfTheDemands+"\n"+"Tick must return: "+this.getTick();
	}
	
	public String getStatusWhithoutTicks() {
		List<Atendee> listOfTheAteendes = atendeeService.getAll();
		System.out.println(listOfTheAteendes);
		List<Demand> listOfTheDemands = demandService.getAll();
		System.out.println(listOfTheDemands);
		
		return "Atendees: "+ listOfTheAteendes+"\n" + 
		"Queue: "+ listOfTheDemands;
	}
}
