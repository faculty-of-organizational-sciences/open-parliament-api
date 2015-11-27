package rs.otvoreniparlament.api.index;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;


import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ElasticClient {

	private static ElasticClient INSTANCE = null;
    private static Object lock = new Object();
    
    private Client client;
    private Node node;
    
    private ElasticClient(){
    	createClient();
    }

    public static ElasticClient getInstance(){
        
        if(INSTANCE == null) { 
            synchronized (lock) {
                if(null == INSTANCE){
                    INSTANCE = new ElasticClient();
                }
            }
        }
        return INSTANCE;
    }

    public void createClient(){
//    	TODO: cluster, port, path - config
    	Settings settings = Settings.settingsBuilder().put("cluster.name", "openParliamentElastic").build();
        
        TransportClient transportClient = TransportClient.builder().settings(settings).build();

        try {
			transportClient = transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		} catch (UnknownHostException e) {
			// TODO u logger
			
			e.printStackTrace();
		}
        
        if(transportClient.connectedNodes().size() == 0)
        {
        	System.out.println("There are no active nodes available for the transport, it will be automatically added once nodes are live!");
        }
        client = transportClient;
    }
    
    public void prepareClient(){
        node   = nodeBuilder().node();
        client = node.client();
    }

    public void closeNode(){
        
        if(!node.isClosed())
            node.close();

    }
    
    public Client getClient(){
        return client;
    }
    
    
    public void printThis() {
        System.out.println(this);
    }
    
}

