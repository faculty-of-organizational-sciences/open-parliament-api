package rs.otvoreniparlament.indexing;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentFactory;

import rs.otvoreniparlament.api.dao.PartyDao;
import rs.otvoreniparlament.api.dao.PlenarySessionDao;
import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.domain.PlenarySession;
import rs.otvoreniparlament.api.index.ElasticClient;

public class IndexingPlenarySessions {

	PlenarySessionDao psd ;
	List<PlenarySession> sessionsForIndexing ;
	
	public IndexingPlenarySessions(){
		psd = new PlenarySessionDao();
		sessionsForIndexing = psd.getPlenarySessions(100000, 1);
	}
	
	private static final Logger logger = LogManager.getLogger(IndexingPlenarySessions.class);
	public void indexPlenarySessions (){
		for (PlenarySession plenarySession : sessionsForIndexing) {
			try {
				IndexResponse response = ElasticClient.getInstance().getClient().prepareIndex(IndexName.SESSION_INDEX, IndexType.SESSION_TYPE, plenarySession.getId().toString())
				        .setSource(XContentFactory.jsonBuilder()
				                    .startObject()
				                    	.field("id", plenarySession.getId()!= null ? plenarySession.getId() : "")
				                    	.field("agenda", plenarySession.getAgenda() != null ? plenarySession.getAgenda() : "")
				                    	.field("transcript", plenarySession.getTranscriptText()!= null ? plenarySession.getTranscriptText() : "")
				                    	.field("date", plenarySession.getDate()!= null ? plenarySession.getDate() : "")
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
				
//				System.out.println(created);
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}
	public void deleteSessions(){
		ElasticClient.getInstance().getClient().admin().indices().delete(Requests.deleteIndexRequest(IndexName.SESSION_INDEX));
	}
}
