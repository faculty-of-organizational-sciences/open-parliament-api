package rs.otvoreniparlament.api.index;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import rs.otvoreniparlament.api.formatters.DateFormatter;

public class ElasticSearchService {
	private static final Logger logger = LogManager.getLogger(ElasticSearchService.class);
// Search
	public static SearchResponse searchQuery(String index, String name, String query, int limit, int page) {
		int paggination = (page-1)*limit;
		QueryBuilder qb;
		if(query == "")	{
		 qb = QueryBuilders.matchAllQuery();
		}else{
		 qb = QueryBuilders.queryStringQuery(query + "*");
		}
		searchResponse = ElasticClient.getInstance().getClient().prepareSearch(index)
				.setTypes(name).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setFrom(paggination).setSize(limit).setExplain(true).execute().actionGet();
	
		return searchResponse;
		
	
	}
	
	public  static SearchResponse searchSpecificID(String index, String name, String field, String id) {
		QueryBuilder qb = QueryBuilders.matchQuery(field, id);
		
		searchResponse = ElasticClient.getInstance().getClient().prepareSearch(index)
				.setTypes(name).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setFrom(0).setSize(60).setExplain(true).execute().actionGet();
	
		return searchResponse;
	}
	
	public static SearchResponse searchQueryWihtFields(String index, String name, String query, int limit, int page) {
		int paggination = (page-1)*limit;
		QueryBuilder qb;
		if(query == "")	{
		 qb = QueryBuilders.matchAllQuery();
		}else{
		 qb = QueryBuilders.multiMatchQuery(query+"*", "name" ,query+"*", "surname");
		}
		searchResponse = ElasticClient.getInstance().getClient().prepareSearch(index)
				.setTypes(name).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb) 
				.setFrom(paggination).setSize(limit).setExplain(true).execute().actionGet();
	
		return searchResponse;
	
	}
//speeches of a member with given id
	public static SearchResponse searchSpecificListMember(String index, String name, Integer id, Integer limit, int page, String qtext, String from, String to) {
		int paggination = (page-1)*limit;
		QueryBuilder qb = QueryBuilders.multiMatchQuery(id, "speech-member-id", "*"+ qtext +"*", "text");
		
		SearchRequestBuilder query = ElasticClient.getInstance().getClient().prepareSearch(index)
				.setTypes(name).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb);
		
		if (from != "" && to != "" ) {
			query.setPostFilter(QueryBuilders.rangeQuery("sessiondate").from(from).to(to));
		}
		if (from != "" && to.equals("")){
			query.setPostFilter(QueryBuilders.rangeQuery("sessiondate").from(from));
		}
		if (to != "" && from.equals("")){
			query.setPostFilter(QueryBuilders.rangeQuery("sessiondate").to(to));
		}
		
		searchResponse = query.setFrom(paggination).setSize(limit).setExplain(true).execute().actionGet();
	
		return searchResponse;
	}
//speeches of a plenary session with given id
		public static SearchResponse searchSpecificListSession(String index, String name,String field, Integer id, Integer limit, int page) {
			
			QueryBuilder qb = QueryBuilders.matchQuery(field,id);
			int pagination = (page-1)*limit;
			searchResponse = ElasticClient.getInstance().getClient().prepareSearch(index)
					.setTypes(name).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
					.setQuery(qb)
					.setFrom(pagination).setSize(limit).setExplain(true).execute().actionGet();
		
			return searchResponse;
		}
		
//list of members from specific party
		public static SearchResponse searchSpecificPartyMember(String index, String name, Integer id, Integer limit, int page) {
			
			QueryBuilder qb = QueryBuilders.matchQuery("party-id", id);
			int paggination = (page-1)*limit;
			searchResponse = ElasticClient.getInstance().getClient().prepareSearch(index)
					.setTypes(name).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
					.setQuery(qb)
					.setFrom(paggination).setSize(limit).setExplain(true).execute().actionGet();
		
			return searchResponse;
		}

	public static SearchResponse searchResponse;

	public SearchResponse getSearchResponse() {
		return searchResponse;
	}

	public void setSearchResponse(SearchResponse searchResponse) {
		ElasticSearchService.searchResponse = searchResponse;
	}

}