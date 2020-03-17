package br.ufpb.dcx.esa.medievalbank.service;

import static org.junit.Assert.assertEquals;
import static br.ufpb.dcx.esa.medievalbank.service.SecurityServiceTestHelper.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.ufpb.dcx.esa.medievalbank.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityServiceTest {
	
	@Autowired
	private SecurityService service;
	
	@Autowired
	private UserService userService;
	
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
	@WithMockUser(username = "john", roles = { "MANAGER", "SYSTEM" })
	@Transactional
	public void rolePermissionSuccessfully() {
		
		System.out.println(userService.getRoles().toString());
	}

}
