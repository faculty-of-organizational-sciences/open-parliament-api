package rs.otvoreniparlament.api.index;

import rs.otvoreniparlament.api.config.Settings;

public class ElasticAvailability {
	
	public static boolean isAvailable() {
		
		if (ElasticClient.getInstance().isConnectionStatus() == false || Settings.getInstance().config.getElasticConfig().isUsingElastic()==false){
			return false;
		}
		return true;
	}

}
