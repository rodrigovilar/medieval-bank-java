package br.ufpb.dcx.esa.medievalbank.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.model.User;
import br.ufpb.dcx.esa.medievalbank.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User create(User user) {
		this.validadeIsNullUsername(user);
		this.validateIsNullPassword(user);
		this.validateDuplicatedUsername(user);
		return repository.save(user);
	}
	
	public User getOne(Integer id) {
		return repository.getOne(id);
	}
	
	private void validateDuplicatedUsername(User user) {
		if(repository.existsByUsername(user.getUsername())) {
			throw new MedievalBankException("User username cannot be duplicated");
		}
	}
	
	private void validadeIsNullUsername(User user) {
		if(user.getUsername() == null) {
			throw new MedievalBankException("Username is mandatory");
		}
	}
	
	private void validateIsNullPassword(User user) {
		if(user.getPassword() == null) {
			throw new MedievalBankException("Password is mandatory");
		}
	}
	
	public boolean getExistsUsername(String username) {
		return repository.existsByUsername(username);
	}
	
	public boolean getExistsPassword(String password) {
		return repository.existsByPassword(password);
	}
	
	public User getUserByUsername(String username) {
		return repository.findByUsername(username);
	}
	
	public Collection<? extends GrantedAuthority> getRoles() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return securityContext.getAuthentication().getAuthorities();
	}
	
}
