package rs.otvoreniparlament.indexing;

public class IndexMain {

	public static void main(String[] args) {
		IndexingMembers im = new IndexingMembers();
		im.deleteMembers();
		System.out.println("Deleted... Indexing started for members!");
		im.indexMembers();
		IndexingSpeeches is = new IndexingSpeeches();
		is.deleteSpeeches();
		System.out.println("Deleted... Indexing started for speeches!");
		is.indexSpeeches();
		IndexingParties ip = new IndexingParties();
		ip.deleteParties();
		System.out.println("Deleted... Indexing started for parties!");
		ip.indexParties();
		IndexingPlearySessions ips = new IndexingPlearySessions();
		ips.deleteParties();
		System.out.println("Deleted... Indexing started for sessions!");
		ips.indexPlenarySessions();
		System.out.println("THE END!");
	}
}
