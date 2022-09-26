package CheckIn.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="checkinDb")
public class CheckIn {
	
	@Id
	private long bookingId;
	private List<Flight> flightid;
	private List<Passenger> passenger;
	private String confirmation;
	private long checkno;
	public CheckIn(long bookingId, List<Flight> flightid, List<Passenger> passenger, String confirmation, long checkno) {
		super();
		this.bookingId = bookingId;
		this.flightid = flightid;
		this.passenger = passenger;
		this.confirmation = confirmation;
		this.checkno = checkno;
	}
	public CheckIn() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getBookingId() {
		return bookingId;
	}
	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}
	public List<Flight> getFlightid() {
		return flightid;
	}
	public void setFlightid(List<Flight> flightid) {
		this.flightid = flightid;
	}
	public List<Passenger> getPassenger() {
		return passenger;
	}
	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}
	public String getConfirmation() {
		return confirmation;
	}
	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}
	public long getCheckno() {
		return checkno;
	}
	public void setCheckno(long checkno) {
		this.checkno = checkno;
	}
	@Override
	public String toString() {
		return "CheckIn [bookingId=" + bookingId + ", flightid=" + flightid + ", passenger=" + passenger
				+ ", confirmation=" + confirmation + ", checkno=" + checkno + "]";
	}

	
}
