package ch.gibm.facade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.dialect.lock.OptimisticEntityLockException;

import ch.gibm.bean.PersonBean;
import ch.gibm.dao.EntityManagerHelper;
import ch.gibm.dao.LanguageDAO;
import ch.gibm.dao.OriginDAO;
import ch.gibm.dao.PersonDAO;
import ch.gibm.entity.Language;
import ch.gibm.entity.Origin;
import ch.gibm.entity.Person;

public class PersonFacade implements Serializable {
	private static final long serialVersionUID = 1L;

	private PersonDAO personDAO = new PersonDAO();
	private LanguageDAO languageDAO = new LanguageDAO();
	private OriginDAO originDAO = new OriginDAO();
	private PersonBean personBean = new PersonBean();

	public void createPerson(Person person) {
		EntityManagerHelper.beginTransaction();
		personDAO.save(person);
		EntityManagerHelper.commitAndCloseTransaction();
	}

	public void updatePerson(Person person) throws OptimisticEntityLockException {
			OptimisticEntityLockException optimisticEntityLockException = new OptimisticEntityLockException(person,
					"The user was updated while opened.");
			EntityManagerHelper.beginTransaction();
			Person persistedPerson = personDAO.find(person.getId());
			persistedPerson.setName(person.getName());
			persistedPerson.setGeschlecht(person.getGeschlecht());
			persistedPerson.setAge(person.getAge());
			if (persistedPerson.getVersion() != person.getVersion()) {
				throw optimisticEntityLockException;
			}
			EntityManagerHelper.commitAndCloseTransaction();
	}

	public void deletePerson(Person person) {
		EntityManagerHelper.beginTransaction();
		Person persistedPersonWithIdOnly = personDAO.findReferenceOnly(person.getId());
		personDAO.delete(persistedPersonWithIdOnly);
		EntityManagerHelper.commitAndCloseTransaction();

	}

	public Person findPerson(int personId) {
		EntityManagerHelper.beginTransaction();
		Person person = personDAO.find(personId);
		EntityManagerHelper.commitAndCloseTransaction();
		return person;
	}

	public List<Person> listAll() {
		EntityManagerHelper.beginTransaction();
		List<Person> result = personDAO.findAll();
		EntityManagerHelper.commitAndCloseTransaction();

		return result;
	}

	public Person findPersonWithAllLanguages(int personId) {
		EntityManagerHelper.beginTransaction();
		Person person = personDAO.findPersonWithAllLanguages(personId);
		EntityManagerHelper.commitAndCloseTransaction();
		return person;
	}

	public void addLanguageToPerson(int languageId, int personId) {
		EntityManagerHelper.beginTransaction();
		Language language = languageDAO.find(languageId);
		Person person = personDAO.find(personId);
		person.getLanguages().add(language);
		language.getPersons().add(person);
		EntityManagerHelper.commitAndCloseTransaction();
	}

	public void removeLanguageFromPerson(int languageId, int personId) {
		EntityManagerHelper.beginTransaction();
		Language language = languageDAO.find(languageId);
		Person person = personDAO.find(personId);
		person.getLanguages().remove(language);
		language.getPersons().remove(person);
		EntityManagerHelper.commitAndCloseTransaction();
	}

	public Person findPersonWithAllOrigins(int personId) {
		EntityManagerHelper.beginTransaction();
		Person person = personDAO.findPersonWithAllOrigins(personId);
		EntityManagerHelper.commitAndCloseTransaction();
		return person;
	}

	public void addOriginToPerson(int originId, int personId) {
		EntityManagerHelper.beginTransaction();
		Origin origin = originDAO.find(originId);
		Person person = personDAO.find(personId);
		person.getOrigins().add(origin);
		origin.getPersons().add(person);
		EntityManagerHelper.commitAndCloseTransaction();
	}

	public void removeOriginFromPerson(int originId, int personId) {
		EntityManagerHelper.beginTransaction();
		Origin origin = originDAO.find(originId);
		Person person = personDAO.find(personId);
		person.getOrigins().remove(origin);
		origin.getPersons().remove(person);
		EntityManagerHelper.commitAndCloseTransaction();
	}
}