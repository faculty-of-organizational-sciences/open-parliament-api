package rs.otvoreniparlament.indexing;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentFactory;
import rs.otvoreniparlament.api.dao.MembersDao;
import rs.otvoreniparlament.api.dao.SpeechDao;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.index.ElasticClient;

public class IndexingMembers {

	
	private MembersDao md = new MembersDao();
	private SpeechDao sd = new SpeechDao();
	private List<Member> membersForIndexing = md.getMembers(1, 10000 ,null, null );
	
	public void indexMembers (){
		for (Member member : membersForIndexing) {
			try {
				IndexResponse response = ElasticClient.getInstance().getClient().prepareIndex("datasearch", "member", IndexType.MEMBER_TYPE)
				        .setSource(XContentFactory.jsonBuilder()
				                    .startObject()
				                    	.field("id", member.getId())
				                        .field("name", member.getName())
				                        .field("surname", member.getLastName())
				                        .field("dateofbirth", member.getDateOfBirth())
				                        .field("gender", member.getGender())
				                        .field("mail", member.getEmail())
				                        .field("biography", member.getBiography())
				                        .field("placeofbirth", member.getPlaceOfBirth())
				                        .field("placeofresidence", member.getPlaceOfResidence())
				                        .field("parties", member.getParties())
				                        
				                    .endObject()
				                  )
				        .get();
				List<Speech> speechesOfMember = sd.getMemberSpeeches(member.getId(), 1000, 1, null, "", "");
				for (Speech speech : speechesOfMember) {
					IndexResponse responseMembers = ElasticClient.getInstance().getClient().prepareIndex("datasearch", "member", IndexType.PARTY_TYPE+".1")
					        .setSource(XContentFactory.jsonBuilder()
					                    .startObject()
					                        .field("speeches", speech.getText())
					                        .field("plenarysession", speech.getPlenarySession())
					                        .field("sessiondate", speech.getSessionDate())
					                    .endObject()
					                  )
					        .get();
				}
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
