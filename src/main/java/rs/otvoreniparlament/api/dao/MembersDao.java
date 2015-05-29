package rs.otvoreniparlament.api.dao;

import java.util.List;
import org.hibernate.Session;

import rs.otvoreniparlament.api.database.HibernateUtil;
import rs.otvoreniparlament.api.domain.Poslanik;

public class MembersDao {

	@SuppressWarnings("unchecked")
	public List<Poslanik> getMembers() {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		
		return session.createQuery("from Poslanik").list();
	}

}
