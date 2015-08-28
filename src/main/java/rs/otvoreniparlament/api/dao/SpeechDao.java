package rs.otvoreniparlament.api.dao;

import java.util.List;

import org.hibernate.Session;

import rs.otvoreniparlament.api.database.HibernateUtil;
import rs.otvoreniparlament.api.domain.Speech;

public class SpeechDao {

	@SuppressWarnings("unchecked")
	public List<Speech> getMemberSpeeches(int id, int limit, int page) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String query = "SELECT s " + "FROM Speech s " + "WHERE s.memberId = :memberId";

		List<Speech> all = session.createQuery(query)
								  .setParameter("memberId", id)
								  .setFirstResult((page - 1) * limit)
								  .setMaxResults(limit)
								  .list();

		session.close();

		return all;
	}

	public Speech getSpeech(int id) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String query = "SELECT s " + "FROM Speech s " + "WHERE s.id = :speechId";

		Speech s = (Speech) session.createQuery(query).setParameter("speechId", id).uniqueResult();

		session.close();

		return s;
	}

}
