package br.ufpb.dcx.esa.medievalbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.model.User;

@Service
public class SecurityService {
	
	@Autowired
	private UserService userService;
	
	public void login(String username, String password) {
		boolean existsUsername =  userService.getExistsUsername(username);
		boolean existsPassword = userService.getExistsPassword(password);
		if(!(existsUsername && existsPassword)) {
			throw new MedievalBankException("Username or Password is incorrect");
		}
		User user = userService.getUserByUsername(username);
		user.setLogged(true);
	}
	

}
