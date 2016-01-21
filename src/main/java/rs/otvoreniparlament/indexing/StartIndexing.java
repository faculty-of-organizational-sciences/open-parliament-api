package rs.otvoreniparlament.indexing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StartIndexing {
	
	private static final Logger logger = LogManager.getLogger(StartIndexing.class);

	public static void main(String[] args) {
		logger.info("Indexing started");
		
		indexMembers();
		indexSpeeches();
		indexParties();
		indexSessions();
		
		logger.info("Indexing ended");
	}

	private static void indexSessions() {
		IndexingPlenarySessions ips = new IndexingPlenarySessions();
		ips.deleteParties();
		logger.info("Deleted... Indexing started for sessions!");
		ips.indexPlenarySessions();
	}

	private static void indexMembers() {
		IndexingMembers im = new IndexingMembers();
		im.deleteMembers();
		logger.info("Deleted... Indexing started for members!");
		im.indexMembers();
	}

	private static void indexParties() {
		IndexingParties ip = new IndexingParties();
		ip.deleteParties();
		logger.info("Deleted... Indexing started for parties!");
		ip.indexParties();
	}

	private static void indexSpeeches() {
		logger.info("Indexing speeches");
		
		IndexingSpeeches is = new IndexingSpeeches();
		is.deleteSpeeches();
		logger.info("Speeches deleted");
		is.indexSpeeches();
		logger.info("Indexing speeches completed");
	}
}
