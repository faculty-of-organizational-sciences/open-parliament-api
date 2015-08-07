package rs.otvoreniparlament.api.uri;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import rs.otvoreniparlament.api.config.Settings;

public class UriGenerator {

	String uriPrefix = Settings.getInstance().config.uriGenerator.uriPrefix;

	String specificName;

	public UriGenerator(Object o, Integer id) {

		specificName = o.getClass().getName().toLowerCase();
		UriBuilder builder = UriBuilder.fromPath(uriPrefix).path(specificName + "/" + id);
		URI uri = builder.build();
		System.out.println(uri);

	}
}
