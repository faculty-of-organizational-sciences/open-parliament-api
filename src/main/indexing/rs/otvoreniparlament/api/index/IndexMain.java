package rs.otvoreniparlament.api.index;

public class IndexMain {

	public static void main(String[] args) {
		IndexingMembers im = new IndexingMembers();
		im.indexMembers();
		IndexingSpeeches is = new IndexingSpeeches();
		is.indexSpeeches();
		IndexingParties ip = new IndexingParties();
		ip.indexParties();
	}
}
