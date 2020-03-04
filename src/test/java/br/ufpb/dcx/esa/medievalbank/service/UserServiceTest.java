package br.ufpb.dcx.esa.medievalbank.service;

import static org.junit.Assert.assertEquals;
import static br.ufpb.dcx.esa.medievalbank.service.UserServiceTestHelper.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.ufpb.dcx.esa.medievalbank.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	@Transactional
	public void createUser() {
		User user = new User("ruaanc", "12345");
		User response = userService.create(user);
		assertEquals(user, response);
	}
	
	@Test
	@Transactional
	public void createUserWithExistingUsername() {
		User user = new User("julioc", "12345");
		userService.create(user);
		User userEquals = new User("julioc","12345");
		String failMessage = "Testing failed because the user was created with an existing username.";
		String expectedExceptionMessage = "User username cannot be duplicated";
		tryCreateUserWithError(userService, userEquals, failMessage, expectedExceptionMessage);
	}

}
