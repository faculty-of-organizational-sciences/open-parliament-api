package rs.otvoreniparlament.api.index;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;

public class ElasticSearchService {
	
	// Search
	public  SearchResponse searchQuery(String index, String name, String query, int limit) {
		QueryBuilder qb;
		if(query == "")	{
		 qb = QueryBuilders.matchAllQuery();
		}else{
		 qb = QueryBuilders.queryStringQuery(query + "*");
		}
		searchResponse = ElasticClient.getInstance().getClient().prepareSearch(index)
				.setTypes(name).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb) // Query
				.setFrom(0).setSize(limit).setExplain(true).execute().actionGet();
	
		return searchResponse;
	}
	public  SearchResponse searchSpecificID(String index, String name, String field, Integer id) {
		
		QueryBuilder qb = QueryBuilders.matchQuery(field, id.toString());
		
		searchResponse = ElasticClient.getInstance().getClient().prepareSearch(index)
				.setTypes(name).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)// Query
				.setFrom(0).setSize(60).setExplain(true).execute().actionGet();
	
		return searchResponse;
	}
	//speeches of a member with given id
	public  SearchResponse searchSpecificListMember(String index, String name, Integer id, Integer limit, String qtext, String from, String to) {
		
		QueryBuilder qb = QueryBuilders.queryStringQuery(qtext +"*");
		
		searchResponse = ElasticClient.getInstance().getClient().prepareSearch(index)
				.setTypes(name).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setPostFilter(QueryBuilders.rangeQuery("sessiondate").from(from).to(to))
				.addAggregation(AggregationBuilders.terms(id.toString()).field("speech-member-id"))
				.setFrom(0).setSize(limit).setExplain(true).execute().actionGet();
	
		return searchResponse;
	}
	//speeches of a plenary session with given id
		public  SearchResponse searchSpecificListSession(String index, String name, Integer id, Integer limit) {
			
			QueryBuilder qb = QueryBuilders.queryStringQuery("sessionId");
			
			searchResponse = ElasticClient.getInstance().getClient().prepareSearch(index)
					.setTypes(name).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
					.setQuery(qb)
					.addAggregation(AggregationBuilders.terms(id.toString()).field("sessionId"))
					.setFrom(0).setSize(limit).setExplain(true).execute().actionGet();
		
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