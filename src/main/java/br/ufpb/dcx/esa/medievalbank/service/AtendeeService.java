package br.ufpb.dcx.esa.medievalbank.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.dao.AtendeeRepository;

@Service
public class AtendeeService {
	@Autowired
	private AtendeeRepository repository;
	
	
	public boolean matchersRegex(String email) {
		final String regex ="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
	    java.util.regex.Matcher matcher = pattern.matcher(email);
	    return matcher.matches();
	}

	public Atendee create(Atendee atendee){
		
		if(atendee.getName() == null) {
			throw new MedievalBankException("Name is mandatory"); 
		}

		if (repository.existsByName(atendee.getName())) {
			throw new MedievalBankException("Atendee name cannot be duplicated");
		}
		Integer id = atendee.getId();// a conversão de um int null para integer gera um inteiro
		
		if (id != null && id != 0 ) {
			throw new MedievalBankException("Atendee id cannot be set");
		}
		if (atendee.getCreation() != null) {
			throw new MedievalBankException("Atendee creation date cannot be set");
		}
		if(atendee.getEmail() != null) {
			if(!matchersRegex(atendee.getEmail())) {
				throw new MedievalBankException("Atendee e-mail format is invalid");
			}
		}
		
		
		atendee.setCreation(new Date());
		return repository.save(atendee);
	}

	public Atendee getOne(Integer id) {
		
		try {			
			return repository.getOne(id);

		} catch (JpaObjectRetrievalFailureException e) {
			throw new MedievalBankException("Unknown Atendee id: " + id);
		}
		
	}

	public Atendee update(Atendee newAtendee) {
		
		if(newAtendee.getName() == null) {
			throw new MedievalBankException("Name is mandatory");
		}
		if(!matchersRegex(newAtendee.getEmail())) {
			throw new MedievalBankException("Atendee e-mail format is invalid");
		}
		if (repository.existsByName(newAtendee.getName())) {
			throw new MedievalBankException("Atendee name cannot be duplicated");
		}
		Atendee oldAtendee = getOne(newAtendee.getId());
		if(!(oldAtendee.getSSN().equals(newAtendee.getSSN()))) throw new MedievalBankException("Atendee SSN is imutable");
		
		repository.save(newAtendee);
		
		return repository.save(newAtendee);

	}

	public void delete(Atendee atendee) {
		if(atendee == null) {
			throw new MedievalBankException("Null atendee"); 
		}
		
		if(repository.existsById(atendee.getId()) == false){
			throw new MedievalBankException("Atendee id not found: "+atendee.getId());
		}
		
		repository.delete(atendee);
		
	}
	
	public List<Atendee> getAll(){
		return repository.findAll();
	}
	public List<Atendee> findByName(String name){
		return repository.findByNameContaining(name);
	}

}
