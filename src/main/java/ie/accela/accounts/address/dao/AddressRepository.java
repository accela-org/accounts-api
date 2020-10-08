package ie.accela.accounts.address.dao;

import ie.accela.accounts.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends MongoRepository<Address, Integer> {
}
