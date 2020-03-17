package br.ufpb.dcx.esa.medievalbank.service;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.utils.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.model.Demand;

@Service
public class AgencyService {
	private String name;
	private String manager;
	private int tick = 0;
	private int quantum = 3;

	List<Demand> demandsToBeFinalized;

	@Autowired
	private AtendeeService atendeeService;

	@Autowired
	private DemandService demandService;

	private Logger logger;

	public void increaseTick() {
		this.tick++;
		this.finalizeDemand();
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

	public void resetTick() {
		this.tick = 0;
	}

	public Atendee addAttendee(Atendee atendee) {
		try {
			this.logger.info("Trying to create attendee");
			Atendee att = this.atendeeService.create(atendee);
			this.logger.success("Attendee created");
			return att;
		} catch (MedievalBankException e) {
			this.logger.error(e.getMessage());
			throw e;
		}
	}

	public void removeAttendee(Atendee atendee) {
		this.atendeeService.delete(atendee);
	}

	public void createDemand(Demand demand) {
		this.demandService.create(demand);
	}
	
	/*
	 * public void removeDemandOfTheAtendee(String D) { Atendee atendee =
	 * atendeeService.getAtendeeByDemandName(D); Demand demand = new Demand();
	 * demand = atendee.getDemand(); finalizeDemandAtTheNextTick(D); //List<Demand>
	 * demandasFinalizadas = this.demandasFinalizas; this.createDemand(demand);
	 * 
	 * 
	 * }
	 */

	public void finalizeDemandAtTheNextTick(String name) { // finalize a demanda no proximo tick
		if (isNull(demandsToBeFinalized))
			demandsToBeFinalized = new ArrayList<>();

		Demand demand = this.demandService.getDemandByName(name);
		this.demandsToBeFinalized.add(demand);
	}

	private void finalizeDemand() {
		if (isNull(demandsToBeFinalized))
			return;
		for (Demand demand : demandsToBeFinalized) {
			Atendee atendee = this.atendeeService.getAtendeeByDemandName(demand.getName());
			if (!isNull(atendee)) {
				atendee.setDemand(null);
				demand.setAtendee(null);
				this.atendeeService.update(atendee);
				this.demandService.update(demand);
			} else {
				this.demandService.delete(demand);
			}
		}
	}

	private boolean isNull(Object object) {
		return (object == null);
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
	public int getQuantum() {
		return quantum;
	}

	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}

	public DemandService getDemandService() {
		return demandService;
	}

	public void setDemandService(DemandService demandService) {
		this.demandService = demandService;
	}

	public void setAtendeeService(AtendeeService atendeeService) {
		this.atendeeService = atendeeService;
	}

	public AtendeeService getAtendeeService() {
		return atendeeService;
	}

	public String getStatus() {
		List<Atendee> listOfTheAteendes = atendeeService.getAll();
		List<Demand> listOfTheDemands = demandService.getAllUnallocated();

		return "Quantum: " + getQuantum() +"\nAtendees: " + listOfTheAteendes + "\n" + "Queue: " + listOfTheDemands;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public Logger getLogger() {
		return logger;
	}

}
