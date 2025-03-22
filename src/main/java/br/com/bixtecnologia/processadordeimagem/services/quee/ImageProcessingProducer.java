package br.com.bixtecnologia.processadordeimagem.services.quee;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bixtecnologia.processadordeimagem.config.RabbitMQConfig;
import br.com.bixtecnologia.processadordeimagem.dto.ImageProcessingRequestDTO;

/**
 * @author Jose Julai Ritsure
 */
@Service
public class ImageProcessingProducer {

	@Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(ImageProcessingRequestDTO request) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, request);
    }
}

