package ch.gibm.dao;

import java.util.HashMap;
import java.util.Map;

import ch.gibm.entity.User;

public class UserDAO extends GenericDAO<User> {

	private static final long serialVersionUID = 1L;

	public UserDAO() {
		super(User.class);
	}

	public void delete(User user) {
        	super.delete(user.getId(), User.class);
	}

	public User getUserByName(String name) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", name);
	//	return super.findOneResult("Select u from user u where u.user_name = :name", parameters);
		return super.findOneResult(User.GET_USER_BY_NAME, parameters);
	}
	
	public User getUserAdminByName(String name) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("admin", true);
		parameters.put("name", name);
		
	//	return super.findOneResult("Select admin from user where u.user_name = :name and admin = 1", parameters);
		return super.findOneResult(User.GET_USER_ADMIN_BY_NAME, parameters);
	}
}
