package rs.otvoreniparlament.api.index;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;

public class ElasticSearchIndexingService {

	public String q;
	public String n;

	// Search
	public  SearchResponse searchQuery(String name, String query) {
		q = query;
		n = name;
		searchResponse = ElasticClient.getInstance().getClient().prepareSearch("dataSearch")
				.setTypes("members", "parties", "speeches").setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.termQuery(n, q)) // Query
				.setFrom(0).setSize(60).setExplain(true).execute().actionGet();
		return searchResponse;
	}

	public SearchResponse searchResponse;

	public SearchResponse getSearchResponse() {
		return searchResponse;
	}

	public void setSearchResponse(SearchResponse searchResponse) {
		this.searchResponse = searchResponse;
	}

	public static void main(String[] args) {
		ElasticSearchIndexingService elastic = new ElasticSearchIndexingService();
		elastic.getSearchResponse();

	}

}