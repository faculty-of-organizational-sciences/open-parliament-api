package rs.otvoreniparlament.indexing;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentFactory;
import rs.otvoreniparlament.api.dao.MembersDao;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.index.ElasticClient;

public class IndexingMembers {

	
	private MembersDao md = new MembersDao();
	private List<Member> membersForIndexing = md.getMembers(1, 1000 ,null, null );
	
	public void indexMembers (){
		for (Member member : membersForIndexing) {
			try {
				IndexResponse response = ElasticClient.getInstance().getClient().prepareIndex("datasearch", "member", IndexType.MEMBER_TYPE)
				        .setSource(XContentFactory.jsonBuilder()
				                    .startObject()
				                        .field("name", member.getName())
				                        .field("surname", member.getLastName())
				                        .field("parties", member.getParties())
				                    .endObject()
				                  )
				        .get();
				
//				String _index = response.getIndex();
//				System.out.println(_index);
//				// Type name
//				String _type = response.getType();
//				
//				System.out.println(_type);
//				 Document ID (generated or not)
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
				// TODO
//				logger.error
			}
		}
	}
	
	public void deleteMembers(){
		for (Member members : membersForIndexing) {			
			DeleteResponse deleteResponse = ElasticClient.getInstance().getClient().prepareDelete("datasearch", "member", IndexType.MEMBER_TYPE).get();
			
		}
	}
}
