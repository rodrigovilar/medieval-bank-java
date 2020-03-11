package br.ufpb.dcx.esa.medievalbank.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
	public void login() {
		User user = new User("ruaanc", "12345");
		userService.create(user);
		service.login("ruaanc", "12345");
		assertEquals(user.isLogged(), true);
	}

}
