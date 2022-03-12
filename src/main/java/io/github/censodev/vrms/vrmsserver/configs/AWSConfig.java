package io.github.censodev.vrms.vrmsserver.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class AWSConfig {
    @Value("${aws.sns.profile}")
    private String profileSNS;

    @Value("${aws.ses.profile}")
    private String profileSES;

    @Bean
    public SnsClient getSNSClient() {
        return SnsClient.builder()
                .region(Region.AP_SOUTHEAST_1)
                .credentialsProvider(ProfileCredentialsProvider.create(profileSNS))
                .build();
    }

    @Bean
    public SesClient getSESClient() {
        return SesClient.builder()
                .region(Region.AP_SOUTHEAST_1)
                .credentialsProvider(ProfileCredentialsProvider.create(profileSES))
                .build();
    }
}
