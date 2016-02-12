package rs.otvoreniparlament.api.service;

import java.util.List;

public class ServiceResponse<T> {

	private long totalHits;
	private List<T> records;

	public long getTotalHits() {
		return totalHits;
	}

	public void setTotalHits(long totalHits) {
		this.totalHits = totalHits;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

}
