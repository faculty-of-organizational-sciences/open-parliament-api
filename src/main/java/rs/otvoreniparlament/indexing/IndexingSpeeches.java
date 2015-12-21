package rs.otvoreniparlament.indexing;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.omg.CORBA.PUBLIC_MEMBER;

import rs.otvoreniparlament.api.dao.SpeechDao;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.index.ElasticClient;

public class IndexingSpeeches {

	SpeechDao sd = new SpeechDao();
	List<Speech> speechesForIndexing = sd.getSpeeches(1000, 1);
	public void indexSpeeches (){
		for (Speech speech : speechesForIndexing) {
			try {
				IndexResponse response = ElasticClient.getInstance().getClient().prepareIndex("datasearch", "speech", IndexType.SPEACH_TYPE)
				        .setSource(XContentFactory.jsonBuilder()
				                    .startObject()
				                    		.field("speechid", speech.getId())
				                        .field("member", speech.getMember())
				                        .field("text", speech.getText())
				                        .field("session", speech.getPlenarySession())
				                        .field("sessiondate", speech.getSessionDate())
				                    .endObject()
				                  )
				        .get();
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
	public void deleteSpeeches(){
		for (Speech speechs : speechesForIndexing) {			
			DeleteResponse deleteResponse = ElasticClient.getInstance().getClient().prepareDelete("datasearch", "speech", IndexType.SPEACH_TYPE).get();
			
		}
	}
	
	
}
