package rs.otvoreniparlament.api.rest.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AppExceptionMapper implements ExceptionMapper<AppException> {

	public Response toResponse(AppException ex) {

		ResponseBuilder response = Response.status(ex.getStatus());

		if (ex.getStatus().getStatusCode() != 204) {
			response.entity(new ErrorMessage(ex));
		}
		return response.type(MediaType.APPLICATION_JSON).build();
	}

}
