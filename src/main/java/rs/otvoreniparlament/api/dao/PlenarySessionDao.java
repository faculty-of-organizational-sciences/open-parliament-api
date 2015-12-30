package rs.otvoreniparlament.api.dao;

import java.util.List;

import org.hibernate.Session;

import rs.otvoreniparlament.api.config.Settings;
import rs.otvoreniparlament.api.database.HibernateUtil;
import rs.otvoreniparlament.api.domain.PlenarySession;

public class PlenarySessionDao {

	@SuppressWarnings("unchecked")
	public List<PlenarySession> getPlenarySessions(int limit, int page) {

		int validLimit;
		int validPage;

		if (limit == 0) {
			validLimit = Settings.getInstance().config.query.limit;
		} else {
			validLimit = limit;
		}

		if (page == 0) {
			validPage = 1;
		} else {
			validPage = page;
		}

		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String query = "SELECT ps " + "FROM PlenarySession ps ORDER BY ps.id";

		List<PlenarySession> all = session.createQuery(query).setFirstResult((validPage - 1) * validLimit)
				.setMaxResults(validLimit).list();

		session.close();

		return all;
	}

	public PlenarySession getPlenarySession(int id) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String query = "SELECT ps " + "FROM PlenarySession ps " + "where ps.id=" + id;

		PlenarySession ps = (PlenarySession) session.createQuery(query).uniqueResult();

		session.close();

		return ps;
	}

}
