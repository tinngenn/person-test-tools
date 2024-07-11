package com.person.test.tools.web.congfig;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.ServerConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.jetty.ConfigurableJettyWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

/*
 * Jetty 同时支持http和https
 */
@Configuration
public class WebServerFactoryCustomizerConfig implements WebServerFactoryCustomizer<ConfigurableJettyWebServerFactory> {

    @Value("${server.port}")
    private int httpsPort = 8091;

    @Value("${server.http.port}")
    private int httpPort = 8092;


    @Override
    public void customize(ConfigurableJettyWebServerFactory factory) {


        factory.addServerCustomizers(
                server -> {
                    HttpConfiguration httpConfiguration = new HttpConfiguration();
                    httpConfiguration.setSecurePort(httpsPort);
                    httpConfiguration.setSecureScheme("https");

                    ServerConnector connector = new ServerConnector(server);
                    connector.addConnectionFactory(new HttpConnectionFactory(httpConfiguration));
                    connector.setPort(httpPort);
                    server.addConnector(connector);
                }
        );
    }
}
