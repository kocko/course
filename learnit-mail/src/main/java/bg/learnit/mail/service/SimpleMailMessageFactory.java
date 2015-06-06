package bg.learnit.mail.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component("mailMessageFactory")
class SimpleMailMessageFactory {

	SimpleMailMessage procudePlainSimpleMailMessage() {
		return new SimpleMailMessage();
	}

}
