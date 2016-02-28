package rs.otvoreniparlament.indexing;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import rs.otvoreniparlament.api.dao.PartyDao;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.index.ElasticClient;

public class IndexingParties {
	
	private static final Logger logger = LogManager.getLogger(IndexingParties.class);
	
	private PartyDao pd;
	private List<Party> partiesForIndexing;
	
	public IndexingParties() {
		pd = new PartyDao();
		partiesForIndexing = pd.getParties(1, 10000 , "ASC", "");
	}
	
	public void indexParties (){
		try {
			for (Party party : partiesForIndexing) {
				XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
				builder
	            	.field("party-id", party.getId())
	                .field("party-name", party.getName());

				List<Member> partiyMembrsForIndexing = pd.getPartyMembers(party.getId(), 1000,1);
				
				builder.startArray("party-members");
				
				for (Member member : partiyMembrsForIndexing) {
					builder.startObject()
						.field("id", member.getId())
					.endObject();
				}
				
				builder.endArray();
				builder.endObject();
				
				IndexResponse response = ElasticClient.getInstance().getClient().prepareIndex(IndexName.PARTY_INDEX, IndexType.PARTY_TYPE, party.getId().toString())
						.setSource(builder).execute().actionGet();
				
//				String _index = response.getIndex();
//				System.out.println(_index);
//				// Type name
//				String _type = response.getType();
//				
//				System.out.println(_type);
//				// Document ID (generated or not)
//				String _id = response.getId();
//				
//				System.out.println(_id);
//				// Version (if it's the first time you index this document, you will get: 1)
//				long _version = response.getVersion();
//				
//				System.out.println(_version);
//				// isCreated() is true if the document is a new one, false if it has been updated
//				boolean created = response.isCreated();
//				
//				System.out.println(created);
			}
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public void deleteParties(){
		ElasticClient.getInstance().getClient().admin().indices().delete(Requests.deleteIndexRequest(IndexName.PARTY_INDEX));
	}
}
