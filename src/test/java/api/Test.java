package api;

import java.util.List;
import org.hibernate.Session;
import rs.otvoreniparlament.api.domain.HibernateUtil;
import rs.otvoreniparlament.api.domain.Profesija;

public class Test {

	public static void main(String[] args) {

		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		List<Profesija> list = session.createCriteria(Profesija.class).list();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		
		System.out.println("Done!");
		
		session.close();
		HibernateUtil.getInstance().shutdown();
	}
}
