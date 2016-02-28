package rs.otvoreniparlament.api.service.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.monitor.jvm.JvmInfo.Mem;
import org.elasticsearch.search.SearchHit;

import com.google.gson.Gson;

import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.index.ElasticSearchService;
import rs.otvoreniparlament.api.service.PartyServiceImp;
import rs.otvoreniparlament.indexing.IndexName;
import rs.otvoreniparlament.indexing.IndexType;

public class PartyConvertor {

	public static List<Party> convertToParties(SearchResponse partyData) {

		List<Party> parties = new LinkedList<>();

		for (SearchHit m : partyData.getHits()) {
			Party party = convertToParty(m);
			parties.add(party);
		}

		return parties;
	}

	public static Party convertToParty(SearchHit partyData) {

		Party party = new Party();

		Map<String, Object> source = partyData.getSource();

		if (source.get("party-id") != null)
			party.setId((int) source.get("party-id"));

		if (source.get("party-name") != null)
			party.setName((String) source.get("party-name"));

		if (source.get("party-members") != null) {

			List<Member> members = new LinkedList<>();
			ArrayList<Member> array = (ArrayList<Member>) source.get("party-members");

			party.setMembers(array);
			List<Member> all = party.getMembers();
			for (Member m : all) {
				System.out.println(m.getId());
			}
		}

		return party;
	}

	public static List<Member> convertToPartyMembers(SearchResponse partyData) {

		List<Member> membermain = new LinkedList<>();

		for (SearchHit m : partyData.getHits()) {

			Map<String, Object> source = m.getSource();

			if (source.get("party-members") != null) {

				List<Member> members = new LinkedList<>();
				ArrayList<Member> array = (ArrayList<Member>) source.get("party-members");

				for (int i = 0; i < array.size(); i++) {
					System.out.println(array.get(i));
					Gson gson=new Gson();
					String json = gson.toJson(array);
					System.out.println(json);
					ElasticSearchService es = new ElasticSearchService();
//					query treba da se prosledi kao string
//					es.searchSpecificID(IndexName.MEMBER_INDEX, IndexType.MEMBER_TYPE, "id", id)
					
				}
			}
		}
		return membermain;
	}

	public static void main(String[] args) {
		PartyServiceImp i = new PartyServiceImp();
		i.getPartyMembers(426, 10, 1);
	}
}
