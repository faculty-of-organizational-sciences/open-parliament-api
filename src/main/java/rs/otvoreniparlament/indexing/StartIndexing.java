package rs.otvoreniparlament.indexing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import rs.otvoreniparlament.api.util.ResourceBundleUtil;
import rs.otvoreniparlament.api.util.exceptions.KeyNotFoundInBundleException;

public class StartIndexing {
	
	private static final Logger logger = LogManager.getLogger(StartIndexing.class);

	public static void main(String[] args) {
		
		System.out.println("Indexing started");
		//logger.info("Indexing started");
		
		indexMembers();
		indexParties();
		indexSessions();
//		indexSpeeches();
		
//		logger.info("Indexing ended");
		System.out.println("Indexing finished");
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
		IndexingSpeeches is = new IndexingSpeeches();
		is.deleteSpeeches();
//		logger.info("Deleted... Indexing started for Speeches!");
		System.out.println("Deleting, and starting");
		is.indexSpeeches();
	}
}
