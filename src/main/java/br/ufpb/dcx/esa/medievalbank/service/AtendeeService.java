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
		
		return repository.getOne(id);
	}

	public Atendee update(Atendee atendee) {
		
		if(atendee.getName() == null) {
			throw new MedievalBankException("Name is mandatory");
		}
		if(!matchersRegex(atendee.getEmail())) {
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
