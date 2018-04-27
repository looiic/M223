package ch.gibm.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.hibernate.dialect.lock.OptimisticEntityLockException;

import com.sun.faces.context.flash.ELFlash;

import ch.gibm.dao.EntityManagerHelper;
import ch.gibm.entity.Language;
import ch.gibm.entity.Origin;
import ch.gibm.entity.Person;
import ch.gibm.facade.OriginFacade;
import ch.gibm.facade.PersonFacade;

@ViewScoped
@ManagedBean
public class PersonBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SELECTED_PERSON = "selectedPerson";

	private Language language;
	private Person person;
	private Person personWithLanguages;
	private Person personWithLanguagesForDetail;

	@ManagedProperty(value = "#{languageBean}")
	private LanguageBean languageBean;

	@ManagedProperty(value = "#{originBean}")
	private OriginBean originBean;

	private Origin origin;
	private Person personWithOrigins;
	private Person personWithOriginsForDetail;

	private List<Person> persons;
	private PersonFacade personFacade;

	public void createPerson() {
		try {
			getPersonFacade().createPerson(person);
			closeDialog();
			displayInfoMessageToUser("Created with success");
			loadPersons();
			resetPerson();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem occurred while saving. Try again later");
			e.printStackTrace();
		}
	}

	public void updatePerson() {
		try {
			getPersonFacade().updatePerson(person);
			closeDialog();
			displayInfoMessageToUser("Updated with success");
			loadPersons();
			resetPerson();
		} catch (OptimisticEntityLockException optimisticLockeException) {
			// redirect to error page
			redirectOnConcurrencyException();
			optimisticLockeException.printStackTrace();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem occurred while updating. Try again later");
			e.printStackTrace();
		}
	}

	public void deletePerson() {
		try {
			getPersonFacade().deletePerson(person);
			closeDialog();
			displayInfoMessageToUser("Deleted with success");
			loadPersons();
			resetPerson();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem occurred while removing. Try again later");
			e.printStackTrace();
		}
	}

	public void addLanguageToPerson() {
		try {
			getPersonFacade().addLanguageToPerson(language.getId(), personWithLanguages.getId());
			closeDialog();
			displayInfoMessageToUser("Added with success");
			reloadPersonWithLanguages();
			resetLanguage();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem occurred while saving. Try again later");
			e.printStackTrace();
		}
	}

	public void removeLanguageFromPerson() {
		try {
			getPersonFacade().removeLanguageFromPerson(language.getId(), personWithLanguages.getId());
			closeDialog();
			displayInfoMessageToUser("Removed with success");
			reloadPersonWithLanguages();
			resetLanguage();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem occurred while removing. Try again later");
			e.printStackTrace();
		}
	}

	public Person getPersonWithLanguages() {
		if (personWithLanguages == null) {
			person = (Person) ELFlash.getFlash().get(SELECTED_PERSON);
			personWithLanguages = getPersonFacade().findPersonWithAllLanguages(person.getId());
		}

		return personWithLanguages;
	}

	public void setPersonWithLanguagesForDetail(Person person) {
		personWithLanguagesForDetail = getPersonFacade().findPersonWithAllLanguages(person.getId());
	}

	public Person getPersonWithLanguagesForDetail() {
		if (personWithLanguagesForDetail == null) {
			personWithLanguagesForDetail = new Person();
			personWithLanguagesForDetail.setLanguages(new ArrayList<Language>());
		}

		return personWithLanguagesForDetail;
	}

	public void resetPersonWithLanguagesForDetail() {
		personWithLanguagesForDetail = new Person();
	}

	public String editPersonLanguages() {
		ELFlash.getFlash().put(SELECTED_PERSON, person);
		return "/pages/public/person/personLanguages/personLanguages.xhtml";
	}

	// OOOOOOOOORIIIIGIIIIIIIIIIIIIINNNNNNNNNNNSSSSSSSSSSSSSSSSSSSSSS
	public void addOriginToPerson() {
		try {
			getPersonFacade().addOriginToPerson(origin.getId(), personWithOrigins.getId());
			closeDialog();
			displayInfoMessageToUser("Added with success");
			reloadPersonWithOrigins();
			resetOrigin();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem occurred while saving. Try again later");
			e.printStackTrace();
		}
	}

	public void removeOriginFromPerson() {
		try {
			getPersonFacade().removeOriginFromPerson(origin.getId(), personWithOrigins.getId());
			closeDialog();
			displayInfoMessageToUser("Removed with success");
			reloadPersonWithOrigins();
			resetOrigin();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem occurred while removing. Try again later");
			e.printStackTrace();
		}
	}

	public Person getPersonWithOrigins() {
		if (personWithOrigins == null) {
			person = (Person) ELFlash.getFlash().get(SELECTED_PERSON);
			personWithOrigins = getPersonFacade().findPersonWithAllOrigins(person.getId());
		}

		return personWithOrigins;
	}

	public void setPersonWithOriginsForDetail(Person person) {
		personWithOriginsForDetail = getPersonFacade().findPersonWithAllOrigins(person.getId());
	}

	public Person getPersonWithOriginsForDetail() {
		if (personWithOriginsForDetail == null) {
			personWithOriginsForDetail = new Person();
			personWithOriginsForDetail.setOrigins(new ArrayList<Origin>());
		}

		return personWithOriginsForDetail;
	}

	public void resetPersonWithOriginsForDetail() {
		personWithOriginsForDetail = new Person();
	}

	public String editPersonOrigins() {
		ELFlash.getFlash().put(SELECTED_PERSON, person);
		return "/pages/public/person/personOrigin/personOrigin.xhtml";
	}

	public PersonFacade getPersonFacade() {
		if (personFacade == null) {
			personFacade = new PersonFacade();
		}

		return personFacade;
	}

	public Person getPerson() {
		if (person == null) {
			person = new Person();
		}

		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setLanguageBean(LanguageBean languageBean) {
		this.languageBean = languageBean;
	}

	public void setOriginBean(OriginBean originBean) {
		this.originBean = originBean;
	}

	public List<Person> getAllPersons() {
		if (persons == null) {
			loadPersons();
		}

		return persons;
	}

	public List<Language> getRemainingLanguages(String name) {
		// get all languages as copy
		List<Language> res = new ArrayList<Language>(this.languageBean.getAllLanguages());
		// remove already added languages
		res.removeAll(personWithLanguages.getLanguages());
		// remove when name not occurs
		res.removeIf(l -> l.getName().toLowerCase().contains(name.toLowerCase()) == false);
		return res;
	}

	public List<Origin> getRemainingOrigins(String name) {
		// get all languages as copy
		List<Origin> res = new ArrayList<Origin>(this.originBean.getAllOrigins());
		// remove already added languages
		res.removeAll(personWithOrigins.getOrigins());
		// remove when name not occurs
		res.removeIf(l -> l.getName().toLowerCase().contains(name.toLowerCase()) == false);
		return res;
	}

	private void loadPersons() {
		persons = getPersonFacade().listAll();
	}

	public void resetPerson() {
		person = new Person();
	}

	public Language getLanguage() {
		if (language == null) {
			language = new Language();
		}

		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public void resetLanguage() {
		language = new Language();
	}

	public Origin getOrigin() {
		if (origin == null) {
			origin = new Origin();
		}
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public void resetOrigin() {
		origin = new Origin();
	}

	private void reloadPersonWithLanguages() {
		personWithLanguages = getPersonFacade().findPersonWithAllLanguages(person.getId());
	}

	private void reloadPersonWithOrigins() {
		personWithOrigins = getPersonFacade().findPersonWithAllOrigins(person.getId());
	}

	// GENERAL

	public void redirectOnConcurrencyException() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect("http://localhost:8080/JSFApp/pages/public/optimisticLockingException.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (EntityManagerHelper.getEntityManager().getTransaction().isActive()) {
				EntityManagerHelper.getEntityManager().getTransaction().rollback();
				EntityManagerHelper.closeEntityManager();
			}
		}
	}
}