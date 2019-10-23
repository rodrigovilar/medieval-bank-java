package br.ufpb.dcx.esa.medievalbank.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

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

	@Test
	public void t02_createAtendeeWithoutName() {
		Atendee atendee = new Atendee();
		String failMessage = "Test failed because the system accepted to create atendee without name";
		String expectedExceptionMessage = "Name is mandatory";
		tryCreateAtendeeWithError(atendee, failMessage, expectedExceptionMessage);
	}

	@Test
	public void t03_atendeeNameDuplicated() {
		String aName = "A name";
		createAtendee(aName);

		Atendee atendee2 = new Atendee();
		atendee2.setName(aName);  // The same name!
				
		String failMessage = "Test failed because the system accepted to create atendee with duplicated name";
		String expectedExceptionMessage = "Atendee name cannot be duplicated";
		tryCreateAtendeeWithError(atendee2, failMessage, expectedExceptionMessage);
	}
	
	@Test
	public void t04_createAtendeeWithAutomaticFields() {
		String aName = "A name";

		Atendee atendee = new Atendee();
		atendee.setName(aName); 
		atendee.setId(123);

		String failMessage = "Test failed because the system accepted to create atendee with id already set";
		String expectedExceptionMessage = "Atendee id cannot be set";
		tryCreateAtendeeWithError(atendee, failMessage, expectedExceptionMessage);

		Atendee atendee2 = new Atendee();
		atendee2.setName(aName); 
		atendee2.setCreation(new Date());
		
		failMessage = "Test failed because the system accepted to create atendee with creation already set";
		expectedExceptionMessage = "Atendee creation cannot be set";
		tryCreateAtendeeWithError(atendee, failMessage, expectedExceptionMessage);
	}


	private void validateCreatedAtendee(String aName, Atendee createdAtendee) {
		assertNotNull(createdAtendee.getId());
		assertNotNull(createdAtendee.getCreation());
		assertEquals(aName, createdAtendee.getName());
	}
	
	private void tryCreateAtendeeWithError(Atendee atendee, String failMessage, String expectedExceptionMessage) {
		try {
			service.create(atendee);
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
