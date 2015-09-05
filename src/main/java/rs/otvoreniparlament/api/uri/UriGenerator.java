package rs.otvoreniparlament.api.uri;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import rs.otvoreniparlament.api.config.Settings;

public class UriGenerator {

	private static String uriPrefix = Settings.getInstance().config.uriGenerator.uriPrefix;

	public static String generate(Object o, Integer id) {
		String specificName = o.getClass().getSimpleName().toLowerCase();
		
		switch(specificName){
		case "member":
			specificName = "members";
			break;
		case "party":
			specificName = "parties";
			break;
		case "speech":
			specificName = "members/speeches";
			break;
		case "plenarysession":
			specificName = "plenarysessions";
			break;
		}
		
		UriBuilder builder = UriBuilder.fromPath(uriPrefix).path(specificName + "/" + id);
		URI uri = builder.build();
		return uri.toString();
	}
}
