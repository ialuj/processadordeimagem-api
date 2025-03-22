package br.com.bixtecnologia.processadordeimagem.services.email;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bixtecnologia.processadordeimagem.domain.models.User;

@Service
public class EmailSender {

	@Autowired
    private EmailService emailService;

    public void sendRegistrationEmail(User user, String destEmail) throws Exception {
        String htmlTemplate = emailService.loadHtmlTemplate("userRegistrationNotificationTemplate");

        Map<String, String> variables = new HashMap<>();
        variables.put("name", user.getName());
        variables.put("username", user.getEmail());
        variables.put("password", user.getPassword());

        String populatedHtml = emailService.populateTemplateVariables(htmlTemplate, variables);

        emailService.sendEmail(destEmail, "Registo na Plataforma BIX Tecnologia - Processador de Imagens", populatedHtml);
    }
    
    public void sendProcessingRequestEmail(String name, String destEmail) throws Exception {
        String htmlTemplate = emailService.loadHtmlTemplate("imageProcessingRequestNotificationTemplate");

        Map<String, String> variables = new HashMap<>();
        variables.put("name", name);

        String populatedHtml = emailService.populateTemplateVariables(htmlTemplate, variables);

        emailService.sendEmail(destEmail, "Solicitação de Processamento de Imagem na Plataforma BIX Tecnologia - Processador de Imagens", populatedHtml);
    }
    
    public void sendProcessingResultEmail(String name, String bodyPart, String destEmail) throws Exception {
        String htmlTemplate = emailService.loadHtmlTemplate("imageProcessingRequestNotificationTemplate");

        Map<String, String> variables = new HashMap<>();
        variables.put("name", name);
        variables.put("link", bodyPart);

        String populatedHtml = emailService.populateTemplateVariables(htmlTemplate, variables);

        emailService.sendEmail(destEmail, "Resulatdo da Sua Solicitação de Processamento de Imagem na Plataforma BIX Tecnologia - Processador de Imagens", populatedHtml);
    }
}
