package br.ufpb.dcx.esa.medievalbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpb.dcx.esa.medievalbank.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Boolean existsByUsername(String username);
	
	Boolean existsByPassword(String password);
	
	User findByUsername(String  username);
}
