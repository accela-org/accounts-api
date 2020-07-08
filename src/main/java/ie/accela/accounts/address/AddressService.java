package ie.accela.accounts.address;

import ie.accela.accounts.models.Address;
import org.springframework.http.ResponseEntity;

public interface AddressService {

    ResponseEntity addAddress(int userId, Address address);

    ResponseEntity updateAddress(int id, Address address);

    ResponseEntity deleteAddress(int id);
}
