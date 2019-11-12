package br.ufpb.dcx.esa.medievalbank.service;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.dao.AtendeeRepository;

@Service
public class AtendeeService {
	@Autowired
	private AtendeeRepository repository;
	

	public Atendee create(Atendee atendee) {
		atendee.setCreation(new Date());
		return repository.save(atendee);
	}

	public Atendee getOne(Integer id) {
		
		return repository.getOne(id);
	}

	public Atendee update(Atendee atendee) {
		
		final String regex ="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
	    java.util.regex.Matcher matcher = pattern.matcher(atendee.getEmail());

		if(atendee.getName() == null) {
			throw new MedievalBankException("Name is mandatory");
		}
		if(!matcher.matches()) {
			throw new MedievalBankException("Atendee e-mail format is invalid");
		}
		
		repository.save(atendee);
		
		return repository.save(atendee);

	}

	public void delete(Atendee atendee) {
		// TODO Auto-generated method stub
		
	}

	public List<Atendee> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Atendee> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
