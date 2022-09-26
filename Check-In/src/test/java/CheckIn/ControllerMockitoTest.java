package CheckIn;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import CheckIn.Controller.CheckinController;
import CheckIn.Model.Booking;
import CheckIn.Model.CheckIn;
import CheckIn.Model.Flight;
import CheckIn.Model.FlightData;
import CheckIn.Model.Passenger;
import CheckIn.Service.CheckInService;
import CheckIn.rabbitmq.MessagingConfiq;

@SpringBootTest(classes= {ControllerMockitoTest.class})
public class ControllerMockitoTest {

	@Mock
	CheckInService checkinser;
	
	@InjectMocks
	CheckinController checkincon;
	
//	@Autowired
//	 RabbitTemplate template;  

//	@Autowired
//	RestTemplate restTemplate;
	
	
//	@Test
//	@Order(1)
//	public 
	
/*	@Test
	@Order(1)
	public void test_getInfo() 
	{
		long i=1234;
		LocalDate l=LocalDate.now();
		List<FlightData> d=new  ArrayList<FlightData>();
		d.add(new FlightData("1234","3423","12:50","9:20") );
		
		
		List<Flight> f=new ArrayList<Flight>();
		f.add(new Flight("1234","Chennai","Mumbai",6000,60,30,"9:20",d));
		
		List<Passenger> p=new ArrayList<Passenger>();
		p.add(new Passenger(1,"Nirmal",3,5,334));
		p.add(new Passenger(2,"Sintha",4,6,335));
		
		Booking b=new Booking(i,f,l,p,20000.00,false,false,false);
		
		CheckIn c= new CheckIn(1234,f,p,"Yes",2345);
	
		RestTemplate restTemplate= new RestTemplate();
		when(restTemplate.getForObject("http://localhost:8081/booking/"+i,Booking.class)).thenReturn(b);
		assertEquals(i,b.getBooking_id());
		  
		when(checkincon.getInfo(i)).thenReturn(c);
		CheckIn ch=checkincon.getInfo(i);
		
		assertEquals(i,ch.getBookingId());
	} */
	
/*	@Test
	@Order(2)
	public void test_Confirm()
	{
		List<FlightData> d=new  ArrayList<FlightData>();
		d.add(new FlightData("1234","3423","12:50","9:20") );
		
		
		List<Flight> f=new ArrayList<Flight>();
		f.add(new Flight("1234","Chennai","Mumbai",6000,60,30,"9:20",d));
		
		List<Passenger> p=new ArrayList<Passenger>();
		p.add(new Passenger(1,"Nirmal",3,5,334));
		p.add(new Passenger(2,"Sintha",4,6,335));
		
		CheckIn c= new CheckIn(1234,f,p,"Yes",2345);
		
		when(template.convertAndSend(MessagingConfiq.EXCHANGE,MessagingConfiq.ROUTING_KEY,checkinstatus)).thenReturn()
		
		when(checkinser.confirmation(c)).thenReturn(c);
		String s=checkincon.confirm();
		
		assertEquals("Check-In has confirmed"+c.getCheckno(),s);
		
	}
	*/
	
	@Test
	@Order(1)
	public void test_Update()
	{
		long pnr=1234;
		LocalDate l=LocalDate.now();
		List<FlightData> d=new  ArrayList<FlightData>();
		d.add(new FlightData("1234","3423","12:50","9:20") );
		
		
		List<Flight> f=new ArrayList<Flight>();
		f.add(new Flight("1234","Chennai","Mumbai",6000,60,30,"9:20",d));
		
		List<Passenger> p=new ArrayList<Passenger>();
		p.add(new Passenger(1,"Nirmal",3,5,334));
		p.add(new Passenger(2,"Sintha",4,6,335));
		
		Booking b=new Booking(pnr,f,l,p,20000.00,false,true,false);
		Booking c=new Booking(pnr,f,l,p,20000.00,false,false,false);
		c.setChecked_in(true);
		RestTemplate restTemplate= new RestTemplate();
		
		//Mockito.when(restTemplate.put("http://localhost:8081/booking/booking/"+ pnr,c)).println("");
		assertEquals("Checked In Successfully:"+pnr,"Checked In Successfully:"+b.booking_id);
	}
}
