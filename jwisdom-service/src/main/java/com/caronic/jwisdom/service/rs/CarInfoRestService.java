package com.caronic.jwisdom.service.rs;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.caronic.jwisdom.data.domain.CarInfo;
import com.caronic.jwisdom.service.config.AppConfig;
import com.caronic.jwisdom.service.config.RestServiceDetails;

/**
 * @author caronic created at 2016年10月9日
 */
@Path(CarInfoRestService.CAR_INFO_PATH)
public class CarInfoRestService {
    
    public static final String CAR_INFO_PATH = "/carinfo";
    
    @Autowired
    private JaxRsApiApplication application;
    @Autowired
    private ServiceDiscovery<RestServiceDetails> discovery;
    @Autowired
    private Environment environment;
    
    @PostConstruct
    public void init() throws Exception {
        final ServiceInstance<RestServiceDetails> instance = ServiceInstance.<RestServiceDetails>builder()
                .name("carinfo")
                .payload(new RestServiceDetails("1.0"))
                .port(environment.getProperty(AppConfig.SERVER_PORT, Integer.class))
                .uriSpec(application.getUriSpec(CAR_INFO_PATH))
                .build();
        discovery.registerService(instance);
    }
    
    @Produces({MediaType.APPLICATION_JSON})
    @GET
    public Collection<CarInfo> getCarInfo(@QueryParam("page") @DefaultValue("1")  final int page) {
        return Arrays.asList(
                new CarInfo("1.jpg", 1.0f),
                new CarInfo("2.jpg", 2.0f)
                );
    }
	
}
