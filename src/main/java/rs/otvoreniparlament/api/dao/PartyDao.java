package rs.otvoreniparlament.api.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import rs.otvoreniparlament.api.database.HibernateUtil;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;

public class PartyDao {

	@SuppressWarnings("unchecked")
	public List<Party> getParties(int page, int limit, String sort, String q) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String queryString = "SELECT p FROM Party p ";
		
		if (!q.isEmpty()) {
			queryString += "WHERE p.name LIKE CONCAT('%', :name, '%'))" ;
		}
		queryString += "ORDER BY p.name " + sort;

		Query query = session.createQuery(queryString);
		
		if (!q.isEmpty()) {
			query.setString("name", q);
		}
		
		List<Party> all = query
				.setFirstResult((page - 1) * limit)
				.setMaxResults(limit)
				.list();

		session.close();

		return all;
	}

	public Party getParty(int id) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		
		String query = "SELECT p " + "FROM Party p " + "WHERE p.id=" + id;
		
		Party p = (Party)session.createQuery(query).uniqueResult();
		
		session.close();
		
		return p;
	}
	

	public List<Member> getPartyMembers(int id, int limit, int page) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		
		String query = 
				"SELECT p.members " + 
				"FROM Party p " + 
				"WHERE p.id = :id";
		
		@SuppressWarnings("unchecked")
		List<Member> result = session.createQuery(query)
				.setFirstResult((page - 1) * limit)
				.setMaxResults(limit)
				.setLong("id", id)
				.list();
		
		session.close();
		
		return result;
	}
}
