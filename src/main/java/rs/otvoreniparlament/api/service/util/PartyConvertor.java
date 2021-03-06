package rs.otvoreniparlament.api.service.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import com.google.gson.Gson;

import rs.otvoreniparlament.api.domain.Member;
import rs.otvoreniparlament.api.domain.Party;
import rs.otvoreniparlament.api.index.ElasticSearchService;
import rs.otvoreniparlament.api.index.IndexName;
import rs.otvoreniparlament.api.index.IndexType;

public class PartyConvertor {

	private static final Logger logger = LogManager.getLogger(MembersConvertor.class);
	
	private static ElasticSearchService elasticSearch  = new ElasticSearchService();
	
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

			party.setId((int) source.get("party-id"));
			if(source.get("party-name")!= null || source.get("party-name") != ""){
				party.setName((String) source.get("party-name"));
			}

		return party;
	}

	@SuppressWarnings("unchecked")
	public static List<Member> convertToPartyMembers(SearchResponse partyData) {

		List<Member> members = new LinkedList<>();
		for (SearchHit m : partyData.getHits()) {

			Map<String, Object> source = m.getSource();

			if (source.get("party-members") != null || source.get("party-members") != "" ) {

				ArrayList<Member> array = (ArrayList<Member>) source.get("party-members");

				for (int i = 0; i < array.size(); i++) {
					String query = "";
					Gson gson=new Gson();
					String json = gson.toJson(array.get(i));
					try {
						JSONObject jsonObj = new JSONObject(json);
						query += jsonObj.getString("id");
						
					} catch (JSONException e) {
						logger.error(e);
					}
					
					SearchResponse search = elasticSearch.searchSpecificID(IndexName.MEMBER_INDEX, IndexType.MEMBER_TYPE, "id", query);
					if (search.getHits().getTotalHits() == 0) {
						return null;
					}
					Member member = MembersConvertor.convertToMemberOfParty(search.getHits().getAt(0));
					members.add(member);
				}
			}
		}
		return members;
	}
}
