package io.github.censodev.vrms.vrmsserver.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class AWSConfig {
    @Value("aws.sns.access-key")
    private String snsAccessKey;

    @Value("aws.sns.secret-key")
    private String snsSecretKey;

    @Bean
    public SnsClient getSNSClient() {
        var credentials = AwsBasicCredentials
                .create(snsAccessKey, snsSecretKey);
        return SnsClient.builder()
                .region(Region.AP_SOUTHEAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }
}
