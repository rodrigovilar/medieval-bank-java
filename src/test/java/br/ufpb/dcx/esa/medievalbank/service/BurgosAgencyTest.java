package br.ufpb.dcx.esa.medievalbank.service;

import static org.junit.Assert.assertEquals;
import static br.ufpb.dcx.esa.medievalbank.service.AtendeeServiceTestHelper.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BurgosAgencyTest {

	@Autowired
	private AgencyService agencyService;

	@Autowired
	private AtendeeService atendeeService;

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
	public void agencyStatusAfterRemovingAnAtendee() {
		createAtendee(atendeeService, "A1");
		Atendee a2 = createAtendee(atendeeService, "A2");
		createAtendee(atendeeService, "A3");
		atendeeService.delete(atendeeService.getOne(a2.getId()));
		String result = agencyService.getStatus();
		assertEquals("Atendees: [A1 --->>> A3]\n" + "Queue: []", result);

	}

}
