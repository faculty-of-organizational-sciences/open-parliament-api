package rs.otvoreniparlament.api.config;

public class ElasticConfig {

	public String clusterName;
	public int port;
	public String ipAddress;
	boolean usingElastic;

	public boolean isUsingElastic() {
		return usingElastic;
	}

	public void setUsingElastic(boolean usingElastic) {
		this.usingElastic = usingElastic;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
