package rs.otvoreniparlament.api.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import rs.otvoreniparlament.api.database.HibernateUtil;
import rs.otvoreniparlament.api.domain.PlenarySession;

public class PlenarySessionDao {

	@SuppressWarnings("unchecked")
	public List<PlenarySession> getPlenarySessions(int limit, int page) {

		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String query = "SELECT ps " + "FROM PlenarySession ps ORDER BY ps.id";

		List<PlenarySession> all = session.createQuery(query).setFirstResult((page - 1) * limit)
				.setMaxResults(limit).list();

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
	
	public Long getTotalCount(){
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		
		String queryString = "SELECT count (ps.id) " +
							 "FROM PlenarySession ps";
		
		Query query = session.createQuery(queryString);
		
		Long countResults = (Long) query.uniqueResult();
		
		session.close();
		
		return countResults;
	}

}
