package rs.otvoreniparlament.api.dao;

import java.util.List;

import org.hibernate.Session;

import rs.otvoreniparlament.api.database.HibernateUtil;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;

public class PartyDao {

	@SuppressWarnings("unchecked")
	public List<Party> getParties(int page, int limit, String sort, String query) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String q = "SELECT p " + 
				   "FROM Party p " +
				   "WHERE p.name LIKE CONCAT('%', :name, '%'))" + 
				   "ORDER BY p.name " + sort;

		List<Party> all = session.createQuery(q)
				.setFirstResult((page - 1) * limit)
				.setMaxResults(limit)
				.setString("name", query)
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
	

	public List<Member> getPartyMembers(int id) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		
		String query = 
				"SELECT p.members " + 
				"FROM Party p " + 
				"WHERE p.id = :id";
		
		@SuppressWarnings("unchecked")
		List<Member> result = session.createQuery(query).
				setLong("id", id)
				.list();
		
		session.close();
		
		return result;
	}
}
