package rs.otvoreniparlament.api.service;

public class ServiceSpecificResponse{

	private long totalHits;
	private Object response;
	public long getTotalHits() {
		return totalHits;
	}
	public void setTotalHits(long totalHits) {
		this.totalHits = totalHits;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	
}
