package rs.otvoreniparlament.indexing;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentFactory;

import rs.otvoreniparlament.api.dao.PartyDao;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.index.ElasticClient;

public class IndexingParties {

	
	PartyDao pd = new PartyDao();
	List<Party> partiesForIndexing = pd.getParties(1, 1000 ,null, null );
	public void indexParties (){
		for (Party party : partiesForIndexing) {
			try {
				IndexResponse response = ElasticClient.getInstance().getClient().prepareIndex("datasearch", "party", IndexType.PARTY_TYPE)
				        .setSource(XContentFactory.jsonBuilder()
				                    .startObject()
				                        .field("name", party.getName())
				                    .endObject()
				                  )
				        .get();
				List<Member> partiyMembrsForIndexing = pd.getPartyMembers(party.getId(), 1000,1);
				for (Member member : partiyMembrsForIndexing) {
					IndexResponse responseMembers = ElasticClient.getInstance().getClient().prepareIndex("datasearch", "party", IndexType.PARTY_TYPE+".1")
					        .setSource(XContentFactory.jsonBuilder()
					                    .startObject()
					                        .field("member", member)
					                    .endObject()
					                  )
					        .get();

//					String _index = responseMembers.getIndex();
//					System.out.println(_index);
				}
				
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void deleteParties(){
		for (Party parties : partiesForIndexing) {			
			DeleteResponse deleteResponse = ElasticClient.getInstance().getClient().prepareDelete("datasearch", "party", IndexType.PARTY_TYPE).get();
			
		}
	}
}
