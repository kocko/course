package bg.learnit.mail.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:META-INF/spring/spring-config.xml"})
public class EmailServiceTest {

	@Mock
	private MailSender mailSender;
	
	@Mock
	private SimpleMailMessageFactory simpleMailMessageFactory;

	private EmailService systemUnderTest;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		systemUnderTest = new EmailService(mailSender);
		systemUnderTest.setSimpleMailMessageFactory(simpleMailMessageFactory);
	}
	
	@Test
	public void shouldSendMailMessageWithMinimumSettings() throws Exception {
		String to = "to@example.com";
		String subject = "Subject";
		String body = "Bogy";
		SimpleMailMessage simpleMailMessage = mock(SimpleMailMessage.class);
		
		when(simpleMailMessageFactory.procudePlainSimpleMailMessage()).thenReturn(simpleMailMessage);

		systemUnderTest.sendMail(to, subject, body);
		
		verify(simpleMailMessage).setTo(to);
		verify(simpleMailMessage).setSubject(subject);
		verify(simpleMailMessage).setText(body);
		
		verify(mailSender).send(simpleMailMessage);
	}
}
