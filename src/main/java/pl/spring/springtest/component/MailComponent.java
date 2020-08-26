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

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailComponent {



        @Autowired
        MailSender mailSender;

        @Autowired
        JavaMailSender javaMailSender;

        @Autowired
        TemplateEngine templateEngine;

        public boolean sendSimpleMail(Contact contact) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(contact.getEmail());
            mailMessage.setSubject(contact.getTitle());
            mailMessage.setText(contact.getContent());
            mailMessage.setTo("springkurs177@gmail.com"); // if you use Gmail do not forget to put your personal address

            try {
                mailSender.send(mailMessage);
                return true;
            } catch (MailException e) {
                System.err.println(e.getMessage());
                return false;
            }
        }

        public boolean sendHtmlMail(Contact contact) {

            Context context = new Context();
            context.setVariable("contact", contact);
            final String messageHtml = templateEngine.process("email/contact", context);

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage);
            try {
                mailMessage.setTo("springkurs177@gmail.com"); // if you use Gmail do not forget to put your personal address
                mailMessage.setFrom(contact.getEmail());
                mailMessage.setSubject(contact.getTitle());
                mailMessage.setText(messageHtml, true);
                javaMailSender.send(mimeMessage);
                return true;
            } catch (MessagingException | MailException e) {
                System.err.println(e.getMessage());
                return false;
            }
        }

}
