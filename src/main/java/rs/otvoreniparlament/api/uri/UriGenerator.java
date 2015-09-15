package rs.otvoreniparlament.api.uri;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import rs.otvoreniparlament.api.config.Settings;
import rs.otvoreniparlament.api.domain.meta.RestServiceName;
import rs.otvoreniparlament.api.uri.exception.MissingRestServiceNameAnnotationException;

public class UriGenerator {

	private static String uriPrefix = Settings.getInstance().config.uriGenerator.uriPrefix;

	public static String generate(Object o, Integer id) {
		RestServiceName restServiceNameAnn = o.getClass().getAnnotation(RestServiceName.class);
		
		if (restServiceNameAnn != null) {
			String specificName = restServiceNameAnn.value();
			
			UriBuilder builder = UriBuilder.fromPath(uriPrefix).path(specificName + "/" + id);
			URI uri = builder.build();
			return uri.toString();
		} else {
			throw new MissingRestServiceNameAnnotationException("Passed object's class does not have declared RestServiceName annotation");
		}
	}
}
