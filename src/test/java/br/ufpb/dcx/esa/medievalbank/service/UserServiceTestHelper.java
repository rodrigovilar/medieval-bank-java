package br.ufpb.dcx.esa.medievalbank.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import br.ufpb.dcx.esa.medievalbank.model.User;

public class UserServiceTestHelper {
	
	static void tryCreateUserWithError(UserService service, User user, String failMessage, 
			String expectedExceptionMessage) {
		try {
			service.create(user);
			fail(failMessage);
		}catch(Exception e) {
			assertEquals(expectedExceptionMessage, e.getMessage());
		}
	}
}
