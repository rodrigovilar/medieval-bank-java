package br.ufpb.dcx.esa.medievalbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpb.dcx.esa.medievalbank.model.Demand;

public interface DemandRepository extends JpaRepository<Demand, Integer> {

}
