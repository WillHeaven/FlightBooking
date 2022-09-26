package FlightSearch.rabbitlistener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import FlightSearch.rabbitmq.BookingMessageConfiq;

@Component
public class MessageListener {

	
	@RabbitListener(queues= BookingMessageConfiq.QUEUE )
	public void listener(String m)
	{
		System.out.println(m);
	}
	
}
