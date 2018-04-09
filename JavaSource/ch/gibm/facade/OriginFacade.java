package ch.gibm.facade;

import java.util.List;

import ch.gibm.dao.EntityManagerHelper;
import ch.gibm.dao.OriginDAO;
import ch.gibm.entity.Origin;

public class OriginFacade {
	private static final long serialVersionUID = 1L;
	
	private OriginDAO originDAO = new OriginDAO();

	public void createOrigin(Origin origin) {
		EntityManagerHelper.beginTransaction();
		originDAO.save(origin);
		EntityManagerHelper.commitAndCloseTransaction();
	}

	public void updateLanguage(Origin origin) {
		EntityManagerHelper.beginTransaction();
		Origin persistedOrigin = originDAO.find(origin.getId());
		persistedOrigin.setName(origin.getName());
		originDAO.update(persistedOrigin);
		EntityManagerHelper.commitAndCloseTransaction();
	}
	
	public void deleteOrigin(Origin origin) {
		EntityManagerHelper.beginTransaction();
		Origin persistedOrigin = originDAO.findReferenceOnly(origin.getId());
		originDAO.delete(persistedOrigin);
		EntityManagerHelper.commitAndCloseTransaction();
	}

	public Origin findOrigin(int originId) {
		EntityManagerHelper.beginTransaction();
		Origin origin= originDAO.find(originId);
		EntityManagerHelper.commitAndCloseTransaction();
		return origin;
	}

	public List<Origin> listAll() {
		EntityManagerHelper.beginTransaction();
		List<Origin> result = originDAO.findAll();
		EntityManagerHelper.commitAndCloseTransaction();
		return result;
	}
	
}
