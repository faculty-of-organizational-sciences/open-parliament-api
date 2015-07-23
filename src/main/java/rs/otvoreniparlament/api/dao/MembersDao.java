package rs.otvoreniparlament.api.dao;

import java.util.List;

import org.hibernate.Session;

import rs.otvoreniparlament.api.database.HibernateUtil;
import rs.otvoreniparlament.api.domain.Member;

public class MembersDao {

	@SuppressWarnings("unchecked")
	public List<Member> getMembers(int page, int limit, String sort) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

	
		String query = 
			"SELECT m " +
			"FROM Member m " + 
			"ORDER BY m.lastName " + sort + ", m.name";
		
		List<Member> all = session.createQuery(query)
				.setFirstResult((page - 1) * limit)
				.setMaxResults(limit)
				.list();

		session.close();

		return all;
	}

}
