package br.ufpb.dcx.esa.medievalbank.service;

import static org.junit.Assert.assertEquals;
import static br.ufpb.dcx.esa.medievalbank.service.AtendeeServiceTestHelper.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtendeeServiceTest {
	
	public static final String EXAMPLE_NAME = "A Name";

	private static final String EXAMPLE_EMAIL = "a@a.com";

	@Autowired
	private AtendeeService service;
	
	@Test
	public void t01_createAtendee() {
		Atendee createdAtendee = createAtendee(service, EXAMPLE_NAME);
				
		validateAtendee(EXAMPLE_NAME, null, createdAtendee);

		Atendee searchedAtendee = service.getOne(createdAtendee.getId());
		assertEquals(createdAtendee, searchedAtendee);
	}

	@Test
	public void t02_createAtendeeWithoutName() {
		Atendee atendee = new Atendee();
		String failMessage = "Test failed because the system accepted to create atendee without name";
		String expectedExceptionMessage = "Name is mandatory";
		tryCreateAtendeeWithError(service, atendee, failMessage, expectedExceptionMessage);
	}

	@Test
	public void t03_atendeeNameDuplicated() {
		createAtendee(service, EXAMPLE_NAME);

		Atendee atendee2 = new Atendee();
		atendee2.setName(EXAMPLE_NAME);  // The same name!
				
		String failMessage = "Test failed because the system accepted to create atendee with duplicated name";
		String expectedExceptionMessage = "Atendee name cannot be duplicated";
		tryCreateAtendeeWithError(service, atendee2, failMessage, expectedExceptionMessage);
	}
	
	@Test
	public void t04_createAtendeeWithAutomaticFields() {
		Atendee atendee = new Atendee();
		atendee.setName(EXAMPLE_NAME); 
		atendee.setId(123);

		String failMessage = "Test failed because the system accepted to create atendee with id already set";
		String expectedExceptionMessage = "Atendee id cannot be set";
		tryCreateAtendeeWithError(service, atendee, failMessage, expectedExceptionMessage);

		Atendee atendee2 = new Atendee();
		atendee2.setName(EXAMPLE_NAME); 
		atendee2.setCreation(new Date());
		
		failMessage = "Test failed because the system accepted to create atendee with creation already set";
		expectedExceptionMessage = "Atendee creation cannot be set";
		tryCreateAtendeeWithError(service, atendee, failMessage, expectedExceptionMessage);
	}
	
	@Test
	public void t05_createAtendeeWithInvalidEmail() {
		Atendee atendee = new Atendee();
		atendee.setName(EXAMPLE_NAME); 
		atendee.setEmail("sdsdfa.sds#");

		String failMessage = "Test failed because the system accepted to create atendee with invalid e-mail format";
		String expectedExceptionMessage = "Atendee e-mail format is invalid";
		tryCreateAtendeeWithError(service, atendee, failMessage, expectedExceptionMessage);
		
		
		atendee.setEmail("sdsdfa@@gmail.com");
		tryCreateAtendeeWithError(service, atendee, failMessage, expectedExceptionMessage);
		
		atendee.setEmail("sdsdfa#gmail.com");
		tryCreateAtendeeWithError(service, atendee, failMessage, expectedExceptionMessage);
		
		atendee.setEmail("sdsdfa@gmail");
		tryCreateAtendeeWithError(service, atendee, failMessage, expectedExceptionMessage);
	}
	
	@Test
	public void t06_updateAtendee() {
		Atendee createdAtendee = createAtendee(service, EXAMPLE_NAME, EXAMPLE_EMAIL);
		
		String otherName = "Other Name";
		String otherEmail = "other@email.com";

		createdAtendee.setName(otherName);
		createdAtendee.setEmail(otherEmail);
		
		Atendee updatedAtendee = service.update(createdAtendee);
		validateAtendee(otherName, otherEmail, updatedAtendee);
		assertEquals(createdAtendee.getId(), updatedAtendee.getId());
		assertEquals(createdAtendee.getCreation(), updatedAtendee.getCreation());
		
		Atendee searchedAtendee = service.getOne(updatedAtendee.getId());
		assertEquals(updatedAtendee, searchedAtendee);
	}

}
