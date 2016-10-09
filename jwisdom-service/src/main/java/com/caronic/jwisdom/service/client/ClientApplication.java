/**
 * 
 */
package com.caronic.jwisdom.service.client;

import java.util.Collection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceInstance;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;

import com.caronic.jwisdom.service.config.RestServiceDetails;

/**
 * @author caronic created at 2016年10月9日
 */
public class ClientApplication {

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        try( final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( ClientConfig.class)) {
            @SuppressWarnings("unchecked")
            final ServiceDiscovery< RestServiceDetails > discovery = context.getBean( ServiceDiscovery.class );
            final Client client = ClientBuilder.newBuilder().newClient();
            
            final Collection< ServiceInstance< RestServiceDetails > > services = discovery.queryForInstances( "carinfo" );
            for( final ServiceInstance< RestServiceDetails > service: services ) {
                final String uri = service.buildUriSpec();
                final Response response = client.target(uri)
                        .request(MediaType.APPLICATION_JSON_VALUE)
                        .get();
                System.out.println(uri + ": " + response.readEntity(String.class));
                System.out.println("API version: " + service.getPayload().getVersion());
                
                response.close();
            }
        }
    }

}
