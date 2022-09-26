package CheckIn.Controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import CheckIn.Exception.BusinessException;
import CheckIn.Exception.CustomException;
import CheckIn.Model.Booking;
import CheckIn.Model.CheckIn;
import CheckIn.Model.CheckInStatus;
import CheckIn.Model.Flight;
import CheckIn.Model.FlightData;
import CheckIn.Model.Passenger;
import CheckIn.Repository.CheckInRepository;
import CheckIn.Service.CheckInService;
import CheckIn.rabbitmq.MessagingConfiq;

@RestController
@RequestMapping("checkin")
public class CheckinController {

	@Autowired
	private RestTemplate resttemplate;

	@Autowired
	private CheckInService service;
	
	@Autowired
	private RabbitTemplate template;  
	
	@Autowired
	private CheckInRepository repo;
	
	CheckIn c=new CheckIn();
	Random r=new Random();
	
	@PutMapping("/booking/{booking_id}")
	/* @HystrixCommand(fallbackMethod = "UpdateBookingFallback") */
	public String updateBooking(Booking booking, @PathVariable("booking_id") long pnr) throws InterruptedException {
		booking.setChecked_in(true); 
		resttemplate.put("http://localhost:8081/booking/booking/"+ pnr , booking);
		return"Checked In Successfully:"+pnr ; 
		

	}
	
		@GetMapping("/{booking_id}")
	public CheckIn getInfo(@PathVariable("booking_id") long pnr)
	{
		Booking booking=resttemplate.getForObject("http://localhost:8081/booking/"+pnr,Booking.class);
		
		
		c.setBookingId(booking.getBooking_id());
		
		
		List<Flight> f=booking.getFlight();
		c.setFlightid(f);
		
		
		List<Passenger> p=booking.getPassenger();
		c.setPassenger(p);
		
		c.setConfirmation("Yes");
		
		long a=booking.getBooking_id()+5;
		c.setCheckno(a);
		
		return c;
		
	}
	
		
		 @RequestMapping("/confirm")
		public String confirm()
		{
			 boolean present=service.findById(c.getBookingId());
			 if(present==false) {
			service.confirmation(c);
			CheckInStatus checkinstatus=new CheckInStatus("PROCESS","Check-In Confirmation sucessfully ");            
			template.convertAndSend(MessagingConfiq.EXCHANGE,MessagingConfiq.ROUTING_KEY,checkinstatus);
			return "Check-In has confirmed"+c.getCheckno();
			 }

			return "Check-In already";
	}
		 

}
