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
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.rest.parsers.MemberJsonParser;
import rs.otvoreniparlament.api.rest.parsers.SpeechJsonParser;
import rs.otvoreniparlament.api.service.MembersService;
import rs.otvoreniparlament.api.service.MembersServiceImp;
import rs.otvoreniparlament.api.service.SpeechService;
import rs.otvoreniparlament.api.service.SpeechServiceImp;

@Path("/members")
public class MemberRESTService {

	protected MembersService memberService;
	protected SpeechService speechService;

	public MemberRESTService() {
		memberService = new MembersServiceImp();
		speechService = new SpeechServiceImp();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public String getMembers(@QueryParam("limit") int limit, @QueryParam("page") int page,
			@QueryParam("sort") String sortType) {

		if (limit == 0) {
			limit = Settings.getInstance().config.query.limit;
		}

		if (page == 0) {
			page = 1;
		}

		if (sortType == null || (!sortType.equalsIgnoreCase("DESC") && sortType != null)) {
			sortType = "ASC";
		}

		List<Member> members = memberService.getMembers(page, limit, sortType.toUpperCase());

		return MemberJsonParser.serializeMembers(members).toString();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public String getMember(@PathParam("id") int id) {

		Member m = memberService.getMember(id);

		return MemberJsonParser.serializeMember(m).toString();
	}

	@GET
	@Path("/{id}/speeches")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public String getMemberSpeeches(@PathParam("id") int id,
									@QueryParam("limit") int limit,
									@QueryParam("page") int page) {
		
		if (limit == 0) {
			limit = Settings.getInstance().config.query.limit;
		}

		if (page == 0) {
			page = 1;
		}
		
		List<Speech> speeches = speechService.getMemberSpeeches(id, limit, page);
		
		return SpeechJsonParser.serializeSpeeches(speeches).toString();
	}
	
	@GET
	@Path("/speeches/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public String getSpeech(@PathParam("id") int id) {
		
		Speech s = speechService.getSpeech(id);
		
		return SpeechJsonParser.serializeSpeech(s).toString();
	}
}