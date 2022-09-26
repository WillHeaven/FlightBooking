package FlightBooking.RabbitReceiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import FlightBooking.Model.CheckInStatus;
import FlightBooking.rabbitmq.MessagingConfiq;



@Component
public class Rabbitresponse {
	

	@RabbitListener(queues=MessagingConfiq.QUEUE)
	public void consumeMessageFromQueue(CheckInStatus checkinstatus)
	{
	System.out.println("Message received from queue :"+checkinstatus);	
	}

}
