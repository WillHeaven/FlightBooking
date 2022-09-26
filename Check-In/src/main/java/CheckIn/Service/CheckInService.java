package CheckIn.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CheckIn.Exception.BusinessException;
import CheckIn.Model.CheckIn;
import CheckIn.Repository.CheckInRepository;

@Service
public class CheckInService {
	
	@Autowired
	private  CheckInRepository repo;
	
	public CheckIn confirmation(CheckIn check)
	{
//		try
//		{
//			if( (check.getBookingId()<0 && check.getBookingId()==0) || check.getFlightid().isEmpty() || check.getPassenger().isEmpty() || check.getConfirmation().contentEquals("Yes") || (check.getCheckno()<0 && check.getCheckno()==0))
//			{
//		throw new BusinessException("601","Please send proper details");
//			}
		return repo.save(check);
//		}
//	catch (Exception e)
//	          {
//			throw new BusinessException("602","Some thing went wrong in service layer"+e.getMessage());
//		      }
	}
	
	public boolean findById(long bookingid)
	{
		boolean present=repo.findById((long) bookingid).isPresent();
		return present;
	}

}
