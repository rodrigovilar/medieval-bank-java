package br.ufpb.dcx.esa.medievalbank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.model.Demand;
import br.ufpb.dcx.esa.medievalbank.utils.logging.Logger;

@Service
public class AgencyService {
	private String name;
	private String manager;
	private int tick = 0;

	List<Demand> demandsToBeFinalized;

	@Autowired
	private AtendeeService atendeeService;

	@Autowired
	private DemandService demandService;

	private Logger logger;

	@Secured("ROLE_SYSTEM")
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
	
	@Secured("ROLE_MANAGER")
	public Atendee addAttendee(Atendee atendee) {
		try {
			Atendee att = this.atendeeService.create(atendee);
			return att;
		} catch (MedievalBankException e) {
			this.logger.error(e.getMessage());
			throw e;
		}
	}

	@Secured("ROLE_MANAGER")
	public void removeAttendee(Atendee atendee) {
		this.atendeeService.delete(atendee);
	}

	public void createDemand(Demand demand) {
		this.demandService.create(demand);
	}

	public void removeDemandOfTheAtendee(Demand demand) {
		this.demandService.delete(demand);
	}

	@Secured("ROLE_ATENDEE")
	public void finalizeDemandAtTheNextTick(String name) {
		if (isNull(demandsToBeFinalized))
			demandsToBeFinalized = new ArrayList<>();

		Demand demand = this.demandService.getDemandByName(name);
		demandsToBeFinalized.add(demand);
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
	
	@Secured("ROLE_MANAGER")
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	@Secured("ROLE_MANAGER")
	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManager() {
		return this.manager;
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

		return "Atendees: " + listOfTheAteendes + "\n" + "Queue: " + listOfTheDemands;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public Logger getLogger() {
		return logger;
	}
}
