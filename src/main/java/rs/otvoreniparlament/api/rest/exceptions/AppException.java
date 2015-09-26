package rs.otvoreniparlament.api.rest.exceptions;

import javax.ws.rs.core.Response.Status;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = -8226744858652884537L;

	private Status status;
	private String error;

	public AppException(Status status, String error) {
		super(error);
		this.status = status;
		this.error = error;
	}

	public AppException() {
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
