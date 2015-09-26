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
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.rest.exceptions.AppException;
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
	public Response getMembers(@QueryParam("limit") int limit, @QueryParam("page") int page,
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
		
		if(members.isEmpty()) throw new AppException(Status.NOT_FOUND, "There are no members to return.");
		
		String json = MemberJsonParser.serializeMembers(members).toString();

		return Response.status(Response.Status.OK).entity(json).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getMember(@PathParam("id") int id) {

		Member m = memberService.getMember(id);

		if (m == null)
			throw new AppException(Status.NOT_FOUND, "There is no member with the given ID.");

		String json = MemberJsonParser.serializeMember(m).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/{id}/speeches")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getMemberSpeeches(@PathParam("id") int id, @QueryParam("limit") int limit,
			@QueryParam("page") int page) {

		if (limit == 0) {
			limit = Settings.getInstance().config.query.limit;
		}

		if (page == 0) {
			page = 1;
		}

		List<Speech> speeches = speechService.getMemberSpeeches(id, limit, page);

		if (speeches.isEmpty())
			throw new AppException(Status.NO_CONTENT, "There are no speeches for specified member.");

		String json = SpeechJsonParser.serializeSpeeches(speeches).toString();

		return Response.status(Status.OK).entity(json).build();
	}

}
