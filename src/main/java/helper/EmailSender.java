package helper;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class EmailSender {
	 public static void sendEmail(String to, String subject, String content) throws MessagingException {
	        // Cấu hình SMTP Server của Gmail
	        String host = "smtp.gmail.com";
	        String from = "pnquangcn1704@gmail.com"; // Thay bằng email của bạn
	        String password = "izzh knnb kjxy rjdv"; // Thay bằng mật khẩu ứng dụng

	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.port", "587");

	        // Tạo session và xác thực tài khoản
	        Session session = Session.getInstance(props, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(from, password);
	            }
	        });

	        // Tạo email
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	        message.setSubject(subject);
	        message.setText(content);

	        // Gửi email
	        Transport.send(message);
	    }

}
