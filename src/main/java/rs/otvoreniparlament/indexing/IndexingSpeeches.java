package rs.otvoreniparlament.indexing;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentFactory;

import rs.otvoreniparlament.api.dao.SpeechDao;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.index.ElasticClient;

public class IndexingSpeeches {

	SpeechDao sd = new SpeechDao();
	List<Speech> speechesForIndexing = sd.getSpeeches(200000, 1);
	
	private static final Logger logger = LogManager.getLogger(IndexingSpeeches.class);
	public void indexSpeeches (){
		for (Speech speech : speechesForIndexing) {
			try {
				String id;
				String text;
				String plenarySessionId;
				String sessionDate;
				String memberId;
				if (speech.getId() != null) {
					id = speech.getId().toString();
				} else {
					id = "";
				}
				
				if(speech.getText() != null) {
					text = speech.getText();
				} else {
					text = "";
				}
				
				if( speech.getPlenarySession() != null && speech.getPlenarySession().getId() != null) {
					plenarySessionId = speech.getPlenarySession().getId().toString();
				} else {
					plenarySessionId = "";
				}
				
				if(speech.getSessionDate() != null) {
					sessionDate = speech.getSessionDate().toString();
				} else {
					sessionDate = "";
				}
				
				if(speech.getMember() != null && speech.getMember().getId() != null) {
					memberId = speech.getMember().getId().toString();
				} else {
					memberId = "";
				}
				
				IndexResponse response = ElasticClient.getInstance().getClient().prepareIndex(IndexName.SPEECH_INDEX, IndexType.SPEECH_TYPE, speech.getId().toString())
				        .setSource(XContentFactory.jsonBuilder()
				                    .startObject()
			                    		.field("speechid", id)
				                        .field("text", text)
				                        .field("sessionId", plenarySessionId)
				                        .field("sessiondate", sessionDate)
				                        .field("speech-member-id", memberId)
				        				.endObject()
				                    
				                  )
				        .get();
				String _index = response.getIndex();
				System.out.println(_index);
				// Type name
				String _type = response.getType();
				
				System.out.println(_type);
				// Document ID (generated or not)
				String _id = response.getId();
				
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
	public void deleteSpeeches(){
		ElasticClient.getInstance().getClient().admin().indices().delete(Requests.deleteIndexRequest(IndexName.SPEECH_INDEX));
	}
}
