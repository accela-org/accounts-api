package ie.accela.accounts.address;

import ie.accela.accounts.address.dao.AddressRepository;
import ie.accela.accounts.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository repository;

    @Override
    public ResponseEntity addAddress(int userId, Address address) {
        address.userId(userId);
        repository.save(address);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity updateAddress(int id, Address address) {
        repository.findById(id)
                .ifPresent(currentAddress -> {
                    if (address.getStreet() != null) {
                        currentAddress.street(address.getStreet());
                    }
                    if (address.getCity() != null) {
                        currentAddress.city(address.getCity());
                    }
                    if (address.getState() != null) {
                        currentAddress.state(address.getState());
                    }
                    if (address.getPostalCode() != null) {
                        currentAddress.postalCode(address.getPostalCode());
                    }
                    repository.save(currentAddress);
                });
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteAddress(int id) {
        repository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
