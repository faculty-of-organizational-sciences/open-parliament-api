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

		if (source.get("member-parties") != null) {

			List<Member> members = new LinkedList<>();

			for (Object member : (List<?>) source.get("party-members")) {
				Member m1 = (Member) member;
				System.out.println(m1.getId());
			}

			party.setMembers(members);
		}
		return party;
	}

	public static List<Member> convertToPartyMembers(SearchResponse partyData) {

		List<Member> members = new LinkedList<>();

		for (SearchHit m : partyData.getHits()) {

			Map<String, Object> source = m.getSource();

			if (source.get("member-parties") != null) {

				for (Object member : (List<?>) source.get("party-members")) {
					Member m1 = (Member) member;
					System.out.println(m1.getId());
				}
			}
		}
		return members;
	}
}
