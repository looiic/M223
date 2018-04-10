package ch.gibm.dao;

import java.util.HashMap;
import java.util.Map;

import ch.gibm.entity.Person;

public class PersonDAO extends GenericDAO<Person> {

	private static final long serialVersionUID = 1L;

	public PersonDAO() {
		super(Person.class);
	}

	public Person findPersonWithAllLanguages(int personId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("personId", personId);

		return super.findOneResult(Person.FIND_PERSON_BY_ID_WITH_LANGUAGES, parameters);
		//return super.findOneResult("select p from Person p left join fetch p.languages where p.id = :personId", parameters);
		
	}
	
	public Person findPersonWithAllOrigins(int personId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("personId", personId);

		return super.findOneResult(Person.FIND_PERSON_BY_ID_WITH_ORIGINS, parameters);
		//return super.findOneResult("select p from Person p left join fetch p.origins where p.id = :personId", parameters);
		
	}

	public void delete(Person person) {
        	super.delete(person.getId(), Person.class);
	}
}
