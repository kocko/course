package bg.learnit.course.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@ApplicationScoped
@ManagedBean(name = "mailer")
public final class Mailer {
	private final Properties properties = new Properties();
	
	@PostConstruct
	public void init() {
		InputStream input;
		try {
			input = getClass().getResourceAsStream("/mail.properties");
			properties.load(input);
		} catch (FileNotFoundException e) {
			//TODO handle the exception
			e.printStackTrace();
		} catch (IOException e) {
			//TODO handle the exception
			e.printStackTrace();
		}
	}

	public void sendMail(String recipient, String subject, String body) {
		
		final String username = properties.getProperty("from.username");
		final String password = properties.getProperty("from.password");

		Session session = Session.getDefaultInstance(properties, 
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			}
		);

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setSubject(subject);
			message.setText(body);
			
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
