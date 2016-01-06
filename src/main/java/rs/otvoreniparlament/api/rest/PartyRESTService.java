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

import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.rest.exceptions.AppException;
import rs.otvoreniparlament.api.rest.parsers.MemberJsonParser;
import rs.otvoreniparlament.api.rest.parsers.PartyJsonParser;
import rs.otvoreniparlament.api.service.PartyService;
import rs.otvoreniparlament.api.service.PartyServiceImp;
import rs.otvoreniparlament.api.util.ResourceBundleUtil;
import rs.otvoreniparlament.api.util.exceptions.KeyNotFoundInBundleException;

@Path("/parties")
public class PartyRESTService {

	private final Logger logger = LogManager.getLogger(PartyRESTService.class);

	protected PartyService partyService;

	public PartyRESTService() {
		partyService = new PartyServiceImp();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getParties(@QueryParam("limit") int limit,
							   @QueryParam("page") int page,
							   @QueryParam("sort") String sortType,
							   @QueryParam("query") String query) {

		List<Party> parties = partyService.getParties(page, limit, sortType.toUpperCase(), query);

		if (parties.isEmpty())
			try {
				throw new AppException(Status.NOT_FOUND, ResourceBundleUtil.getMessage("parties.not_found.noParties"));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}

		String json = PartyJsonParser.serializeParties(parties).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getParty(@PathParam("id") int id) {

		Party p = partyService.getParty(id);

		if (p == null)
			try {
				throw new AppException(Status.NOT_FOUND,
						ResourceBundleUtil.getMessage("parties.not_found.noPartyId", String.valueOf(id)));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}

		String json = PartyJsonParser.serializeParty(p).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/{id}/members")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getPartyMembers(@PathParam("id") int id,
									@QueryParam("limit") int limit,
									@QueryParam("page") int page) {
		
		List<Member> members = partyService.getPartyMembers(id, limit, page);

		if (members.isEmpty())
			try {
				throw new AppException(Status.NO_CONTENT,
						ResourceBundleUtil.getMessage("parties.no_content.noMembers", String.valueOf(id)));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}

		String json = MemberJsonParser.serializeMembers(members).toString();

		return Response.status(Status.OK).entity(json).build();
	}

}
