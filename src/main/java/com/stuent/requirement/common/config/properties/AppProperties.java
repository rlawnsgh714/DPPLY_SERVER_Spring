package com.stuent.requirement.common.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("app")
public class AppProperties {
    private String DAUTH_SERVER;
    private String OPEN_API_SERVER;
    private String CLIENT_ID;
    private String CLIENT_SECRET;
}