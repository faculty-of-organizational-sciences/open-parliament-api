package rs.otvoreniparlament.api;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import rs.otvoreniparlament.api.database.HibernateUtil;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.rest.util.formatters.DateFormatter;

public class Test {

	public static void main(String[] args) {

		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		
		Transaction tx = session.beginTransaction();
//
		Speech s = (Speech) session.createCriteria(Speech.class).setMaxResults(1).uniqueResult();
		System.out.println(DateFormatter.format(s.getSessionDate()));

//		System.out.println("Velicina liste: " + list.size());

//		System.out.println(list.get(5).toString());
//		for (int i = 0; i < list.size(); i++) {
//
//		}
		
//		String queryString = "SELECT m " +
//				 "FROM Member m where m.lastName LIKE CONCAT('0.', '%'))";
//		
//		Query query =  session.createQuery(queryString);
//		
//		List<Member> all = session.createQuery(queryString).list();
//		
//		System.out.println(all.size());
//		
//		for (Member m : all) {
//			String newName = m.getLastName().substring(3);
//			m.setLastName(newName);
//			session.update(m);
//			System.out.println("done");
//			
//		}
//		tx.commit();
//		System.out.println("commited");
		session.close();
		HibernateUtil.getInstance().shutdown();
		
	}
}
