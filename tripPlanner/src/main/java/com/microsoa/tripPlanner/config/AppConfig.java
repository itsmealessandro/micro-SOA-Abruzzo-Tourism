package com.microsoa.tripPlanner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        // You can add more configurations here, e.g., timeouts, message converters
        // Example with timeouts (requires org.apache.httpcomponents.httpclient5)
        /*
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(new PoolingHttpClientConnectionManager())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        requestFactory.setConnectTimeout(5000); // 5 seconds connection timeout
        requestFactory.setReadTimeout(10000); // 10 seconds read timeout
        return new RestTemplate(requestFactory);
        */
        return new RestTemplate();
    }
}