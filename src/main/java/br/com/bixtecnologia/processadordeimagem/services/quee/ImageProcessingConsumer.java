package br.com.bixtecnologia.processadordeimagem.services.quee;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bixtecnologia.processadordeimagem.config.RabbitMQConfig;
import br.com.bixtecnologia.processadordeimagem.dto.ImageProcessingRequestDTO;
import br.com.bixtecnologia.processadordeimagem.services.IImageProcessingService;

/**
 * @author Jose Julai Ritsure
 */
@Service
public class ImageProcessingConsumer {

	@Autowired
    private IImageProcessingService imageProcessingService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void processImage(ImageProcessingRequestDTO request) {
        imageProcessingService.processImage(request);
    }
}

