package sendemailssl;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailSSL {

	public SendMailSSL() {
		// TODO Auto-generated constructor stub
	}

	public void sendMail(String From, String To, String subject, String body) {

		Properties props = new Properties();

		props.put("mail.smtp.user", "jqguedesneto@gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.fallback", "false");

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				" javax.net.ssl.SSLSocketFactory");

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"jqguedesneto@gmail.com", "password");
					}
				});
		//
		// session.setDebug(true);

		try {

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(From));

			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(To));
			message.setSubject(subject);
			message.setText(body);

			Transport transport = session.getTransport("smtps");
			transport.connect("smtp.gmail.com", Integer.valueOf(465),
					"jqguedesneto@gmail.com", "password;");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

			// System.out.println("Done");

			// } catch (MessagingException e) {
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}

	}
}
