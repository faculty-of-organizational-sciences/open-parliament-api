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
import org.elasticsearch.action.search.SearchResponse;

import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.index.ElasticSearchService;
import rs.otvoreniparlament.api.rest.exceptions.AppException;
import rs.otvoreniparlament.api.rest.parsers.MemberJsonParser;
import rs.otvoreniparlament.api.rest.parsers.SpeechJsonParser;
import rs.otvoreniparlament.api.service.MembersService;
import rs.otvoreniparlament.api.service.MembersServiceImp;
import rs.otvoreniparlament.api.service.SpeechService;
import rs.otvoreniparlament.api.service.SpeechServiceImp;
import rs.otvoreniparlament.api.util.ResourceBundleUtil;
import rs.otvoreniparlament.api.util.exceptions.KeyNotFoundInBundleException;

@Path("/members")
public class MemberRESTService {

	private final Logger logger = LogManager.getLogger(MemberRESTService.class);

	protected MembersService memberService;
	protected SpeechService speechService;

	public MemberRESTService() {
		memberService = new MembersServiceImp();
		speechService = new SpeechServiceImp();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getMembers(@QueryParam("limit") int limit,
							   @QueryParam("page") int page,
							   @QueryParam("sort") String sortType,
							   @QueryParam("query") String query) {
		
		List<Member> members = memberService.getMembers(page, limit, sortType, query);

		if (members.isEmpty())
			try {
				throw new AppException(Status.NOT_FOUND, ResourceBundleUtil.getMessage("members.not_found.noMembers"));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}

		String json = MemberJsonParser.serializeMembers(members).toString();

		return Response.status(Response.Status.OK).entity(json).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getMember(@PathParam("id") int id) {

		Member m = memberService.getMember(id);
		

		if (m == null) {
			try {
				throw new AppException(Status.NOT_FOUND,
						ResourceBundleUtil.getMessage("members.not_found.noMemberId", String.valueOf(id)));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}
		}

		String json = MemberJsonParser.serializeMember(m).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/{id}/speeches")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getMemberSpeeches(@PathParam("id") int id,
								      @QueryParam("limit") int limit,
								      @QueryParam("page") int page,
								      @QueryParam("qtext") String qtext,
								      @QueryParam("fromDate") String from,
								      @QueryParam("toDate") String to) {
		
		List<Speech> speeches = speechService.getMemberSpeeches(id, limit, page, qtext, from, to);

		if (speeches.isEmpty())
			try {
				throw new AppException(Status.NO_CONTENT,
						ResourceBundleUtil.getMessage("members.no_content.noSpeeches", String.valueOf(id)));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}

		String json = SpeechJsonParser.serializeSpeeches(speeches).toString();

		return Response.status(Status.OK).entity(json).build();
	}

}
