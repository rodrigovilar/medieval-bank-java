package br.ufpb.dcx.esa.medievalbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.model.Demand;
import br.ufpb.dcx.esa.medievalbank.model.DemandTempoFixo;

@Service
public class AgencyService {
	private String name;
	private String manager;
	private int tick = 0;

	@Autowired
	private AtendeeService atendeeService;

	@Autowired
	private DemandService demandService;
	
	@Autowired
	private DemandTempoFixoService dtfs;

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
	}
	public void finishDemand(DemandTempoFixo d, Atendee atendee) {
		d.setAllocated(true);
		d.setAtendee(atendee);
		if(d.getSize()>=2) d.setSize(d.getSize()-1);
		else dtfs.delete(d);
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
	
	public void createDemandTempoFixo(Demand demand) {
		this.demandService.create(demand);
	}
	
	public void removeDemandWTOfTheAtendee(DemandTempoFixo demand) {
		this.demandService.delete(demand);
	}
	
	public void removeDemandOfTheAtendee(Demand demand) {
		this.demandService.delete(demand);
	}

	public String getStatus() {
		List<Atendee> listOfTheAteendes = atendeeService.getAll();
		List<Demand> listOfTheDemands = demandService.getAllUnallocated();

		return "Atendees: " + listOfTheAteendes + "\n" + "Queue: " + listOfTheDemands;
	}
}
