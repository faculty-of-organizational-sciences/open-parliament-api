package rs.otvoreniparlament.api.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import rs.otvoreniparlament.api.config.Settings;
import rs.otvoreniparlament.api.database.HibernateUtil;
import rs.otvoreniparlament.api.domain.Speech;

public class SpeechDao {

	private final Logger logger = LogManager.getLogger(SpeechDao.class);
	private SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");

	@SuppressWarnings("unchecked")
	public List<Speech> getMemberSpeeches(int id, int limit, int page, String qtext, String from, String to) {

		int validLimit;
		int validPage;
		String validFromDate;
		String validToDate;
		String validQueryText;

		if (from == null) {
			validFromDate = "";
		} else {
			validFromDate = from;
		}

		if (to == null) {
			validToDate = "";
		} else {
			validToDate = to;
		}

		if (qtext == null) {
			validQueryText = "";
		} else {
			validQueryText = qtext;
		}

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

		java.util.Date fromDate = null;
		java.util.Date toDate = null;

		try {
			if (!validFromDate.isEmpty())
				fromDate = sdf1.parse(validFromDate);
			if (!validToDate.isEmpty())
				toDate = sdf1.parse(validToDate);
		} catch (ParseException e) {
			logger.warn(e);
		}

		String queryString = "SELECT s " + "FROM Speech s " + "WHERE s.member.id = :memberId";

		if (!validQueryText.isEmpty()) {
			queryString += " AND s.text LIKE CONCAT('%', :text, '%')";
		}

		if (fromDate != null && toDate == null) {
			queryString += " AND s.sessionDate >= :fromDate";
		} else if (fromDate == null && toDate != null) {
			queryString += " AND s.sessionDate <= :toDate";
		} else if (fromDate != null && toDate != null) {
			queryString += " AND s.sessionDate BETWEEN :fromDate and :toDate";
		}

		Query query = session.createQuery(queryString);

		if (!validQueryText.isEmpty()) {
			query.setString("text", validQueryText);
		}

		if (fromDate != null && toDate == null) {
			query.setDate("fromDate", fromDate);
		} else if (fromDate == null && toDate != null) {
			query.setDate("toDate", toDate);
		} else if (fromDate != null && toDate != null) {
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}

		System.out.println(queryString);

		List<Speech> all = query.setParameter("memberId", id).setFirstResult((validPage - 1) * validLimit)
				.setMaxResults(validLimit).list();
		logger.info(query.toString());

		session.close();

		return all;
	}

	public Speech getSpeech(int id) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String queryString = "SELECT s " + "FROM Speech s " + "WHERE s.id = :speechId";

		Query query = session.createQuery(queryString);

		Speech s = (Speech) query.setParameter("speechId", id).uniqueResult();

		session.close();

		return s;
	}

	@SuppressWarnings("unchecked")
	public List<Speech> getSpeeches(int limit, int page) {
		
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

		String query = "SELECT s " + "FROM Speech s " + "ORDER BY s.id";

		List<Speech> all = session.createQuery(query).setFirstResult((validPage - 1) * validLimit).setMaxResults(validLimit).list();

		session.close();

		return all;
	}

	@SuppressWarnings("unchecked")
	public List<Speech> getPlenarySessionSpeeches(int id, int limit, int page) {

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

		String query = "SELECT s " + "FROM Speech s " + "WHERE s.plenarySession.id = :plenarySessionId "
				+ "ORDER BY s.id";

		List<Speech> all = session.createQuery(query).setParameter("plenarySessionId", id)
				.setFirstResult((validPage - 1) * validLimit).setMaxResults(validLimit).list();

		session.close();

		return all;
	}

}
