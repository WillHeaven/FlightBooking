package CheckIn.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import CheckIn.Model.CheckIn;

@Repository
public interface CheckInRepository extends MongoRepository<CheckIn,Long> 
{

}
