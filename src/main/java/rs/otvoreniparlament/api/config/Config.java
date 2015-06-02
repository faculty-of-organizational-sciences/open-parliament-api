package rs.otvoreniparlament.api.config;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

public class Config {

	public DbConfig dbConfig;
	
	@SerializedName ("query")
	public QueryConfig query;

	public DbConfig getDbConfig() {
		return dbConfig;
	}

	public void setDbConfig(DbConfig dbConfig) {
		this.dbConfig = dbConfig;
	}

	public QueryConfig getQuery() {
		return query;
	}

	public void setQuery(QueryConfig query) {
		this.query = query;
	}
	
}
