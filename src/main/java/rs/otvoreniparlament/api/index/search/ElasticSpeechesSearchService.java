package rs.otvoreniparlament.api.index.search;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;

import rs.otvoreniparlament.api.index.ElasticClient;
import rs.otvoreniparlament.api.index.ElasticSearchService;

public class ElasticSpeechesSearchService extends ElasticSearchService {
	
	/**
	 * Speeches of a specific member with the given id
	 * 
	 * @param index Index name for the elastic
	 * @param name Index type for the elastic
	 * @param id The id of the member
	 * @param limit Number of results to return
	 * @param page Page number of the results for the given limit
	 * @param qtext Text by which are speeches selected
	 * @param from Include speeches from the given date
	 * @param to Include speeches to the given date
	 * @return SearchResponse object that contains speeches to return
	 */
	
	public SearchResponse searchMemberSpeeches(	String index, 
												String name, 
												Integer id, 
												Integer limit,
												int page, 
												String qtext, 
												String from, 
												String to) {
		
		int paggination = (page - 1) * limit;
		QueryBuilder qb = QueryBuilders.multiMatchQuery(id, "speech-member-id", "*" + qtext + "*", "text");

		SearchRequestBuilder query = ElasticClient.getInstance()
												  .getClient()
								  				  .prepareSearch(index)
												  .setTypes(name)
												  .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
												  .setQuery(qb);

		RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("sessiondate");

		if (!from.isEmpty()) {
			rangeQuery.from(from);
		}

		if (!to.isEmpty()) {
			rangeQuery.to(to);
		}
		
		SearchResponse searchRespnse = query.setPostFilter(rangeQuery)
											.setFrom(paggination)
											.setSize(limit)
											.setExplain(true)
											.execute().actionGet();

		return searchRespnse;
	}

	/**
	 * Speeches of a plenary session with given id
	 * 
	 * @param index
	 * @param name
	 * @param field
	 * @param id
	 * @param limit
	 * @param page
	 * @return
	 */
	public  SearchResponse searchPlenarySessionSpeeches(String index, 
														String name, 
														String field, 
														Integer id,
														Integer limit, 
														int page) {

		int pagination = (page - 1) * limit;
		QueryBuilder qb = QueryBuilders.matchQuery(field, id);
		
		SearchResponse searchResponse = ElasticClient.getInstance()
													 .getClient()
													 .prepareSearch(index)
													 .setTypes(name)
													 .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
													 .setQuery(qb)
													 .setFrom(pagination)
													 .setSize(limit)
													 .setExplain(true)
													 .execute().actionGet();

		return searchResponse;
	}

}
