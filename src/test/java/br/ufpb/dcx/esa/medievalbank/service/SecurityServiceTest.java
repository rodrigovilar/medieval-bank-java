package br.ufpb.dcx.esa.medievalbank.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.command.Command;
import br.ufpb.dcx.esa.medievalbank.command.InsertAttendee;
import br.ufpb.dcx.esa.medievalbank.model.Atendee;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class SecurityServiceTest {
	
	@Autowired
    private AgencyService agencyService;
	
	public Atendee builAttendee(String name, String email) {
        Atendee atendee = new Atendee();
        atendee.setName(name);
        atendee.setEmail(email);
        return atendee;
    }
	
	public void tryinsertAttendeeWithError(AgencyService service, Atendee atendee, String failMessage,
			String expectedExceptionMessage) {
		try {
			Command insertAttendee = new InsertAttendee(atendee);
			service.execute(insertAttendee);
			fail(failMessage);
		} catch (MedievalBankException e) {
			assertEquals(expectedExceptionMessage, e.getMessage());
		}
	}
	
	@Test
	@WithMockUser(username = "john", roles = { "MANAGER" })
	@Transactional
	public void succesfullyInsertAttendeeWithPermission() {
		Atendee atendee = builAttendee("A1", "a@gmail.com");
		Command insertAttendee = new InsertAttendee(atendee);
		Atendee response = (Atendee) agencyService.execute(insertAttendee);
		assertEquals(atendee, response);
	}
	
	@Test
	@WithMockUser(username = "john", roles = { "SYSTEM" })
	@Transactional
	public void exceptionInsertAttendeeWithoutPermission() {
		Atendee atendee = builAttendee("A1", "a@gmail.com");
		String failMessage = "Test failed because the system accepted insert attendee without permission";
		String expectedExceptionMessage = "User is not allowed";
		tryinsertAttendeeWithError(agencyService, atendee, failMessage, expectedExceptionMessage);
	}
	

}
