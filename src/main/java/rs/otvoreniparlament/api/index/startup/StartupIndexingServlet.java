package rs.otvoreniparlament.api.index.startup;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rs.otvoreniparlament.api.config.Settings;

public class StartupIndexingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 2665061837084831925L;
	
	private static final Logger logger = LogManager.getLogger(StartupIndexingServlet.class);

	public void init() throws ServletException {
		if (Settings.getInstance().config.elasticConfig.isIndexDataOnStartup()) {
			logger.info("Indexing started");
			
			indexMembers();
			indexParties();
			indexSpeeches();
			indexSessions();
			
			logger.info("Indexing completed");
		}
	}

	private static void indexSessions() {
		IndexingPlenarySessions ips = new IndexingPlenarySessions();
		ips.deleteSessions();
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
		logger.info("Deleted... Indexing started for Speeches!");
		is.indexSpeeches();
	}
}
