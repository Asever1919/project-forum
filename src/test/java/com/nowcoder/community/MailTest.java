package com.nowcoder.community;


import com.nowcoder.community.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;

import javax.naming.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTest {
    @Autowired
    private MailClient mailClient;
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    // 能运行，但是不能真的接收到邮件
    public void testTextMail() {
        mailClient.sendMail("734251745@qq.com", "Text", "zhanshuai");
    }

    @Test
    // 能运行，但是不能真的接收到邮件
    public void TestMialHtml() {
        org.thymeleaf.context.Context context = new org.thymeleaf.context.Context();
        context.setVariable("username", "sunday");

        String content = templateEngine.process("/Mail/demo", context);
        System.out.println(content);

        mailClient.sendMail("734251745@qq.com", "HTML", content);
    }
}
