package rs.otvoreniparlament.api.index;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;


public class ElasticSearchService {

	
	private static final Logger logger = LogManager.getLogger(ElasticSearchService.class);

	// Search
	public  SearchResponse searchQuery(String index, String name, String query) {
				
		QueryBuilder qb = QueryBuilders.queryStringQuery(query + "*");
		
		searchResponse = ElasticClient.getInstance().getClient().prepareSearch(index)
				.setTypes(name).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb) // Query
				.setFrom(0).setSize(60).setExplain(true).execute().actionGet();
	
		return searchResponse;
	}
	
//	public SearchResponse test (String query){
//		QueryBuilder qb =QueryBuilders.queryStringQuery(query);
//
//		SearchResponse scrollResp = ElasticClient.getInstance().getClient().prepareSearch("datasearch")
//		        .setSearchType(SearchType.SCAN)
//		        .setScroll(new TimeValue(60000))
//		        .setQuery(qb)
//		        .setSize(100).execute().actionGet(); //100 hits per shard will be returned for each scroll
//		//Scroll until no hits are returned
//		while (true) {
//
//		    for (SearchHit hit : scrollResp.getHits().getHits()) {
//		    	 Map<String,Object> result = hit.getSource();   
//		            System.out.println(result);
//
//		    }
//		    scrollResp = ElasticClient.getInstance().getClient().prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(600000)).execute().actionGet();
//		    //Break condition: No hits are returned
//		    if (scrollResp.getHits().getHits().length == 0) {
//		        break; }}
//		    return scrollResp;
//	}

	public SearchResponse searchResponse;

	public SearchResponse getSearchResponse() {
		return searchResponse;
	}

	public void setSearchResponse(SearchResponse searchResponse) {
		this.searchResponse = searchResponse;
	}

}