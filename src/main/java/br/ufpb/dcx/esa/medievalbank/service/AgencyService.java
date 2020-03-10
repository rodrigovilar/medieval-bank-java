package br.ufpb.dcx.esa.medievalbank.service;

import java.util.List;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.command.Command;
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

	@Autowired
	private AtendeeService atendeeService;

	@Autowired
	private DemandService demandService;

	private Logger logger;

	public void execute(Command command){
		command.setAgencyService(this);
		this.logger.trace(String.format("Executing %s", command.getDescription()));
		try {
			command.execute();
		} catch (Exception e) {
			e.printStackTrace();
			this.logger.error(String.format("Error executing %s, exception message was %s", command.getDescription(), e.getMessage()));
		}
		this.logger.trace(String.format("Executed %s", command.getDescription()));
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

	public Atendee addAttendee(Atendee atendee) {
		try {
			Atendee att = this.atendeeService.create(atendee);
			return att;
		} catch (MedievalBankException e) {
			throw e;
		}
	}

	public void removeAttendee(Atendee atendee) {
		try {
			this.logger.info("Trying to delete attendee");
			this.atendeeService.delete(atendee);
			this.logger.success("Attendee deleted");
		} catch (Exception e){
			this.logger.error(e.getMessage());
			throw e;
		}
	}

	public Demand createDemand(Demand demand) {
		return this.demandService.create(demand);
	}

	public void removeDemandOfTheAtendee(Demand demand) {
		this.demandService.delete(demand);
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

    public void removeDemand(Demand demand) {
		this.demandService.delete(demand);
    }
}
