package rs.otvoreniparlament.api.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import rs.otvoreniparlament.api.database.HibernateUtil;
import rs.otvoreniparlament.api.domain.Member;

public class MembersDao {
	
	@SuppressWarnings("unchecked")
	public List<Member> getMembers(int page, int limit, String sort, String q) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		
		String queryString = "SELECT m " +
							 "FROM Member m ";
		
		if (q != null && !q.isEmpty()) {
			queryString += "WHERE CONCAT(m.name, ' ', m.lastName) " +
						   "LIKE CONCAT('%', :name, '%'))";
		}
			queryString += "ORDER BY m.lastName " + sort + ", m.name";
		
		Query query = session.createQuery(queryString);
		
		if (!q.isEmpty()) {
			query.setString("name", q);
		}
		
		List<Member> all = query
				.setFirstResult((page - 1) * limit)
				.setMaxResults(limit)
				.list();

		session.close();

		return all;
	}
	
	public Member getMember(int id) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		

		String query = 
			"SELECT m " +
			"FROM Member m " + 
			"where m.id=" + id;
		
		Member m = (Member)session.createQuery(query).uniqueResult();
		
		session.close();
		
		return m;		
	}
	
	public Long getTotalCount(String q){
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		
		String queryString = "SELECT count (m.id) " +
							 "FROM Member m ";
		
		if (q != null && !q.isEmpty()) {
			queryString += "WHERE CONCAT(m.name, ' ', m.lastName) " +
						   "LIKE CONCAT('%', :name, '%'))";
		}
		
		Query query = session.createQuery(queryString);
		
		if (!q.isEmpty()) {
			query.setString("name", q);
		}
		
		Long countResults = (Long) query.uniqueResult();
		
		session.close();
		
		return countResults;
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
	
	public Long getPartyMembersTotalCount(int id){
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		
		String queryString = 
				"SELECT count(idposlanika) " + 
				"FROM clanpolitickeorganizacije " + 
				"WHERE clanpolitickeorganizacije.idpolitickeorganizacije = :id";
		
		SQLQuery query = (SQLQuery) session.createSQLQuery(queryString).setLong("id", id);
		
		Long countResults = ((Number) query.uniqueResult()).longValue();
		
		session.close();
		
		return countResults;
	}

}
