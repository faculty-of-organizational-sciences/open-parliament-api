package rs.otvoreniparlament.api.index;

import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public class ElasticCount {
	public CountResponse countResponse;
	public  CountResponse searchQuery(String index, String name, String query) {
		QueryBuilder qb;
		if(query == "")	{
		 qb = QueryBuilders.matchAllQuery();
		}else{
		 qb = QueryBuilders.queryStringQuery(query + "*");
		}
		countResponse = ElasticClient.getInstance().getClient().prepareCount(index)
				.setTypes(name)
				.setQuery(qb) // Query
				.execute().actionGet();
	
		return countResponse;
		
	
	}

}
