package io.github.censodev.vrms.vrmsserver;

import io.github.censodev.vrms.vrmsserver.services.MailService;
import io.github.censodev.vrms.vrmsserver.utils.BeanUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest
class MailTests {
    @Test
    void testSendHtmlMail() throws ExecutionException, InterruptedException {
        var service = BeanUtil.getBean(MailService.class);
        var rs = service.sendHtml("censo.dev@gmail.com", "Subject", "emails/account-create", null).get();
        System.out.println(rs);
    }
}
