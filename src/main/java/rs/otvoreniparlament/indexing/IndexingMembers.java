package rs.otvoreniparlament.indexing;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import rs.otvoreniparlament.api.dao.MembersDao;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.index.ElasticClient;

public class IndexingMembers {
	private MembersDao md = new MembersDao();
	private List<Member> membersForIndexing = md.getMembers(1, 10000 , "ASC", "");
	
	private static final Logger logger = LogManager.getLogger(IndexingMembers.class);
	
	public void indexMembers (){
		for (Member member : membersForIndexing) {
			try {
				XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
				
				builder
                	.field("id", member.getId())
                    .field("name", member.getName())
                    .field("surname", member.getLastName())
                    .field("dateofbirth", member.getDateOfBirth())
                    .field("gender", member.getGender())
                    .field("mail", member.getEmail())
                    .field("biography", member.getBiography())
                    .field("placeofbirth", builder.startObject()
												.field("member-birth-town", member.getPlaceOfBirth().getName())
												.field("member-birth-region", member.getPlaceOfBirth().getRegion())
												.field("member-birth-country", member.getPlaceOfBirth().getCountry())
											.endObject())
                    .field("placeofresidence", builder.startObject()
                    								.field("member-residence-town", member.getPlaceOfResidence().getName())
                    								.field("member-residence-region", member.getPlaceOfResidence().getRegion())
                    								.field("member-residence-country", member.getPlaceOfResidence().getCountry())
                    							.endObject());
                        
                builder.startArray("member-parties");
				
				for (Party party : member.getParties()) {
					builder.startObject()
						.field("member-party-id", party.getId().toString())
						.field("member-party-name1", party.getName())
					.endObject();
				}
				
				builder.endArray();        
                
                builder.endObject();
				
				IndexResponse response = ElasticClient.getInstance().getClient().prepareIndex(IndexName.MEMBER_INDEX, IndexType.MEMBER_TYPE, member.getId().toString())
				        .setSource(builder)
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
				logger.error(e);
				
			}
		}
	}
	
	public void deleteMembers(){
		for (Member member : membersForIndexing) {			
			DeleteResponse deleteResponse = ElasticClient.getInstance().getClient().prepareDelete(IndexName.MEMBER_INDEX, IndexType.MEMBER_TYPE, member.getId().toString()).get();
			
		}
	}
}
