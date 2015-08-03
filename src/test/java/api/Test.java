package api;

import java.util.List;

import org.hibernate.Session;

import rs.otvoreniparlament.api.config.Settings;
import rs.otvoreniparlament.api.database.HibernateUtil;
import rs.otvoreniparlament.api.domain.*;

public class Test {

	public static void main(String[] args) {

		Settings.getInstance();

		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		List<Member> list = session.createCriteria(Party.class).list();

		System.out.println("Velicina liste: " + list.size());

		for (int i = 0; i < list.size(); i++) {

			//System.out.println(list.get(i).toString());
		}

		System.out.println("Done!");

		session.close();
		HibernateUtil.getInstance().shutdown();
	}
}
