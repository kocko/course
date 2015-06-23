package bg.learnit.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("emailService")
@Scope("singleton")
public class EmailService {

	private MailSender mailSender;

	private SimpleMailMessageFactory simpleMailMessageFactory;

	@Autowired
	public EmailService(@Qualifier("mailSender") MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Autowired
	public void setSimpleMailMessageFactory(SimpleMailMessageFactory simpleMailMessageFactory) {
		this.simpleMailMessageFactory = simpleMailMessageFactory;
	}

	public void sendMail(String to, String subject, String body) {
		SimpleMailMessage message = createMailMessage(to, subject, body);
		mailSender.send(message);
	}

	private SimpleMailMessage createMailMessage(String to, String subject, String body) {
		SimpleMailMessage message = simpleMailMessageFactory.procudePlainSimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		return message;
	}

}
