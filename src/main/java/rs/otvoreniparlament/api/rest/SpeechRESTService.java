package rs.otvoreniparlament.api.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import rs.otvoreniparlament.api.config.Settings;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.rest.parsers.SpeechJsonParser;
import rs.otvoreniparlament.api.service.SpeechService;
import rs.otvoreniparlament.api.service.SpeechServiceImp;

@Path("/speeches")
public class SpeechRESTService {
	
	protected SpeechService speechService;

	public SpeechRESTService() {
		speechService = new SpeechServiceImp();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public String getMemberSpeeches(@QueryParam("limit") int limit,
									@QueryParam("page") int page) {
		
		if (limit == 0) {
			limit = Settings.getInstance().config.query.limit;
		}

		if (page == 0) {
			page = 1;
		}
		
		List<Speech> speeches = speechService.getSpeeches(limit, page);
		
		return SpeechJsonParser.serializeSpeeches(speeches).toString();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public String getSpeech(@PathParam("id") int id) {
		
		Speech s = speechService.getSpeech(id);
		
		return SpeechJsonParser.serializeSpeech(s).toString();
	}

}
