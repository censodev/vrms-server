package io.github.censodev.vrms.vrmsserver.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class MailService {
    @Value("${spring.mail.from}")
    private String mailFrom;

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    public MailService(JavaMailSender emailSender, TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public CompletableFuture<Void> sendText(String to, String subject, String text) {
        var message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try {
            emailSender.send(message);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    @Async
    public CompletableFuture<Boolean> sendHtml(String to, String subject, String template, Map<String, Object> props) {
        try {
            props = Optional.ofNullable(props).orElse(new HashMap<>());
            var message = emailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            var context = new Context();
            context.setVariables(props);
            var html = templateEngine.process(template, context);
            helper.setTo(to);
            helper.setText(html, true);
            helper.setSubject(subject);
            helper.setFrom(mailFrom);
            emailSender.send(message);
            return CompletableFuture.completedFuture(true);
        } catch (MessagingException e) {
            log.error(e.getMessage());
            return CompletableFuture.completedFuture(false);
        }
    }
}
