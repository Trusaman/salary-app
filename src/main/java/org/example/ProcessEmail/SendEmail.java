package org.example.ProcessEmail;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.example.constant.Constant;
import org.example.model.Employee;
import org.example.pdf.ProcessPdfFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class SendEmail {
    public static void main(String[] args) throws Exception {
        List<Employee> employees = ProcessPdfFile.getEmp();

        String username = Constant.EMAIL_USER;
        String password = Constant.EMAIL_PASSWORD;

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        employees.forEach(employee -> {
            try {
                sendMail(session, employee);
            } catch (MessagingException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    private static void sendMail(Session session, Employee employee) throws MessagingException, IOException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("ketoan03@lunet.vn"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(employee.getEmail()));
        message.setSubject("Salary Payroll Slip - May 2023");

        String msg = "Dear <b>Mr./Mrs." + employee.getName() + "</b><br>Please take find the attachment for your detail salary in May 2023.<br>If you have any further questions, do not hesitate to contact us: 03587528 - Mrs. Thu";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        MimeBodyPart  attachmentPart = new MimeBodyPart();
        attachmentPart.attachFile(new File(employee.getPdfFilePath()));

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        multipart.addBodyPart(attachmentPart);

        message.setContent(multipart);

//        Transport.send(message);

        System.out.println("Send email successfully");
    }
}
