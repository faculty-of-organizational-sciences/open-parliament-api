package rs.otvoreniparlament.api.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import rs.otvoreniparlament.api.config.Settings;
import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.rest.parsers.PlenarySessionJsonParser;
import rs.otvoreniparlament.api.service.PlenarySessionService;
import rs.otvoreniparlament.api.service.plenarySessionServiceImp;

@Path("/sessions")
public class PlenarySessionRESTService {

	protected PlenarySessionService plenarySessionService;

	public PlenarySessionRESTService() {
		plenarySessionService = new plenarySessionServiceImp();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public String getPlenarySessions(@QueryParam("limit") int limit, @QueryParam("page") int page) {

		if (limit == 0) {
			limit = Settings.getInstance().config.query.limit;
		}

		if (page == 0) {
			page = 1;
		}

		List<PlenarySession> plenarySessions = plenarySessionService.getPlenarySessions(limit, page);

		return PlenarySessionJsonParser.serializePlenarySessions(plenarySessions).toString();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public String getPlenarySession(@PathParam("id") int id) {

		PlenarySession ps = plenarySessionService.getPlenarySession(id);

		return PlenarySessionJsonParser.serializePlenarySession(ps).toString();
	}
}
