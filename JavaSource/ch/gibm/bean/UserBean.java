package ch.gibm.bean;

import java.io.Serializable;
import java.security.Principal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ch.gibm.entity.User;
import ch.gibm.facade.UserFacade;

@ViewScoped
@ManagedBean
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private User user;
	private UserFacade userFacade = new UserFacade();


	public boolean isAdmin() {
		userFacade.updateUser(getUser());
		return this.getUser() != null ? user.isAdmin() : false;
	}

	public boolean isDefaultUser() {
		userFacade.updateUser(getUser());
		return this.getUser() != null ? user.isUser() : false;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/pages/protected/index?faces-redirect=true";
	}
	
	public void updateUser() {
			userFacade.updateUser(user);
	}

	public User getUser() {
		if (user == null) {
			Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

			if (principal != null) {
				UserFacade userFacade = new UserFacade();
				user = userFacade.getUserByName(principal.getName());
			}
		}
		return user;
	}
}