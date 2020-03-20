package br.ufpb.dcx.esa.medievalbank.command.demand;



import br.ufpb.dcx.esa.medievalbank.repository.DemandRepository;

public class GetAll implements Command {
	private DemandRepository repository;

	@Override
	public Object run() {		
		return repository.findAll();
	}
}