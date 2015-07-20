package rs.otvoreniparlament.api.dao;

import java.util.List;

import org.hibernate.Session;

import rs.otvoreniparlament.api.database.HibernateUtil;
import rs.otvoreniparlament.api.domain.Party;

public class PartyDao {

	@SuppressWarnings("unchecked")
	public List<Party> getParties(int page, int limit, String sort) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String query = "SELECT p " + "FROM Party p " + "ORDER BY p.name " + sort;

		List<Party> all = session.createQuery(query)
				.setFirstResult((page - 1) * limit)
				.setMaxResults(limit)
				.list();

		session.close();

		return all;
	}

}
