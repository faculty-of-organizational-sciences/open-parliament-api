package rs.otvoreniparlament.api.config;


public class Config {
	
	public DbConfig dbConfig;

	public QueryConfig query;

	public UriConfig uriGenerator;
	
	public ElasticConfig elasticConfig;
	
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
