package ch.gibm.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String FIND_PERSON_BY_ID_WITH_LANGUAGES = "User.findUserByIdWithLanguages";
	public static final String FIND_PERSON_BY_ID_WITH_ORIGINS = "User.findUserByIdWithOrigins";
	private boolean admin;
	private boolean user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String user_name;
	private String user_pass;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isUser() {
		return user;
	}

	public void setUser(boolean user) {
		this.user = user;
	}
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pass() {
		return user_pass;
	}

	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User user = (User) obj;
			return user.getId() == id;
		}

		return false;
	}
}