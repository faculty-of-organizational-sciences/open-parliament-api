package rs.otvoreniparlament.api.service.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;

public class PartyConvertor {

	public static List<Party> convertToParties(SearchResponse partyData) {
		List<Party> parties = new LinkedList<>();
		for (SearchHit m : partyData.getHits()) {
			Map<String, Object> source = m.getSource();
			Party party = new Party();
			party.setId((int) source.get("party-id"));
			party.setName((String) source.get("party-name"));

			if (source.get("member-parties") != null) {
				List<Member> members = new LinkedList<>();
				for (Object member : (List<?>) source.get("party-members")) {
					System.out.println(member.getClass());
				}
				party.setMembers(members);
			}
			parties.add(party);
		}
		
		return parties;
	}
	public static Party convertToParty(SearchResponse partyData){
		Party party = new Party();
		for (SearchHit m : partyData.getHits()) {
			Map<String, Object> source = m.getSource();
			party.setId((int) source.get("party-id"));
			party.setName((String) source.get("party-name"));

			if (source.get("member-parties") != null) {
				List<Member> members = new LinkedList<>();
				for (Object member : (List<?>) source.get("party-members")) {
					System.out.println(member.getClass());
				}
				party.setMembers(members);
			}
		}
		return party;
	}
}
