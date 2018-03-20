
package upec.groupe1.session;
import java.io.Serializable;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * <p>
 * {@link Email} contains all the business logic for the application, and also serves as the controller for the JSF view.
 * </p>
 * <p>
 * It contains address, subject, and content for the <code>email</code> to be sent.
 * </p>
 * <p>
 * The {@link #send()} method provides the business logic to send the email
 * </p>
 *
 * @author Joel Tosi
 *
 */

@Named
@Stateless 
public class Email implements Serializable {

    private static final long serialVersionUID = 1544680932114626710L;

    /**
     * Resource for sending the email. The mail subsystem is defined in either standalone.xml or domain.xml in your respective
     * configuration directory.
     */
    @Resource(mappedName = "java:/jboss/mail/gmail")
    private Session mySession;

    private String to;

    private String from;

    private String subject;

    private String body;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    
    public void send() throws MessagingException {
        Message message = new MimeMessage(mySession);
        Address toAddress = new InternetAddress(to);
        message.addRecipient(Message.RecipientType.TO, toAddress);
        message.setSubject(subject);
        message.setContent(body, "text/plain");
        Transport.send(message);
    }
}
