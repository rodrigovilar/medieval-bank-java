package br.ufpb.dcx.esa.medievalbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpb.dcx.esa.medievalbank.model.Demand;
import br.ufpb.dcx.esa.medievalbank.model.DemandTempoFixo;

public interface DemandTempoFixoRepository  extends JpaRepository<Demand, Integer> {

	Boolean existsByName(String name);
    public List<DemandTempoFixo> findByAllUnallocated();

}
