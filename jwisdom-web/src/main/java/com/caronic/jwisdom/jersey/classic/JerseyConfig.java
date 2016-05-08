package com.caronic.jwisdom.jersey.classic;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * Created by caronic on 2016/5/8.
 */
@Profile("web")
@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(UserController.class);
    }
}
