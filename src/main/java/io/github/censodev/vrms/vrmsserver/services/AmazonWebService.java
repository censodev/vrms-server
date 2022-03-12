package io.github.censodev.vrms.vrmsserver.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.SnsException;

@Service
@Slf4j
public class AmazonWebService {
    @Value("${aws.ses.sender}")
    private String mailSender;

    private final SnsClient snsClient;
    private final SesClient sesClient;

    public AmazonWebService(SnsClient snsClient, SesClient sesClient) {
        this.snsClient = snsClient;
        this.sesClient = sesClient;
    }

    @Async
    public void sendSMS(String phone, String message) {
        try {
            var request = PublishRequest.builder()
                    .message(message)
                    .phoneNumber(phone)
                    .build();
            var result = snsClient.publish(request);
            log.info(result.messageId() + " Message sent. Status was " + result.sdkHttpResponse().statusCode());
        } catch (SnsException e) {
            log.error(e.awsErrorDetails().errorMessage());
        }
    }

    @Async
    public void sendMail(String recipient, String subject, String bodyTextOrHtml) {
        var destination = Destination.builder()
                .toAddresses(recipient)
                .build();
        var content = Content.builder()
                .data(bodyTextOrHtml)
                .build();
        var sub = Content.builder()
                .data(subject)
                .build();
        var body = Body.builder()
                .html(content)
                .build();
        var msg = Message.builder()
                .subject(sub)
                .body(body)
                .build();
        var emailRequest = SendEmailRequest.builder()
                .destination(destination)
                .message(msg)
                .source(mailSender)
                .build();
        try {
            var result = sesClient.sendEmail(emailRequest);
            log.info(result.messageId() + " Message sent. Status was " + result.sdkHttpResponse().statusCode());
        } catch (SesException e) {
            log.error(e.awsErrorDetails().errorMessage());
        }
    }
}
