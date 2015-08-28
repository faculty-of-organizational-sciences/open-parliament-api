package rs.otvoreniparlament.api;

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

		Speech s = (Speech) session.createCriteria(Speech.class).setMaxResults(1).uniqueResult();
		System.out.println(s.toString());

//		System.out.println("Velicina liste: " + list.size());

//		System.out.println(list.get(5).toString());
//		for (int i = 0; i < list.size(); i++) {
//
//		}

		System.out.println("Done!");

		session.close();
		HibernateUtil.getInstance().shutdown();
	}
}
