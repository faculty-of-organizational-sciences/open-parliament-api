package rs.otvoreniparlament.api.rest.exceptions;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

	@XmlElement(name = "status")
	private int status;

	@XmlElement(name = "error")
	private String error;

	public ErrorMessage(AppException ex) {
		this.status = ex.getStatus().getStatusCode();
		this.error = ex.getError();
	}

	public ErrorMessage() {
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
