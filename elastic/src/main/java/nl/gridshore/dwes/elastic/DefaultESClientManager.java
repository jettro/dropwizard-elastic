package nl.gridshore.dwes.elastic;

import io.dropwizard.lifecycle.Managed;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * <p>Elasticsearch client factory bean that returns a client instance. You are responsible for closing the client when
 * you are done with it. Client objects are expensive to use and should be reused within your application.</p>
 * <p>The host string most be of format "host1:port1,host2:port2"</p>
 * <p>The cluster name must be the name of the cluster than runs on the provided host(s)</p>
 */
public class DefaultESClientManager implements Managed, ESClientManager {
    private static final Logger logger = LoggerFactory.getLogger(DefaultESClientManager.class);

    private final List<String> hosts;
    private final String clusterName;
    private final String usernamePassword;

    private Client client;

    public DefaultESClientManager(String host, String clusterName, String usernamePassword) {
        this.hosts = Arrays.asList(host.split(","));
        this.clusterName = clusterName;
        this.usernamePassword = usernamePassword;
    }

    @Override
    public Client obtainClient() {
        return this.client;
    }

    @Override
    public ClusterAdminClient obtainClusterClient() {
        return this.client.admin().cluster();
    }

    @Override
    public IndicesAdminClient obtainIndicesClient() {
        return this.client.admin().indices();
    }

    @Override
    public void start() throws Exception {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", clusterName)
                .put("shield.user", usernamePassword)
                .build();
        logger.debug("Settings used for connection to elasticsearch : {}", settings.toDelimitedString('#'));

        List<TransportAddress> addresses = hosts.stream()
                .map(ParseServerStringFunction::parse)
                .collect(toList());
        logger.debug("Hosts used for transport client : {}", addresses);

        this.client = new TransportClient(settings)
                .addTransportAddresses(addresses.toArray(new TransportAddress[addresses.size()]));

    }

    @Override
    public void stop() throws Exception {
        this.client.close();
    }
}
