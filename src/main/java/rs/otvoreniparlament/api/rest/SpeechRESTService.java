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
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.rest.exceptions.AppException;
import rs.otvoreniparlament.api.rest.parsers.json.SpeechJsonParser;
import rs.otvoreniparlament.api.rest.util.ParameterChecker;
import rs.otvoreniparlament.api.service.ServiceResponse;
import rs.otvoreniparlament.api.service.SpeechService;
import rs.otvoreniparlament.api.service.impl.SpeechServiceImp;
import rs.otvoreniparlament.api.util.ResourceBundleUtil;
import rs.otvoreniparlament.api.util.exceptions.KeyNotFoundInBundleException;

@Path("/speeches")
public class SpeechRESTService {

	private final Logger logger = LogManager.getLogger(SpeechRESTService.class);

	protected SpeechService speechService;

	public SpeechRESTService() {
		speechService = new SpeechServiceImp();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getSpeeches(@QueryParam("limit") int limit, @QueryParam("page") int page) {

		// validation
		int validLimit = ParameterChecker.check(limit, Settings.getInstance().config.query.limit);
		int validPage = ParameterChecker.check(page, 1);

		// retrieving the data
		ServiceResponse<Speech> response = speechService.getSpeeches(validLimit, validPage);
		List<Speech> speeches = response.getRecords();
		long counter = response.getTotalHits();

		if (speeches.isEmpty())
			try {
				throw new AppException(Status.NOT_FOUND,
						ResourceBundleUtil.getMessage("speeches.not_found.noSpeeches"));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}

		String json = SpeechJsonParser.serializeSpeeches(speeches, counter).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getSpeech(@PathParam("id") int id) {

		Speech s = speechService.getSpeech(id);

		if (s == null)
			try {
				throw new AppException(Status.NOT_FOUND,
						ResourceBundleUtil.getMessage("speeches.not_found.noSpeechId", String.valueOf(id)));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}

		String json = SpeechJsonParser.serializeSpeech(s).toString();

		return Response.status(Status.OK).entity(json).build();
	}

}
