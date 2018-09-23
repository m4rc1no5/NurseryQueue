package pl.marceen.nurseryqueueapi.email.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.email.entity.EmailData;
import pl.marceen.nurseryqueueapi.email.entity.EmailException;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Marcin Zaremba
 */
public class EmailSender {
    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

    @Resource(name = "java:jboss/mail/Default")
    private Session session;

    public void send(EmailData data) {
        logger.info("Sending email with data: {}", data);

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addFrom(InternetAddress.parse(data.getEmailsFrom()));
            mimeMessage.addRecipients(Message.RecipientType.TO, InternetAddress.parse(data.getEmailsTo()));
            mimeMessage.setSubject(data.getSubject());
            mimeMessage.setText(data.getText());

            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            throw EmailException.sendingProblem("Problem with sending email - details: " + e.getMessage(), logger);
        }
    }
}
