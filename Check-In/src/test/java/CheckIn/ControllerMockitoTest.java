package CheckIn;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;


import CheckIn.Controller.CheckinController;
import CheckIn.Model.Booking;
import CheckIn.Model.CheckIn;
import CheckIn.Model.Flight;
import CheckIn.Model.FlightData;
import CheckIn.Model.Passenger;
import CheckIn.Service.CheckInService;

@SpringBootTest(classes= {ControllerMockitoTest.class})
public class ControllerMockitoTest {

	@Mock
	CheckInService checkinser;
	
	@InjectMocks
	CheckinController checkincon;
	
@LocalServerPort
int randomServerPort;

@Autowired
private TestRestTemplate restTemplate;

	@Test
	@Order(1)
	public void test_getInfo() throws URISyntaxException
	{
		List<FlightData> d=new  ArrayList<FlightData>();
		d.add(new FlightData("1234","3423","12:50","9:20") );
		
		
		List<Flight> f=new ArrayList<Flight>();
		f.add(new Flight("1234","Chennai","Mumbai",6000,60,30,"9:20",d));
		
		List<Passenger> p=new ArrayList<Passenger>();
		p.add(new Passenger(1,"Nirmal",3,5,334));
		p.add(new Passenger(2,"Sintha",4,6,335));
		
		CheckIn c= new CheckIn(1234,f,p,"Yes",2345);
		long i=1234;
	     
	 final String baseUrl = "http://localhost:" + randomServerPort + "/booking/"+i;
		    URI uri = new URI(baseUrl);
		    
		    //final String baseUrl = "http://localhost:8081/booking/"+id;
	
		  Booking b=this.restTemplate.getForObject(baseUrl,Booking.class);
		  Assertions.assertEquals(true,b.getBooking_id()==i);
		  
		  
		when(checkincon.getInfo(i)).thenReturn(c);
		CheckIn ch=checkincon.getInfo(i);
		
		assertEquals(i,ch.getBookingId());
	}
	
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
		
		when(checkinser.confirmation(c)).thenReturn(c);
		String s=checkincon.confirm();
		
		assertEquals("Check-In has confirmed"+c.getCheckno(),s);
		
	}*/
	
}
