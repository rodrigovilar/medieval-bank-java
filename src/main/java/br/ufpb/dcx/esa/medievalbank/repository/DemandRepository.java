package br.ufpb.dcx.esa.medievalbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpb.dcx.esa.medievalbank.model.Atendee;

public interface DemandRepository extends JpaRepository<Atendee, Integer> {

}
