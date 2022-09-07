package com.stuent.dpply.common.config.restemplate;

import com.stuent.dpply.common.config.properties.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {
    private final RestTemplateBuilder restTemplateBuilder;
    private final AppProperties properties;

    private RestTemplate restTemplate(final String endpoint) {
        return restTemplateBuilder.rootUri(endpoint)
                .additionalInterceptors(new RestTemplateClientHttpRequestInterceptor())
                .errorHandler(new RestTemplateErrorHandler())
                .setConnectTimeout(Duration.ofMinutes(3))
                .build();
    }

    @Bean
    public RestTemplate dauthTemplate() {
        return restTemplate(properties.getDAUTH_SERVER());
    }

    @Bean
    public RestTemplate dodamOpenApiTemplate() {
        return restTemplate(properties.getOPEN_API_SERVER());
    }
}
