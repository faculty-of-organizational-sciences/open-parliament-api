package rs.otvoreniparlament.api;

import org.elasticsearch.bootstrap.Elasticsearch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Test {

	public static void main(String[] args) {

//		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//		session.beginTransaction();
//
//		Speech s = (Speech) session.createCriteria(Speech.class).setMaxResults(1).uniqueResult();
//		System.out.println(s.toString());

//		System.out.println("Velicina liste: " + list.size());

//		System.out.println(list.get(5).toString());
//		for (int i = 0; i < list.size(); i++) {
//
//		}

//		System.out.println("Done!");
//
//		session.close();
//		HibernateUtil.getInstance().shutdown();
		
		JsonObject partyJson = new JsonObject();
		partyJson.addProperty("meta","");
		partyJson.addProperty("id","");
		partyJson.addProperty("title","");
		partyJson.addProperty("members","");
		
		Gson gson = new GsonBuilder().create();
		System.out.println(gson.toJson(partyJson));
		
		
	}
}
