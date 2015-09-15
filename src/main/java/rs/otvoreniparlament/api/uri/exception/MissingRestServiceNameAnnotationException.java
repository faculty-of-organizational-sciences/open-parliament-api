package rs.otvoreniparlament.api.uri.exception;

public class MissingRestServiceNameAnnotationException extends RuntimeException {

	private static final long serialVersionUID = 8825953285295733027L;

	public MissingRestServiceNameAnnotationException(String msg) {
		super(msg);
	}
}
