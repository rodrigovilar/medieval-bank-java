package br.ufpb.dcx.esa.medievalbank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpb.dcx.esa.medievalbank.service.Atendee;

public interface AtendeeRepository extends JpaRepository<Atendee, Integer> {
	
	Boolean existsByName(String name);
	
}
