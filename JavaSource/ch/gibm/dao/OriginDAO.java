package ch.gibm.dao;

import ch.gibm.entity.Language;
import ch.gibm.entity.Origin;

public class OriginDAO extends GenericDAO<Origin> {

	private static final long serialVersionUID = 1L;

	public OriginDAO() {
		super(Origin.class);
	}
	
	public void delete(Origin origin) {
		super.delete(origin.getId(), Origin.class);
	}
}
