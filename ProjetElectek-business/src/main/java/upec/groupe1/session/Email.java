/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;

/**
 *
 * @author drajasin
 */
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@LocalBean
@Stateless
public class Email {
 
    @Resource(name = "java:/jboss/mail/gmail")
    private Session session;

    public void setSession() {
        Context cxt;
        try {
            cxt = new InitialContext();
            this.session = (Session) cxt.lookup("java:/jboss/mail/gmail");
            
        } catch (NamingException ex) {
            System.err.println("ERROORR" +ex.getExplanation());
        }
    }
    
    public void send(String addresses, String topic, String textMessage) throws AddressException, MessagingException {
 
 
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
            message.setSubject(topic);
            message.setText(textMessage);
 
            Transport.send(message);
 
    }
}

