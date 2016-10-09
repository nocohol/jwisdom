package com.caronic.jwisdom.service.rs;

import com.caronic.jwisdom.service.config.AppConfig;
import org.apache.curator.x.discovery.UriSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Created by caronic on 2016/10/8.
 */
@ApplicationPath(JaxRsApiApplication.APPLICATION_PATH)
public class JaxRsApiApplication extends Application {

    public final static String APPLICATION_PATH = "api";

    @Autowired
    private Environment environment;

    public UriSpec getUriSpec(final String servicePath) {
        return new UriSpec(
                String.format("{scheme}://%s:{port}/%s/%s%s",
                        environment.getProperty(AppConfig.SERVER_HOST),
                        AppConfig.CONTEXT_PATH,
                        APPLICATION_PATH,
                        servicePath)
        );
    }

}
