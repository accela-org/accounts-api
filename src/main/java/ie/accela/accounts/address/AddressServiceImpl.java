package ie.accela.accounts.address;

import ie.accela.accounts.address.dao.AddressRepository;
import ie.accela.accounts.models.Address;
import ie.accela.accounts.models.User;
import ie.accela.accounts.users.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity addAddress(int userId, Address address) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        User user = optionalUser.get();
        address.user(user);
        addressRepository.save(address);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity updateAddress(int id, Address address) {
        addressRepository.findById(id)
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
                    addressRepository.save(currentAddress);
                });
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteAddress(int id) {
        addressRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
