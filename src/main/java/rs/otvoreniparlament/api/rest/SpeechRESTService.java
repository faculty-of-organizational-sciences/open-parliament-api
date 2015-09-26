package rs.otvoreniparlament.api.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import rs.otvoreniparlament.api.config.Settings;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.rest.exceptions.AppException;
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
	public Response getMemberSpeeches(@QueryParam("limit") int limit, @QueryParam("page") int page) {

		if (limit == 0) {
			limit = Settings.getInstance().config.query.limit;
		}

		if (page == 0) {
			page = 1;
		}

		List<Speech> speeches = speechService.getSpeeches(limit, page);

		if (speeches.isEmpty())
			throw new AppException(Status.NOT_FOUND, "There are no speeches to return.");

		String json = SpeechJsonParser.serializeSpeeches(speeches).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getSpeech(@PathParam("id") int id) {

		Speech s = speechService.getSpeech(id);

		if (s == null)
			throw new AppException(Status.NOT_FOUND, "There is no speech with the given ID.");

		String json = SpeechJsonParser.serializeSpeech(s).toString();

		return Response.status(Status.OK).entity(json).build();
	}

}
