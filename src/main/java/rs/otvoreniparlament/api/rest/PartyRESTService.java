package rs.otvoreniparlament.api.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import rs.otvoreniparlament.api.config.Settings;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
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
	public String getParties(
			@QueryParam("limit") int limit, 
			@QueryParam("page") int page,
			@QueryParam("sort") String sortType) {
		
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
		
		return PartyJsonParser.serializeParties(parties).toString();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public String getParty(@PathParam("id") int id) {
		
		Party p = partyService.getParty(id);
		
		return PartyJsonParser.serializeParty(p).toString();
	}
	
	@GET
	@Path("/{id}/members")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public String getPartyMembers(@PathParam("id") int id) {
		
		List<Member> members = partyService.getPartyMembers(id);
		
		return MemberJsonParser.serializeMembers(members).toString();
	}

}
