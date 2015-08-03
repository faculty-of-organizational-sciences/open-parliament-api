package rs.otvoreniparlament.api.config;

import com.google.gson.annotations.SerializedName;

public class Config {

	public DbConfig dbConfig;

	@SerializedName("query")
	public QueryConfig query;

	public UriConfig uriConfig;

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

	public UriConfig getUriConfig() {
		return uriConfig;
	}

	public void setUriConfig(UriConfig uriConfig) {
		this.uriConfig = uriConfig;
	}

}
