package rs.otvoreniparlament.api.config;


public class Config {
	
	public boolean indexDataOnStartup;

	public DbConfig dbConfig;

	public QueryConfig query;

	public UriConfig uriGenerator;
	
	public ElasticConfig elasticConfig;
	
	public boolean isIndexDataOnStartup() {
		return indexDataOnStartup;
	}

	public void setIndexDataOnStartup(boolean indexDataOnStartup) {
		this.indexDataOnStartup = indexDataOnStartup;
	}

	public ElasticConfig getElasticConfig() {
		return elasticConfig;
	}

	public void setElasticConfig(ElasticConfig elasticConfig) {
		this.elasticConfig = elasticConfig;
	}

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

	public UriConfig getUriGenerator() {
		return uriGenerator;
	}

	public void setUriGenerator(UriConfig uriGenerator) {
		this.uriGenerator = uriGenerator;
	}

}
