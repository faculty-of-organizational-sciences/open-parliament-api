package rs.otvoreniparlament.api.index;

import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import rs.otvoreniparlament.indexing.IndexType;

public class ElasticSearchService {

	public String q;
	public String n;

	// Search
	public  SearchResponse searchQuery(String name, String query) {
		q = query;
		n = name;
		
		QueryBuilder qb = QueryBuilders.queryStringQuery(query);
		searchResponse = ElasticClient.getInstance().getClient().prepareSearch("datasearch")
				.setTypes("member", "party", "speech", "plenarysessions").setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb) // Query
				.setFrom(0).setSize(60).setExplain(true).execute().actionGet();
		System.out.println("test... >>>>" + searchResponse );
		
		SearchHit[] hits = searchResponse.getHits().getHits();
        System.out.println("Current results: " + hits.length);
        for (SearchHit hit : hits) {
            System.out.println("------------------------------");
            Map<String,Object> result = hit.getSource();   
            System.out.println(result);
        }
		GetResponse response = ElasticClient.getInstance().getClient().prepareGet("datasearch", "speech",IndexType.SPEACH_TYPE).get();
		
		System.out.println("response ###" +response.getSourceAsString());
	
		return searchResponse;
		
	}
	public SearchResponse test (String query){
		QueryBuilder qb =QueryBuilders.queryStringQuery(query);

		SearchResponse scrollResp = ElasticClient.getInstance().getClient().prepareSearch("datasearch")
		        .setSearchType(SearchType.SCAN)
		        .setScroll(new TimeValue(60000))
		        .setQuery(qb)
		        .setSize(100).execute().actionGet(); //100 hits per shard will be returned for each scroll
		//Scroll until no hits are returned
		while (true) {

		    for (SearchHit hit : scrollResp.getHits().getHits()) {
		    	 Map<String,Object> result = hit.getSource();   
		            System.out.println(result);
		    }
		    scrollResp = ElasticClient.getInstance().getClient().prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(600000)).execute().actionGet();
		    //Break condition: No hits are returned
		    if (scrollResp.getHits().getHits().length == 0) {
		        break; }}
		    return scrollResp;
	}

	public SearchResponse searchResponse;

	public SearchResponse getSearchResponse() {
		return searchResponse;
	}

	public void setSearchResponse(SearchResponse searchResponse) {
		this.searchResponse = searchResponse;
	}

	public static void main(String[] args) {
		ElasticSearchService elastic = new ElasticSearchService();
		elastic.test("aleksandar");

	}

}