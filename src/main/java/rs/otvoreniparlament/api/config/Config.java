package rs.otvoreniparlament.api.config;

public class Config {

	public DbConfig dbConfig;

	public DbConfig getDbConfig() {
		return dbConfig;
		// System.out.println("Izmena");
	}

	public void setDbConfig(DbConfig dbConfig) {
		this.dbConfig = dbConfig;
	}

}
