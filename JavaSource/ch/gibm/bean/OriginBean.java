package ch.gibm.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.gibm.entity.Origin;
import ch.gibm.facade.OriginFacade;


@ViewScoped
@ManagedBean(name="originBean")
public class OriginBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Origin origin;
	private List<Origin> origins;
	private OriginFacade originFacade;

	public OriginFacade getOriginFacade() {
		if (originFacade == null) {
			originFacade = new OriginFacade();
		}

		return originFacade;
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

	public void createOrigin() {
		try {
			getOriginFacade().createOrigin(origin);
			closeDialog();
			displayInfoMessageToUser("Created with success");
			loadOrigins();
			resetOrigin();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem occurred while saving. Try again later");
			e.printStackTrace();
		}
	}
	
	public void updateOrigin() {
		try {
			getOriginFacade().updateLanguage(origin);
			closeDialog();
			displayInfoMessageToUser("Updated with success");
			loadOrigins();
			resetOrigin();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem occurred while updating. Try again later");
			e.printStackTrace();
		}
	}
	
	public void deleteOrigin() {
		try {
			getOriginFacade().deleteOrigin(origin);
			closeDialog();
			displayInfoMessageToUser("Deleted with success");
			loadOrigins();
			resetOrigin();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem occurred while removing. Try again later");
			e.printStackTrace();
		}
	}

	public List<Origin> getAllOrigin() {
		if (origins == null) {
			loadOrigins();
		}

		return origins;
	}

	private void loadOrigins() {
		origins = getOriginFacade().listAll();
	}

	public void resetOrigin() {
		origin = new Origin();
	}
}
