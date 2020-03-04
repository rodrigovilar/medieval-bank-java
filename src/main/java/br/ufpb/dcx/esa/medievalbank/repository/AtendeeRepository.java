package br.ufpb.dcx.esa.medievalbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpb.dcx.esa.medievalbank.model.Atendee;

public interface AtendeeRepository extends JpaRepository<Atendee, Integer> {

	Boolean existsByName(String name);

	List<Atendee> findByNameContaining(String name);

	List<Atendee> findByDemandIsNull();

	Atendee findByDemandName(String name);
}
