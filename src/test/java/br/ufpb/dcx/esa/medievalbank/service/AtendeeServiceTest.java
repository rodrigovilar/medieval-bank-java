package br.ufpb.dcx.esa.medievalbank.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtendeeServiceTest {

	@Autowired
	private AtendeeService service;
	
	@Test
	public void t01_createAtendee() {
		Atendee atendee = new Atendee();
		atendee.setName("A name");
		Atendee createdAtendee = service.create(atendee);
		
		assertNotNull(createdAtendee.getId());
		assertNotNull(createdAtendee.getCreation());
		assertEquals(atendee.getName(), createdAtendee.getName());

		Atendee searchedAtendee = service.getOne(createdAtendee.getId());
		assertEquals(createdAtendee, searchedAtendee);
	}
	
	@Test
	public void t02_createAtendeeWithoutName() {
		Atendee atendee = new Atendee();
		try {
			service.create(atendee);
			fail("Accepted atendee without name");
		} catch (MedievalBankException e) {
			assertEquals("Name is mandatory", e.getMessage());
		}
	}
	
	@Test
	public void t03_atendeeNameDuplicated() {
		Atendee atendee1 = new Atendee();
		atendee1.setName("A name");
		service.create(atendee1);

		Atendee atendee2 = new Atendee();
		atendee2.setName("A name"); // The same name!
		try {
			service.create(atendee2);
			fail("Accepted atendee with duplicated name");
		} catch (MedievalBankException e) {
			assertEquals("Atendee name cannot be duplicated", e.getMessage());
		}
	}
}
