package br.ufpb.dcx.esa.medievalbank.service;

import static org.junit.Assert.assertEquals;

import static br.ufpb.dcx.esa.medievalbank.service.AtendeeServiceTestHelper.*;
import static br.ufpb.dcx.esa.medievalbank.service.DemandServiceTestHelper.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class BurgosAgencyTest {

	@Autowired
	private AgencyService agencyService;

	@Autowired
	private AtendeeService atendeeService;

	@Autowired
	private DemandService demandService;

	@Test
	public void initialConfiguration() {

		agencyService.setName("Burgosland");
		assertEquals("Burgosland", agencyService.getName());

		agencyService.setManager("Joseph");
		assertEquals("Joseph", agencyService.getManager());

	}

	@Test
	public void agencyStatusWhithoutAtendee() {
		String result = agencyService.getStatus();
		assertEquals("Atendees: []\n" + "Queue: []", result);

	}

	@Test
	@Transactional
	public void agencyStatusWithOneAtendee() {

		createAtendee(atendeeService, "A1");
		String result = agencyService.getStatus();
		assertEquals("Atendees: [A1]\n" + "Queue: []", result);

	}

	@Test
	@Transactional
	public void agencyStatusWithThreeAtendees() {
		createAtendee(atendeeService, "A1");
		createAtendee(atendeeService, "A2");
		createAtendee(atendeeService, "A3");
		String result = agencyService.getStatus();
		assertEquals("Atendees: [A1, A2, A3]\n" + "Queue: []", result);

	}

	@Test
	@Transactional
	public void agencyStatusWithOneDemand() {
		createDemand(demandService, "D1");
		String result = agencyService.getStatus();
		assertEquals("Atendees: []\n" + "Queue: [D1]", result);
	}

	@Test
	@Transactional
	public void agencyStatusWithTickAndQueue() {
		createDemand(demandService, "D1");
		createDemand(demandService, "D2");
		createDemand(demandService, "D3");
		String result = agencyService.getStatus() + "\n" + agencyService.getTick();

		assertEquals("Atendees: []\n" + "Queue: [D1, D2, D3]\n" + 0, result);

		agencyService.increaseTick();
		result = agencyService.getStatus() + "\n" + agencyService.getTick();
		;

		assertEquals("Atendees: []\n" + "Queue: [D1, D2, D3]\n" + 1, result);

		agencyService.increaseTick();
		result = agencyService.getStatus() + "\n" + agencyService.getTick();
		;

		assertEquals("Atendees: []\n" + "Queue: [D1, D2, D3]\n" + 2, result);

	}

	@Test
	@Transactional
	public void agencyStatusWithTick() {

		int tick = agencyService.getTick();
		String result = agencyService.getStatus();
		assertEquals("Atendees: []\n" + "Queue: []", result);
		assertEquals(0, tick);

		agencyService.increaseTick();
		tick = agencyService.getTick();
		assertEquals("Atendees: []\n" + "Queue: []", result);
		assertEquals(1, tick);

		agencyService.increaseTick();
		tick = agencyService.getTick();
		assertEquals("Atendees: []\n" + "Queue: []", result);
		assertEquals(2, tick);

	}

	@Test
	@Transactional
	public void agencyStatusWithTick_QueueAndAtendee() {

		createAtendee(atendeeService, "A1");
		createDemand(demandService, "D1");

		String status = agencyService.getStatus();
		int tick = agencyService.getTick();
		assertEquals("Atendees: [A1]\n" + "Queue: [D1]", status);
		assertEquals(tick, 0);

		agencyService.increaseTick();
		tick = agencyService.getTick();
		status = agencyService.getStatus();
		assertEquals("Atendees: [A1->D1]\n" + "Queue: []", status);
		assertEquals(tick, 1);
	}

	@Test
	@Transactional
	public void fifo_competitionBetweenTwoDemands() {
		createAtendee(atendeeService, "A1");
		createDemand(demandService, "D1");
		createDemand(demandService, "D2");

		agencyService.increaseTick();
		agencyService.finalizeDemandAtTheNextTick("D1");
		String status = agencyService.getStatus();
		assertEquals("Atendees: [A1->D1]\n" + "Queue: [D2]", status);

		agencyService.increaseTick();
		status = agencyService.getStatus();
		assertEquals("Atendees: [A1->D2]\n" + "Queue: []", status);
	}

	@Test
	@Transactional
	public void fifo_competitionBetweenDemandsFromDifferentStart() {
		createAtendee(atendeeService, "A1");
		createDemand(demandService, "D1");

		AgencyServiceTestHelper.increaseTick(agencyService, 2);
		createDemand(demandService, "D2");

		String status = agencyService.getStatus();
		assertEquals("Atendees: [A1->D1]\n" + "Queue: [D2]", status);

		agencyService.finalizeDemandAtTheNextTick("D1");
		agencyService.increaseTick();
		status = agencyService.getStatus();
		assertEquals("Atendees: [A1->D2]\n" + "Queue: []", status);
	}

	@Test
	@Transactional
	public void fifo_finalizeDemandInQueue() {
		createAtendee(atendeeService, "A1");
		createDemand(demandService, "D1");
		createDemand(demandService, "D2");
		createDemand(demandService, "D3");

		agencyService.finalizeDemandAtTheNextTick("D2");
		agencyService.increaseTick();
		String status = agencyService.getStatus();
		assertEquals("Atendees: [A1->D1]\n" + "Queue: [D3]", status);

		agencyService.finalizeDemandAtTheNextTick("D3");
		agencyService.increaseTick();
		status = agencyService.getStatus();
		assertEquals("Atendees: [A1->D1]\n" + "Queue: []", status);
	}

	@Test
	@Transactional
	public void fifo_competitionBetweenFourDemandsWithTwoAtendee() {
		createAtendee(atendeeService, "A1");
		createAtendee(atendeeService, "A2");
		createDemand(demandService, "D1");
		createDemand(demandService, "D2");
		createDemand(demandService, "D3");
		createDemand(demandService, "D4");

		agencyService.increaseTick();
		agencyService.increaseTick();
		String status = agencyService.getStatus();
		assertEquals("Atendees: [A1->D1, A2->D2]\n" + "Queue: [D3, D4]", status);
	}

	@Test
	@Transactional
	public void fifo_removeDemandFromTheSecondAtendee() {
		createAtendee(atendeeService, "A1");
		createAtendee(atendeeService, "A2");
		createDemand(demandService, "D1");
		createDemand(demandService, "D2");
		createDemand(demandService, "D3");
		createDemand(demandService, "D4");

		agencyService.increaseTick();
		agencyService.finalizeDemandAtTheNextTick("D2");
		agencyService.increaseTick();

		String status = agencyService.getStatus();
		assertEquals("Atendees: [A1->D1, A2->D3]\n" + "Queue: [D4]", status);
	}

}
