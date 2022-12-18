package com.stuent.dpply.common.config.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.stuent.dpply.common.config.properties.S3KeyProperties;
import com.stuent.dpply.common.config.properties.S3Properties;
import com.stuent.dpply.common.config.properties.S3RegionProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class S3Config {

    private final S3KeyProperties s3KeyProperties;
    private final S3RegionProperties s3RegionProperties;
    private final S3Properties s3Properties;

    @Bean
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(s3KeyProperties.getAccessKey(), s3KeyProperties.getSecretKey());
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(s3Properties.getEndpoint(), s3RegionProperties.getRegion()))
                .build();
    }
}
