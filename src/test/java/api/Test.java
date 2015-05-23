package api;

import java.util.List;

import org.hibernate.Session;

import rs.otvoreniparlament.api.config.Settings;
import rs.otvoreniparlament.api.database.HibernateUtil;
import rs.otvoreniparlament.api.domain.Drzava;
import rs.otvoreniparlament.api.domain.GrupePrijateljstva;
import rs.otvoreniparlament.api.domain.ImovinskaKarta;
import rs.otvoreniparlament.api.domain.Lista;
import rs.otvoreniparlament.api.domain.MedjunarodnaOrganizacija;
import rs.otvoreniparlament.api.domain.Mesto;
import rs.otvoreniparlament.api.domain.Poslanik;
import rs.otvoreniparlament.api.domain.PolitickaOrganizacija;
import rs.otvoreniparlament.api.domain.Profesija;
import rs.otvoreniparlament.api.domain.WebPrezentacija;

public class Test {

	public static void main(String[] args) {

		Settings.getInstance();

		Session session = HibernateUtil.getInstance().getSessionFactory()
				.openSession();
		session.beginTransaction();

		// List<Profesija> list =
		// session.createCriteria(Profesija.class).list();
		// List<Mesto> list = session.createCriteria(Mesto.class).list();
		// List<Drzava> list = session.createCriteria(Drzava.class).list();
		// List<GrupePrijateljstva> list =
		// session.createCriteria(GrupePrijateljstva.class).list();
		// List<Poslanik> list = session.createCriteria(Poslanik.class).list();
		// List<Lista> list = session.createCriteria(Lista.class).list();
		// List<ImovinskaKarta> list =
		// session.createCriteria(ImovinskaKarta.class).list();
		// List<MedjunarodnaOrganizacija> list =
		// session.createCriteria(MedjunarodnaOrganizacija.class).list();
		// List<WebPrezentacija> list =
		// session.createCriteria(WebPrezentacija.class).list();
		List<PolitickaOrganizacija> list = session.createCriteria(PolitickaOrganizacija.class).list();

		System.out.println("Velicina liste: " + list.size());

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}

		System.out.println("Done!");

		session.close();
		HibernateUtil.getInstance().shutdown();
	}
}
