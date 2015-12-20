package rs.otvoreniparlament.api.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import rs.otvoreniparlament.api.config.Settings;
import rs.otvoreniparlament.api.database.HibernateUtil;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.rest.MemberRESTService;

public class MembersDao {
	
	private final Logger logger = LogManager.getLogger(MembersDao.class);

	@SuppressWarnings("unchecked")
	public List<Member> getMembers(int page, int limit, String sort, String q) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		
		if (limit == 0) {
			limit = Settings.getInstance().config.query.limit;
		}

		if (page == 0) {
			page = 1;
		}

		if (sort == null || (!sort.equalsIgnoreCase("DESC") && sort != null)) {
			sort = "ASC";
		}
		
		if(q == null){
			q = "";
		}

		String queryString = "SELECT m FROM Member m ";
		
		if (q != null && !q.isEmpty()) {
			queryString += "WHERE CONCAT(m.name, ' ', m.lastName) LIKE CONCAT('%', :name, '%'))";
		}
			queryString += "ORDER BY m.lastName " + sort + ", m.name";
		
		logger.info(queryString);
			
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
			"where m.id="+id;
		
		Member m = (Member)session.createQuery(query).uniqueResult();
		
		session.close();
		
		return m;		
	}

}
