package rs.otvoreniparlament.api.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import rs.otvoreniparlament.api.config.Settings;
import rs.otvoreniparlament.api.domain.Poslanik;
import rs.otvoreniparlament.api.rest.parsers.MembersJsonParser;
import rs.otvoreniparlament.api.service.MembersService;
import rs.otvoreniparlament.api.service.MembersServiceImp;

@Path("/members")
public class MemberRESTService {

	protected MembersService memberService;
	
	public MemberRESTService() {
		memberService = new MembersServiceImp();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public String getMembers(
			@QueryParam("limit") int limit, 
			@QueryParam("page") int page) {
		
		if (limit == 0) {
			limit = Settings.getInstance().config.query.limit;
		}
		
		if (page == 0) {
			page = 1;
		}
		
		List<Poslanik> members = memberService.getMembers(page, limit);
		
		return MembersJsonParser.serializeMembers(members);
	}
}
