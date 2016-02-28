package rs.otvoreniparlament.api.service.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

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
			@SuppressWarnings("unchecked")
			ArrayList<Party> al = (ArrayList<Party>) source.get("member-parties");

			System.out.println(al);
			member.setParties(al);
			List<Party> all = member.getParties();

			for (Party party : all) {
				System.out.println(party);
			}
		}
		return member;
	}
}
