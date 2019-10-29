package br.ufpb.dcx.esa.medievalbank.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;

public class AtendeeServiceTestHelper {

	static void validateCreatedAtendee(String aName, Atendee createdAtendee) {
		assertNotNull(createdAtendee.getId());
		assertNotNull(createdAtendee.getCreation());
		assertEquals(aName, createdAtendee.getName());
	}

	static void tryCreateAtendeeWithError(AtendeeService service, Atendee atendee, String failMessage, String expectedExceptionMessage) {
		try {
			service.create(atendee);
			fail(failMessage);
		} catch (MedievalBankException e) {
			assertEquals(expectedExceptionMessage, e.getMessage());
		}
	}

	static Atendee createAtendee(AtendeeService service, String aName) {
		Atendee atendee1 = new Atendee();
		atendee1.setName(aName);
		return service.create(atendee1);
	}

}
