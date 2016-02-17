package rs.otvoreniparlament.api.service.util;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.formatters.DateFormatter;

public class MembersConvertor {

	public static List<Member> convertToMembers(SearchResponse membersData) {
		List<Member> members = new LinkedList<>();

		for (SearchHit m : membersData.getHits()) {

			Member member = convertToMember(m);
			members.add(member);
		}

		return members;
	}

	public static Member convertToMember(SearchHit memberData) {

		Member member = new Member();

		Map<String, Object> source = memberData.getSource();

		member.setId((int) source.get("id"));

		member.setName((String) source.get("name"));

		member.setLastName((String) source.get("surname"));

		if (source.get("mail") != null) {
			member.setEmail((String) source.get("mail"));
		}
		if (source.get("biography") != null) {
			member.setBiography((String) source.get("biography"));
		}
		if (source.get("birth-town") != null) {
			member.getPlaceOfBirth().setName((String) source.get("birth-town"));
		}
		if (source.get("residence-town") != null) {
			member.getPlaceOfResidence().setName((String) source.get("residence-town"));
		}
		if (source.get("dateofbirth") != null) {
			member.setDateOfBirth((Date) DateFormatter.parseFullTimeDate(source.get("dateofbirth").toString()));
		}
		if (source.get("gender") != null) {
			member.setGender((String) source.get("gender"));
		}

		if (source.get("member-parties") != null) {

			List<Party> parties = new LinkedList<>();

			for (Object party : (List<?>) source.get("member-parties")) {
				Party p = new Party();
				System.out.println("ID partije: " + source.get("party-id"));
				System.out.println("ID partije: " + source.get("party-name"));
				p.setId((Integer) source.get("party-id"));
				p.setName((String) source.get("party-name"));
				parties.add(p);
			}
			member.setParties(parties);
		}
		return member;
	}
}
