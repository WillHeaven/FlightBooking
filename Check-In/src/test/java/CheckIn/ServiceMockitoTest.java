package CheckIn;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import CheckIn.Model.CheckIn;
import CheckIn.Model.Flight;
import CheckIn.Model.FlightData;
import CheckIn.Model.Passenger;
import CheckIn.Repository.CheckInRepository;
import CheckIn.Service.CheckInService;

@SpringBootTest(classes= {ServiceMockitoTest.class})
public class ServiceMockitoTest {

	@Mock
	CheckInRepository checkinrep;
	
	@InjectMocks
	CheckInService checkinser;
	
	@Test
	@Order(1)
	public void test_confirmation()
	{
		List<FlightData> d=new  ArrayList<FlightData>();
		d.add(new FlightData("1234","3423","12:50","9:20") );
		
		
		List<Flight> f=new ArrayList<Flight>();
		f.add(new Flight("1234","Chennai","Mumbai",6000,60,30,"9:20",d));
		
		List<Passenger> p=new ArrayList<Passenger>();
		p.add(new Passenger(1,"Nirmal",3,5,334));
		
		
		CheckIn c= new CheckIn(234,f,p,"Yes",2345);
		
		when(checkinrep.save(c)).thenReturn(c);
		assertEquals(c,checkinser.confirmation(c));
		
	}
	
	@Test
	@Order(2)
	public void test_Present()
	{
		List<FlightData> d=new  ArrayList<FlightData>();
		d.add(new FlightData("1234","3423","12:50","9:20") );
		
		
		List<Flight> f=new ArrayList<Flight>();
		f.add(new Flight("1234","Chennai","Mumbai",6000,60,30,"9:20",d));
		
		List<Passenger> p=new ArrayList<Passenger>();
		p.add(new Passenger(1,"Nirmal",3,5,334));
		
		
		Optional<CheckIn> c= Optional.of(new CheckIn(234,f,p,"Yes",2345));
		
		long i=234;
		
		when(checkinrep.findById((long) i)).thenReturn(c);
		assertEquals(true,checkinser.findById(i));
	}
	
}
