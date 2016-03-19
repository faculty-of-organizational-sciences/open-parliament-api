package rs.otvoreniparlament.api.index.startup;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentFactory;

import rs.otvoreniparlament.api.dao.SpeechDao;
import rs.otvoreniparlament.api.domain.Speech;
import rs.otvoreniparlament.api.index.ElasticClient;
import rs.otvoreniparlament.api.index.IndexName;
import rs.otvoreniparlament.api.index.IndexType;
import rs.otvoreniparlament.api.rest.util.formatters.DateFormatter;

public class IndexingSpeeches {

	SpeechDao sd = new SpeechDao();
	List<Speech> speechesForIndexing = sd.getSpeeches(200000, 1);
	
	private static final Logger logger = LogManager.getLogger(IndexingSpeeches.class);
	public void indexSpeeches (){
		for (Speech speech : speechesForIndexing) {
			try {
				Integer id;
				String text;
				Integer plenarySessionId;
				Date sessionDate;
				Integer memberId;
				String memberName;
				String memberLastName;
				
				if (speech.getId() != null) {
					id = speech.getId();
				} else {
					id = null;
				}
				
				if(speech.getText() != null) {
					text = speech.getText();
				} else {
					text = "";
				}
				
				if( speech.getPlenarySession() != null && speech.getPlenarySession().getId() != null) {
					plenarySessionId = speech.getPlenarySession().getId();
				} else {
					plenarySessionId = null;
				}
				
				if(speech.getSessionDate() != null) {
					sessionDate = speech.getSessionDate();
				} else {
					sessionDate = DateFormatter.parseFullTimeDate("0000-00-00");
				}
				
				if(speech.getMember() != null && speech.getMember().getId() != null) {
					memberId = speech.getMember().getId();
				} else {
					memberId = null;
				}
				if(speech.getMember() != null && speech.getMember().getName() != null) {
					memberName = speech.getMember().getName();
				} else {
					memberName = null;
				}
				if(speech.getMember() != null && speech.getMember().getLastName()!= null) {
					memberLastName = speech.getMember().getLastName();
				} else {
					memberLastName = null;
				}
				
				IndexResponse response = ElasticClient.getInstance().getClient().prepareIndex(IndexName.SPEECH_INDEX, IndexType.SPEECH_TYPE, speech.getId().toString())
				        .setSource(XContentFactory.jsonBuilder()
				                    .startObject()
			                    		.field("speechid", id != null ? id : "")
				                        .field("text", text)
				                        .field("sessionId", plenarySessionId != null ? plenarySessionId : "")
				                        .field("sessiondate", sessionDate)
				                        .field("speech-member-id", memberId != null ? memberId : "")
				                        .field("speech-member-name", memberName != null ? memberName : "")
				                        .field("speech-member-surname", memberLastName != null ? memberLastName : "")
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
////				System.out.println(_id);
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
