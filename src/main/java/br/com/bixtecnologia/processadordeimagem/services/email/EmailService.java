package br.com.bixtecnologia.processadordeimagem.services.email;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import jakarta.inject.Inject;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Inject
    private Environment environment;

    public void sendEmail(String to, String subject, String text) throws MessagingException {
        String username = environment.getProperty("spring.email.mailer.gmail.username", String.class);
        String password = environment.getProperty("spring.email.mailer.gmail.password", String.class);
        String host = environment.getProperty("spring.email.mailer.gmail.host", String.class);
        int port = environment.getProperty("spring.email.mailer.gmail.port", Integer.class);
        boolean tls = environment.getProperty("spring.email.mailer.gmail.tls", Boolean.class);
        boolean auth = environment.getProperty("spring.email.mailer.gmail.auth", Boolean.class);

        Properties props = new Properties();
        props.put("mail.smtp.auth", String.valueOf(auth));
        props.put("mail.smtp.starttls.enable", String.valueOf(tls));
        props.put("mail.smtp.starttls.enable", String.valueOf(tls));
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", String.valueOf(port));
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Create session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setContent(text, "text/html");

        Transport.send(message);
    }

    public String loadHtmlTemplate(String templateName) throws Exception {
        try (InputStream inputStream = getClass().getResourceAsStream("/templates/" + templateName + ".html")) {
            if (inputStream == null) {
                throw new IOException("Template file not found: " + templateName);
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }

    public String populateTemplateVariables(String htmlTemplate, Map<String, String> variables) {
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            htmlTemplate = htmlTemplate.replace("${" + entry.getKey() + "}", entry.getValue());
        }
        return htmlTemplate;
    }
    public String populateTemplateVariablesList(String htmlTemplate, Map<String, List<String>> variables) {
        for (Map.Entry<String, List<String>> entry : variables.entrySet()) {

            for (String value : entry.getValue()){
                htmlTemplate = htmlTemplate.replace( ("${" + entry.getKey() + "}"), value);
            }

        }
        return htmlTemplate;
    }
}
