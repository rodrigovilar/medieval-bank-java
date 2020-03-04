package br.ufpb.dcx.esa.medievalbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.dcx.esa.medievalbank.model.User;
import br.ufpb.dcx.esa.medievalbank.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User create(User user) {
		return repository.save(user);
	}
	
	public User getOne(Integer id) {
		return repository.getOne(id);
	}
	
}
