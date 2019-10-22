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
	
	
}
