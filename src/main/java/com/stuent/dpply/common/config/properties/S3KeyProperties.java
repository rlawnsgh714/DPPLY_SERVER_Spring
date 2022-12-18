package com.stuent.dpply.common.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("cloud.aws.credentials")
public class S3KeyProperties {

    private String accessKey;
    private String secretKey;
}
