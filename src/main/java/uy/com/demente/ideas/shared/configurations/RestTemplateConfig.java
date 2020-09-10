package uy.com.demente.ideas.shared.configurations;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	@Value("${restclient.bqm.proxy-host}")
	private String proxyHost;
	
	@Value("${restclient.bqm.proxy-port}")
	private String proxyPort;

    @Bean
    public RestTemplate restTemplate() {
    	SimpleClientHttpRequestFactory factory = addProxy();
        return new RestTemplate(factory);
    }
    
    

	private SimpleClientHttpRequestFactory addProxy() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		InetSocketAddress address = new InetSocketAddress("34.193.195.154", 3128);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
		factory.setProxy(proxy);
		return factory;

	}
}
