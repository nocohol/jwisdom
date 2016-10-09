/**
 * 
 */
package com.caronic.jwisdom.service;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.caronic.jwisdom.service.config.AppConfig;

/**
 * @author caronic created at 2016年10月9日
 */
public class Application {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException("Please provide port number for web server");
        }
        
        final int port = Integer.valueOf(args[0]);
        final Server server = new Server(port);
        
        System.setProperty(AppConfig.SERVER_PORT, args[0]);
        System.setProperty(AppConfig.SERVER_HOST, "localhost");
        
        final ServletHolder servletHolder = new ServletHolder(new CXFServlet());
        final ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(servletHolder, "/" + AppConfig.CONTEXT_PATH + "/*");
        context.addEventListener(new ContextLoaderListener());
        
        context.setInitParameter("contextClass", AnnotationConfigWebApplicationContext.class.getName());
        context.setInitParameter("contextConfigLocation", AppConfig.class.getName());
        
        server.setHandler(context);
        server.start();
        server.join();
        
    }
}
