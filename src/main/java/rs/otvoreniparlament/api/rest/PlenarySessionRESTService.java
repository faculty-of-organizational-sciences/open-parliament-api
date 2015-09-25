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
import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.rest.exceptions.AppException;
import rs.otvoreniparlament.api.rest.parsers.PlenarySessionJsonParser;
import rs.otvoreniparlament.api.rest.parsers.SpeechJsonParser;
import rs.otvoreniparlament.api.service.PlenarySessionService;
import rs.otvoreniparlament.api.service.SpeechService;
import rs.otvoreniparlament.api.service.SpeechServiceImp;
import rs.otvoreniparlament.api.service.plenarySessionServiceImp;

@Path("/sessions")
public class PlenarySessionRESTService {

	protected PlenarySessionService plenarySessionService;
	protected SpeechService speechService;

	public PlenarySessionRESTService() {
		plenarySessionService = new plenarySessionServiceImp();
		speechService = new SpeechServiceImp();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getPlenarySessions(@QueryParam("limit") int limit, @QueryParam("page") int page)
			throws AppException {

		if (limit == 0) {
			limit = Settings.getInstance().config.query.limit;
		}

		if (page == 0) {
			page = 1;
		}

		List<PlenarySession> plenarySessions = plenarySessionService.getPlenarySessions(limit, page);

		if (plenarySessions.isEmpty())
			throw new AppException(Status.NOT_FOUND, "There are no sessions to return");

		String json = PlenarySessionJsonParser.serializePlenarySessions(plenarySessions).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getPlenarySession(@PathParam("id") int id) throws AppException {

		PlenarySession ps = plenarySessionService.getPlenarySession(id);

		if (ps == null)
			throw new AppException(Status.NOT_FOUND, "There is no session with the given ID.");

		String json = PlenarySessionJsonParser.serializePlenarySession(ps).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/{id}/speeches")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getPlenarySessionSpeeches(@PathParam("id") int id, @QueryParam("limit") int limit,
			@QueryParam("page") int page) throws AppException {

		if (limit == 0) {
			limit = Settings.getInstance().config.query.limit;
		}

		if (page == 0) {
			page = 1;
		}

		List<Speech> speeches = speechService.getPlenarySessionSpeeches(id, limit, page);

		if (speeches.isEmpty())
			throw new AppException(Status.NO_CONTENT, "There are no speeches for specified session.");

		String json = SpeechJsonParser.serializeSpeeches(speeches).toString();

		return Response.status(Status.OK).entity(json).build();
	}
}
