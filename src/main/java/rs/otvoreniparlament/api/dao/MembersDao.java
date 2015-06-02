package rs.otvoreniparlament.api.dao;

import java.util.List;

import org.hibernate.Session;

import rs.otvoreniparlament.api.database.HibernateUtil;
import rs.otvoreniparlament.api.domain.Poslanik;

public class MembersDao {

	@SuppressWarnings("unchecked")
	public List<Poslanik> getMembers(int page, int limit) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String query = 
			"SELECT p " +
			"FROM Poslanik p " + 
			"ORDER BY p.prezimePoslanika, p.imePoslanika ASC";
		
		List<Poslanik> all = session.createQuery(query)
				.setFirstResult((page - 1) * limit)
				.setMaxResults(limit)
				.list();

		session.close();

		return all;
	}

}
