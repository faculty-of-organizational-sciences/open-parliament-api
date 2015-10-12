package rs.otvoreniparlament.api.dao;

import java.util.List;

import org.hibernate.Session;

import rs.otvoreniparlament.api.database.HibernateUtil;
import rs.otvoreniparlament.api.domain.Member;

public class MembersDao {

	@SuppressWarnings("unchecked")
	public List<Member> getMembers(int page, int limit, String sort, String query) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String q = 
			"SELECT m " +
			"FROM Member m " + 
			"WHERE CONCAT(m.name, ' ', m.lastName) LIKE CONCAT('%', :name, '%'))" + 
			"ORDER BY m.lastName " + sort + ", m.name";
		
		List<Member> all = session.createQuery(q)
				.setFirstResult((page - 1) * limit)
				.setMaxResults(limit)
				.setString("name", query)
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
			"where m.id="+id;
		
		Member m = (Member)session.createQuery(query).uniqueResult();
		
		session.close();
		
		return m;		
	}

}
