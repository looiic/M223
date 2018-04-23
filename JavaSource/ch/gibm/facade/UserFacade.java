package ch.gibm.facade;

import java.io.Serializable;
import java.util.List;

import ch.gibm.dao.EntityManagerHelper;
import ch.gibm.dao.UserDAO;

import ch.gibm.entity.User;

public class UserFacade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO = new UserDAO();


	public void createUser(User user) {
		EntityManagerHelper.beginTransaction();
		userDAO.save(user);
		EntityManagerHelper.commitAndCloseTransaction();
	}

	public void updateUser(User user) {
		boolean admin = false;
		EntityManagerHelper.beginTransaction();
		User persistedUser = userDAO.find(user.getId());
		persistedUser.setUser_name(user.getUser_name());
		if(userDAO.getUserAdminByName(user.getUser_name()) != null) {
			admin = true;
		}
		persistedUser.setAdmin(admin);
		persistedUser.setUser(user.isUser());
		EntityManagerHelper.commitAndCloseTransaction();
	}
	
	public void deleteUser(User user){
		EntityManagerHelper.beginTransaction();
		User persistedUserWithIdOnly = userDAO.findReferenceOnly(user.getId());
		userDAO.delete(persistedUserWithIdOnly);
		EntityManagerHelper.commitAndCloseTransaction();
		
	}

	public User findUser(int userId) {
		EntityManagerHelper.beginTransaction();
		User user = userDAO.find(userId);
		EntityManagerHelper.commitAndCloseTransaction();
		return user;
	}

	public List<User> listAll() {
		EntityManagerHelper.beginTransaction();
		List<User> result = userDAO.findAll();
		EntityManagerHelper.commitAndCloseTransaction();

		return result;
	}

	public User getUserByName(String name) {
		EntityManagerHelper.beginTransaction();
		User user = userDAO.getUserByName(name);
		EntityManagerHelper.commitAndCloseTransaction();		
		updateUser(user);
		
//		EntityManagerHelper.rollback();
//		EntityManagerHelper.closeEntityManager();

		return user;
	}
}