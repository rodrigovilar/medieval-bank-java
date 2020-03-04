package br.ufpb.dcx.esa.medievalbank.service;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.repository.AtendeeRepository;

@Service
public class AtendeeService {

	@Autowired
	private AtendeeRepository repository;

	public boolean matchersRegex(String email) {
		final String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		java.util.regex.Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public Atendee create(Atendee atendee) {

		if (atendee.getName() == null)
			throw new MedievalBankException("Name is mandatory");

		validateDuplicatedName(atendee);
		Integer id = atendee.getId();// a conversão de um int null para integer gera um inteiro

		if (id != null && id != 0)
			throw new MedievalBankException("Atendee id cannot be set");

		if (atendee.getCreation() != null)
			throw new MedievalBankException("Atendee creation date cannot be set");
		validateEmail(atendee);

		atendee.setCreation(new Date());
		return repository.save(atendee);
	}

	public Atendee update(Atendee atendee) {

		Atendee oldAtendee = getOne(atendee.getId());

		nullSafeEquals(oldAtendee.getSSN(), atendee.getSSN(), "Atendee SSN is immutable");
		nullSafeEquals(oldAtendee.getCreation(), atendee.getCreation(), "Atendee creation date cannot be changed");

		validateEmail(atendee);

		if (atendee.getName() == null) {
			throw new MedievalBankException("Name is mandatory");

		} else {
			if (!(oldAtendee.getName().equals(atendee.getName()))) {
				validateDuplicatedName(atendee);
			}
		}

		return repository.save(atendee);

	}

	private void nullSafeEquals(Object obj1, Object obj2, String message) {
		if (obj1 == null) {
			if (obj2 != null) {
				throw new MedievalBankException(message);
			}

		} else { // obj1 != null
			if (obj2 == null) {
				throw new MedievalBankException(message);

			} else { // oldSsn != null && newSsn != null
				if (!(obj1.equals(obj2))) {
					throw new MedievalBankException(message);
				}
			}
		}
	}

	public List<Atendee> getAll() {
		return repository.findAll();
	}

	public Atendee getAtendeeByDemandName(String name) {
		return this.repository.findByDemandName(name);
	}

	public List<Atendee> getAllAtendeesWithoutDemand() {
		return repository.findByDemandIsNull();
	}

	public List<Atendee> findByName(String name) {
		return repository.findByNameContaining(name);
	}

	private void validateDuplicatedName(Atendee atendee) {
		if (repository.existsByName(atendee.getName())) {
			throw new MedievalBankException("Atendee name cannot be duplicated");
		}
	}

	private void validateEmail(Atendee atendee) {
		if (atendee.getEmail() != null) {
			if (!matchersRegex(atendee.getEmail())) {
				throw new MedievalBankException("Atendee e-mail format is invalid");
			}
		}
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

	public void delete(Atendee atendee) {
		if (atendee == null) {
			throw new MedievalBankException("Null atendee");
		}

		if (!(repository.existsById(atendee.getId()))) {
			throw new MedievalBankException("Atendee id not found: " + atendee.getId());
		}

		repository.delete(atendee);

	}
}
