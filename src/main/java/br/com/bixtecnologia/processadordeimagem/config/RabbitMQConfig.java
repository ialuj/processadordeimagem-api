package br.com.bixtecnologia.processadordeimagem.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	public static final String QUEUE_NAME = "image-processing-queue";
	public static final String EXCHANGE_NAME = "image-processing-exchange";
	public static final String ROUTING_KEY = "image.processing";

	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME, true);
	}

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(EXCHANGE_NAME);
	}

	@Bean
	public Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter(); // Configure message converter for JSON
	}

	/*@Bean
	public MessageListener messageListener() {
		return new ImageProcessingListener(); // Use your listener to process messages
	}

	@Bean
	public MessageListenerContainer messageListenerContainer() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setMessageConverter(messageConverter());
		container.setQueues(queue());
		container.setMessageListener(new MessageListenerAdapter(messageListener()));
		return container;
	}*/
}
