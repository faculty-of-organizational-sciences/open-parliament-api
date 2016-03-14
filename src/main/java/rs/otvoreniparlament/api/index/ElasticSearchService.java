package rs.otvoreniparlament.api.index;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public class ElasticSearchService {

	// Search
	public SearchResponse searchQuery(	String index, 
										String name, 
										String query, 
										int limit, 
										int page) {
		
		int paggination = (page - 1) * limit;
		QueryBuilder qb;
		
		if (query == "") {
			qb = QueryBuilders.matchAllQuery();
		} else {
			qb = QueryBuilders.queryStringQuery(query + "*");
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

	public SearchResponse searchSpecificID(	String index, 
											String name, 
											String field, 
											String id) {

		QueryBuilder qb = QueryBuilders.matchQuery(field, id);

		SearchResponse searchResponse = ElasticClient.getInstance()
													 .getClient()
													 .prepareSearch(index)
												 	 .setTypes(name)
													 .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
													 .setQuery(qb)
													 .setSize(1)
													 .setExplain(true)
													 .execute().actionGet();

		return searchResponse;
	}

}