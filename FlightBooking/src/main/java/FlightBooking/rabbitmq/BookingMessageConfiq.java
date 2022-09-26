package FlightBooking.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingMessageConfiq {

	public static final String QUEUE="Booking_Queue";
	public static final String EXCHANGE="Booking_Exchange";
	public static final String ROUNTING_KEY="Booking_RoutingKey";
	
	@Bean
	public Queue q()
	{
		return new Queue(QUEUE);
	}
	
	@Bean
	public TopicExchange e()
	{
		return new TopicExchange(EXCHANGE);
	}
	
	
	@Bean
	public Binding bind(Queue q, TopicExchange e)
	{
		return BindingBuilder.bind(q).to(e).with(ROUNTING_KEY);
	}
	
	@Bean
	public MessageConverter mc()
	{
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate temp(ConnectionFactory connectionFactory)
	{
		RabbitTemplate temp=new RabbitTemplate(connectionFactory);
		temp.setMessageConverter(mc());
		return temp;
	}
	
}
