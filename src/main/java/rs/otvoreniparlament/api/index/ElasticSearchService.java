package rs.otvoreniparlament.api.index;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;

public class ElasticSearchService {
	
	// Search
	public  SearchResponse searchQuery(String index, String name, String query, int limit, int page) {
		int paggination = (page-1)*limit;
		QueryBuilder qb;
		if(query == "")	{
		 qb = QueryBuilders.matchAllQuery();
		}else{
		 qb = QueryBuilders.queryStringQuery(query + "*");
		}
		searchResponse = ElasticClient.getInstance().getClient().prepareSearch(index)
				.setTypes(name).setSearchType( SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb) // Query
				.setFrom(paggination).setSize(limit).setExplain(true).execute().actionGet();
	
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
	public  SearchResponse searchSpecificListMember(String index, String name, Integer id, Integer limit,int page, String qtext, String from, String to) {
		int paggination = (page-1)*limit;
		QueryBuilder qb = QueryBuilders.queryStringQuery(qtext +"*");
		
		searchResponse = ElasticClient.getInstance().getClient().prepareSearch(index)
				.setTypes(name).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setPostFilter(QueryBuilders.rangeQuery("sessiondate").from(from).to(to))
				.addAggregation(AggregationBuilders.terms(id.toString()).field("speech-member-id"))
				.setFrom(paggination).setSize(limit).setExplain(true).execute().actionGet();
	
		return searchResponse;
	}
	//speeches of a plenary session with given id
		public  SearchResponse searchSpecificListSession(String index, String name, Integer id, Integer limit, int page) {
			
			QueryBuilder qb = QueryBuilders.queryStringQuery("sessionId");
			int paggination = (page-1)*limit;
			searchResponse = ElasticClient.getInstance().getClient().prepareSearch(index)
					.setTypes(name).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
					.setQuery(qb)
					.addAggregation(AggregationBuilders.terms(id.toString()).field("sessionId"))
					.setFrom(paggination).setSize(limit).setExplain(true).execute().actionGet();
		
			return searchResponse;
		}
		
//		List of members from specific party
public  SearchResponse searchSpecificPartyMember(String index, String name, Integer id, Integer limit, int page) {
			
			QueryBuilder qb = QueryBuilders.queryStringQuery("party-members");
			int paggination = (page-1)*limit;
			searchResponse = ElasticClient.getInstance().getClient().prepareSearch(index)
					.setTypes(name).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
					.setQuery(qb)
					.addAggregation(AggregationBuilders.terms(id.toString()).field("party-id"))
					.setFrom(paggination).setSize(limit).setExplain(true).execute().actionGet();
		
			return searchResponse;
		}
		
	


	public SearchResponse searchResponse;

	public SearchResponse getSearchResponse() {
		return searchResponse;
	}

	public void setSearchResponse(SearchResponse searchResponse) {
		this.searchResponse = searchResponse;
	}

}