package rs.otvoreniparlament.api.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import rs.otvoreniparlament.api.service.MembersServiceImp;

@Path("/members")
public class PoslaniciService {

	private Gson gson;
	protected MembersServiceImp msi;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getMembers() {
		gson = new Gson();
		msi = new MembersServiceImp();
		return gson.toJson(msi.getMembers());
	}
}
