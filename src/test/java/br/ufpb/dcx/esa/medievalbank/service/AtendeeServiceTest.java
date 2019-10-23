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
		String aName = "A name";
		Atendee createdAtendee = createAtendee(aName);
				
		validateCreatedAtendee(aName, createdAtendee);

		Atendee searchedAtendee = service.getOne(createdAtendee.getId());
		assertEquals(createdAtendee, searchedAtendee);
	}

	private void validateCreatedAtendee(String aName, Atendee createdAtendee) {
		assertNotNull(createdAtendee.getId());
		assertNotNull(createdAtendee.getCreation());
		assertEquals(aName, createdAtendee.getName());
	}
	
	@Test
	public void t02_createAtendeeWithoutName() {
		String failMessage = "Test failed because the system accepted to create atendee without name";
		String expectedExceptionMessage = "Name is mandatory";
		tryCreateAtendeeWithError(null, failMessage, expectedExceptionMessage);
	}

	@Test
	public void t03_atendeeNameDuplicated() {
		String aName = "A name";
		createAtendee(aName);

		String failMessage = "Test failed because the system accepted to create atendee with duplicated name";
		String expectedExceptionMessage = "Atendee name cannot be duplicated";
		tryCreateAtendeeWithError(aName, failMessage, expectedExceptionMessage);
	}

	private void tryCreateAtendeeWithError(String aName, String failMessage, String expectedExceptionMessage) {
		try {
			createAtendee(aName);
			fail(failMessage);
		} catch (MedievalBankException e) {
			assertEquals(expectedExceptionMessage, e.getMessage());
		}
	}
	
	private Atendee createAtendee(String aName) {
		Atendee atendee1 = new Atendee();
		atendee1.setName(aName);
		return service.create(atendee1);
	}
}
