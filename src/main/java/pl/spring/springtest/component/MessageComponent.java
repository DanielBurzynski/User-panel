package pl.spring.springtest.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.spring.springtest.component.mailer.Contact;
import pl.spring.springtest.component.mailer.Message;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
@Component
public class MessageComponent {


    @Autowired
    MailSender mailSender;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    public boolean sendSimpleMessage(Message message) {
        SimpleMailMessage messageMessage = new SimpleMailMessage();
        messageMessage.setFrom(message.getEmail());
        messageMessage.setSubject(message.getTitle());
        messageMessage.setText(message.getMessage());
        messageMessage.setTo("springkurs177@gmail.com"); // if you use Gmail do not forget to put your personal address

        try {
            mailSender.send(messageMessage);
            return true;
        } catch (MailException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean sendHtmlMessage(Message message) {

        Context context = new Context();
        context.setVariable("message", message);
        final String messageHtml = templateEngine.process("email/contact", context);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage);
        try {
            mailMessage.setTo("springkurs177@gmail.com"); // if you use Gmail do not forget to put your personal address
            mailMessage.setFrom(message.getEmail());
            mailMessage.setSubject(message.getTitle());
            mailMessage.setText(messageHtml, true);
            javaMailSender.send(mimeMessage);
            return true;
        } catch (MessagingException | MailException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
