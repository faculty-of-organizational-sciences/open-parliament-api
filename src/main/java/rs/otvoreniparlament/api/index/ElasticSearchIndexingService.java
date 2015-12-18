package rs.otvoreniparlament.api.index;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;

public class ElasticSearchIndexingService {
		

//				
//				//Get document
//				GetResponse response = ElasticClient.getInstance().getClient().prepareGet("dataSearch", "members", "1").get();
//				System.out.println(response.getIndex());
//	
		
				//Search
				public void searchQuery(String query){
					
				}
				SearchResponse searchResponse = ElasticClient.getInstance().getClient().prepareSearch("dataSearch")
				        .setTypes("members", "parties")
				        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				        .setQuery(QueryBuilders.termQuery("member", "speech"))  // Query
				        .setFrom(0).setSize(60).setExplain(true)
				        .execute()
				        .actionGet();
				
				
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