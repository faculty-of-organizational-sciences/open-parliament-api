package rs.otvoreniparlament.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import rs.otvoreniparlament.api.dao.SpeechDao;

public class SerializeSpeeches {

	protected static SpeechDao sd = new SpeechDao();

	public static void main(String[] args) throws Exception {
//		for (int i = 11; i < 161; i++) {
//
//			URL speechesURL = new URL("http://localhost:8080/api/api/speeches?limit=1000&page=" + i);
//			URLConnection connection = speechesURL.openConnection();
//			System.out.println("Opening a connection " + speechesURL);
//			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//			String inputLine;
//			String all = "";
//
//			while ((inputLine = in.readLine()) != null) {
//				all += inputLine;
//				System.out.println("Reading iteration: " + i);
//			}
//			in.close();
//
//			JsonObject jo = new JsonParser().parse(all).getAsJsonObject();
//			JsonArray json = jo.getAsJsonArray("dataArray");
//			PrintWriter out = new PrintWriter(
//					new BufferedWriter(new FileWriter("C:/Users/Baki/Desktop/govori/speeches.txt", true)));
//
//			for (int j = 0; j < json.size(); j++) {
//				JsonObject data = json.get(j).getAsJsonObject();
//				String text = data.get("text").toString();
//				out.println(text);
//			}
//			out.close();
//		}
		
		URL speechesURL = new URL("http://localhost:8080/api/api/members/217859/speeches");
		URLConnection connection = speechesURL.openConnection();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		String inputLine;
		String all = "";

		while ((inputLine = in.readLine()) != null) {
			all += inputLine;
		}
		in.close();

		JsonObject jo = new JsonParser().parse(all).getAsJsonObject();
		JsonArray json = jo.getAsJsonArray("dataArray");
		PrintWriter out = new PrintWriter(
				new BufferedWriter(new FileWriter("C:/Users/Baki/Desktop/govori/speeches_vucic.txt", true)));

		for (int j = 0; j < json.size(); j++) {
			JsonObject data = json.get(j).getAsJsonObject();
			String text = data.get("text").toString();
			out.println(text);
		}
		out.close();
	}
}
