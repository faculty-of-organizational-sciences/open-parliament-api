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
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.rest.exceptions.AppException;
import rs.otvoreniparlament.api.rest.parsers.MemberJsonParser;
import rs.otvoreniparlament.api.rest.parsers.PartyJsonParser;
import rs.otvoreniparlament.api.service.PartyService;
import rs.otvoreniparlament.api.service.PartyServiceImp;

@Path("/parties")
public class PartyRESTService {
	
protected PartyService partyService;
	
	public PartyRESTService() {
		partyService = new PartyServiceImp();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getParties(
			@QueryParam("limit") int limit, 
			@QueryParam("page") int page,
			@QueryParam("sort") String sortType) throws AppException {
		
		if (limit == 0) {
			limit = Settings.getInstance().config.query.limit;
		}
		
		if (page == 0) {
			page = 1;
		}
		
		if(sortType == null || (!sortType.equalsIgnoreCase("DESC") && sortType != null)) {
			sortType = "ASC";
		}
				
		List<Party> parties = partyService.getParties(page, limit, sortType.toUpperCase());
		
		if (parties.isEmpty()) throw new AppException(Status.NOT_FOUND, "There are no parties to return.");
		
		String json =  PartyJsonParser.serializeParties(parties).toString();
		
		return Response.status(Status.OK).entity(json).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getParty(@PathParam("id") int id) throws AppException {
		
		Party p = partyService.getParty(id);
		
		if (p == null) throw new AppException(Status.NOT_FOUND, "There is no party with the given ID.");
		
		String json = PartyJsonParser.serializeParty(p).toString();
		
		return Response.status(Status.OK).entity(json).build();
	}
	
	@GET
	@Path("/{id}/members")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getPartyMembers(@PathParam("id") int id) throws AppException {
		
		List<Member> members = partyService.getPartyMembers(id);
		
		if (members.isEmpty()) throw new AppException(Status.NO_CONTENT, "The selected party has no members.");
		
		String json = MemberJsonParser.serializeMembers(members).toString();
		
		return Response.status(Status.OK).entity(json).build();
	}

}
