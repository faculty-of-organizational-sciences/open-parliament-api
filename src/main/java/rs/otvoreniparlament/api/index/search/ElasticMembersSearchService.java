package rs.otvoreniparlament.api.index.search;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import rs.otvoreniparlament.api.index.ElasticClient;
import rs.otvoreniparlament.api.index.ElasticSearchService;

public class ElasticMembersSearchService extends ElasticSearchService {
	
	public SearchResponse searchQueryWihtFields(String index, 
												String name, 
												String query, 
												int limit, 
												int page) {
		
		int paggination = (page - 1) * limit;
		QueryBuilder qb;

		if (query.isEmpty()) {
			qb = QueryBuilders.matchAllQuery();
		} else {
			qb = QueryBuilders.multiMatchQuery(query + "*", "name", query + "*", "surname");
		}

		SearchResponse searchResponse = ElasticClient.getInstance()
													 .getClient()
													 .prepareSearch(index)
													 .setTypes(name)
													 .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
													 .setQuery(qb)
													 .setFrom(paggination)
													 .setSize(limit)
													 .setExplain(true)
													 .execute().actionGet();

		return searchResponse;

	}

	
	// list of members from specific party
	public SearchResponse searchSpecificPartyMember(String index, 
													String name, 
													Integer id, 
													Integer limit,
													int page) {

		int paggination = (page - 1) * limit;
		QueryBuilder qb = QueryBuilders.matchQuery("party-id", id);

		SearchResponse searchResponse = ElasticClient.getInstance()
											 		 .getClient()
											 		 .prepareSearch(index)
													 .setTypes(name)
													 .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
													 .setQuery(qb)
													 .setFrom(paggination)
													 .setSize(limit)
													 .setExplain(true)
													 .execute().actionGet();

		return searchResponse;
	}
}
