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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rs.otvoreniparlament.api.config.Settings;
import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.rest.exceptions.AppException;
import rs.otvoreniparlament.api.rest.parsers.json.PlenarySessionJsonParser;
import rs.otvoreniparlament.api.rest.parsers.json.SpeechJsonParser;
import rs.otvoreniparlament.api.rest.util.ParameterChecker;
import rs.otvoreniparlament.api.service.PlenarySessionService;
import rs.otvoreniparlament.api.service.SpeechService;
import rs.otvoreniparlament.api.service.impl.PlenarySessionServiceImp;
import rs.otvoreniparlament.api.service.impl.SpeechServiceImp;
import rs.otvoreniparlament.api.service.ServiceResponse;
import rs.otvoreniparlament.api.util.ResourceBundleUtil;
import rs.otvoreniparlament.api.util.exceptions.KeyNotFoundInBundleException;

@Path("/sessions")
public class PlenarySessionRESTService {

	private final Logger logger = LogManager.getLogger(PartyRESTService.class);

	protected PlenarySessionService plenarySessionService;
	protected SpeechService speechService;

	public PlenarySessionRESTService() {
		plenarySessionService = new PlenarySessionServiceImp();
		speechService = new SpeechServiceImp();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getPlenarySessions(@QueryParam("limit") int limit, @QueryParam("page") int page) {
		
		// validation
		int validLimit = ParameterChecker.check(limit, Settings.getInstance().config.query.limit);
		int validPage = ParameterChecker.check(page, 1);

		// retrieving the data
		ServiceResponse<PlenarySession> response = plenarySessionService.getPlenarySessions(validLimit, validPage);
		List<PlenarySession> plenarySessions = response.getRecords();
		long counter = response.getTotalHits();

		if (plenarySessions.isEmpty())
			try {
				throw new AppException(Status.NOT_FOUND,
						ResourceBundleUtil.getMessage("sessions.not_found.noSessions"));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}

		String json = PlenarySessionJsonParser.serializePlenarySessions(plenarySessions, counter).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getPlenarySession(@PathParam("id") int id) {

		PlenarySession ps = plenarySessionService.getPlenarySession(id);

		if (ps == null)
			try {
				throw new AppException(Status.NOT_FOUND,
						ResourceBundleUtil.getMessage("sessions.not_found.noSessionId", String.valueOf(id)));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}

		String json = PlenarySessionJsonParser.serializePlenarySession(ps).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/{id}/speeches")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getPlenarySessionSpeeches(@PathParam("id") int id, @QueryParam("limit") int limit,
			@QueryParam("page") int page) {

		// validation
		int validLimit = ParameterChecker.check(limit, Settings.getInstance().config.query.limit);
		int validPage = ParameterChecker.check(limit, 1);

		// retrieving the data
		ServiceResponse<Speech> speechresponse = speechService.getPlenarySessionSpeeches(id, validLimit, validPage);
		List<Speech> speeches = speechresponse.getRecords();
		long counter = speechresponse.getTotalHits();

		if (speeches.isEmpty())
			try {
				throw new AppException(Status.NO_CONTENT,
						ResourceBundleUtil.getMessage("sessions.no_content.noSpeeches", String.valueOf(id)));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}

		String json = SpeechJsonParser.serializeSpeeches(speeches, counter).toString();

		return Response.status(Status.OK).entity(json).build();
	}
}
