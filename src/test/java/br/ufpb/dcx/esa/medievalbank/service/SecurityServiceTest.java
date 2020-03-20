package br.ufpb.dcx.esa.medievalbank.service;

import static br.ufpb.dcx.esa.medievalbank.service.SecurityServiceTestHelper.tryLoginUnsuccessfully;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.ufpb.dcx.esa.medievalbank.command.Command;
import br.ufpb.dcx.esa.medievalbank.command.InsertAttendee;
import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.model.User;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class SecurityServiceTest {
	
	@Autowired
	private SecurityService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private AgencyService agencyService;
	
	public Atendee builAttendee(String name, String email) {
        Atendee atendee = new Atendee();
        atendee.setName(name);
        atendee.setEmail(email);
        return atendee;
    }
	
	@Test
	@Transactional
	public void loginSuccessfully() {
		User user = new User("ruaanc", "12345");
		userService.create(user);
		service.login("ruaanc", "12345");
		assertEquals(user.isLogged(), true);
	}
	
	@Test
	@Transactional
	public void loginUnsuccessfully() {
		User user = new User("francisco", "12345");
		userService.create(user);
		String failMessage = "Test failed because the user was able to log in with the incorrect credentials.";
		String expectedExceptionMessage = "Username or Password is incorrect";
		tryLoginUnsuccessfully(service, "francisco", "123456", failMessage, expectedExceptionMessage);
		tryLoginUnsuccessfully(service, "francis", "12345", failMessage, expectedExceptionMessage);
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
	
	
	

}
