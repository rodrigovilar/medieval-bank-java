package br.ufpb.dcx.esa.medievalbank.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SecurityServiceTestHelper {
	
	static void tryLoginUnsuccessfully(SecurityService service, String username, String password, String failMessage, 
			String expectedExceptionMessage) {
		try {
			service.login(username, password);
			fail(failMessage);
		}catch(Exception e) {
			assertEquals(expectedExceptionMessage, e.getMessage());
		}
		
	}

}
