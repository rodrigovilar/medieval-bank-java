package br.ufpb.dcx.esa.medievalbank.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.EntityNotFoundException;

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
			Atendee atendee = repository.getOne(id);
			atendee.getId();
			return atendee;

		} catch (JpaObjectRetrievalFailureException e) {
			throw new MedievalBankException("Unknown Atendee id: " + id);
		} catch (EntityNotFoundException e) {
			throw new MedievalBankException("Atendee id not found: " + id);			
		}
		
	}

	public Atendee update(Atendee atendee) {
		
		/*if(atendee.getName() == null) {
			if(repository.existsById(atendee.getId()) == false){
				throw new MedievalBankException("Atendee id not found: "+atendee.getId());
			}
			
			throw new MedievalBankException("Name is mandatory");
		}
		else {
			if(repository.existsById(atendee.getId()) == false ) {
				throw new MedievalBankException("Atendee id not found: " + atendee.getId());
			}
		}*/
		

		
		/*if (repository.existsByName(Atendee.getName())) {
			throw new MedievalBankException("Atendee name cannot be duplicated");
		}*/
		Atendee oldAtendee = getOne(atendee.getId());
		
		nullSafeEquals(oldAtendee.getSSN(), atendee.getSSN(), "Atendee SSN is immutable");
		nullSafeEquals(oldAtendee.getCreation(), atendee.getCreation(), "Atendee creation date cannot be changed");
	
		
		if(atendee.getEmail() != null && !matchersRegex(atendee.getEmail())) {
			throw new MedievalBankException("Atendee e-mail format is invalid");
		}
		
		boolean exist = repository.existsById(atendee.getId());
		if(exist) {
			nullSafeEquals(oldAtendee.getId(), atendee.getId(), "Atendee id not found: "+atendee.getId());

		}else{
			throw new MedievalBankException("Atendee id not found: "+atendee.getId());
		}
		
		if(atendee.getName() == null) {
			throw new MedievalBankException("Name is mandatory");
		
		} else {
			if(!(oldAtendee.getName().equals(atendee.getName()))) {
				if (repository.existsByName(atendee.getName())) {
					throw new MedievalBankException("Atendee name cannot be duplicated");
				}
			}
		}
		repository.save(atendee);
		
		return repository.save(atendee);

	}

	private void nullSafeEquals(Object obj1, Object obj2, String message) {
		if (obj1 == null) {
			if (obj2 != null) {
				throw new MedievalBankException(message);
			} 
			
		} else { //obj1 != null
			if (obj2 == null) {
				throw new MedievalBankException(message);
			
			} else { // oldSsn != null && newSsn != null
				if(!(obj1.equals(obj2))) {
					throw new MedievalBankException(message);
				}
			}
		}
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
