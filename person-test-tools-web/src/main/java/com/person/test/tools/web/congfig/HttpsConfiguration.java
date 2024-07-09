package com.person.test.tools.web.congfig;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.http.HttpScheme;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@Slf4j
@Configuration
public class HttpsConfiguration {

    /**
     * HTTP端口
     */
    @Value("${http.port}")
    private int httpPort;

    /**
     * HTTPS端口
     */
    @Value("${https.port}")
    private int httpsPort;

    /**
     * HTTPS ssl密码
     */
    @Value("${https.ssl.key-store-password}")
    private String httpsPassword;

    /**
     * HTTPS ssl加密文件
     */
    @Value("${https.ssl.key-store-file}")
    private String httpsFile;

    /**
     * HTTPS ssl加密类型
     */
    @Value("${https.ssl.key-store-type}")
    private String httpsType;

    @Bean
    public WebServerFactoryCustomizer<WebServerFactory> servletContainerCustomizer() {
        return new WebServerFactoryCustomizer<WebServerFactory>() {

            @Override
            public void customize(WebServerFactory container) {
                //自定义jetty容器
                if (container instanceof JettyServletWebServerFactory) {
                    customizeJetty((JettyServletWebServerFactory) container);
                }
            }

            /**
             * 修改jetty容器配置，同时开放http和https两个端口
             *
             * @author wangpeipei
             * @createDate 2018-09-19
             * @param container
             */
            private void customizeJetty(JettyServletWebServerFactory container) {

                container.addServerCustomizers((Server server) -> {
                    //默认jetty连接器是http
                    ServerConnector connector = new ServerConnector(server);
                    //设置jetty连接器端口号
                    connector.setPort(httpPort);
                    log.info("HTTP配置端口号：【{}】", httpPort);

                    // HTTPS配置
                    //获取加密文件
                    URL httpsFileUrl = HttpsConfiguration.class.getClassLoader().getResource(httpsFile);

                    //通过ssl工厂分别设置加密文件、密码和加密类型
                    SslContextFactory sslContextFactory = new SslContextFactory();
                    sslContextFactory.setKeyStorePath(httpsFileUrl.toString());
                    sslContextFactory.setKeyStorePassword(httpsPassword);
                    sslContextFactory.setKeyStoreType(httpsType);

                    //设置http配置为https
                    HttpConfiguration config = new HttpConfiguration();
                    config.setSecureScheme(HttpScheme.HTTPS.asString());
                    config.addCustomizer(new SecureRequestCustomizer());

                    //设置jetty连接器为https
                    ServerConnector sslConnector = new ServerConnector(
                            server,
                            new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()),
                            new HttpConnectionFactory(config));
                    sslConnector.setPort(httpsPort);

                    //将两个不同端口的连接器设置到jetty server中
                    server.setConnectors(new Connector[]{connector, sslConnector});

                    log.info("HTTPS配置端口号【{}】", httpsPort);
                    log.info("证书：【{}】", httpsFile);
                });
            }
        };
    }
}
