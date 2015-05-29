package rs.otvoreniparlament.api.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/members")
public class PoslaniciService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getMembers() {
		return "{}";
	}
}
