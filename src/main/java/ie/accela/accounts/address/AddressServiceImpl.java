package ie.accela.accounts.address;

import ie.accela.accounts.address.dao.AddressRepository;
import ie.accela.accounts.models.Address;
import ie.accela.accounts.models.User;
import ie.accela.accounts.users.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Address addAddress(int userId, Address address) throws Exception {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new Exception("User not found");
        }
        address.userId(userId);
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(int id, Address address) {
        Address currentAddress = addressRepository.findById(id).orElse(null);
        if (currentAddress == null) {
            return null;
        }
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
        return addressRepository.save(currentAddress);
    }

    @Override
    public boolean deleteAddress(int id) {
        addressRepository.deleteById(id);
        return !getAddressById(id).isPresent();
    }

    private Optional<Address> getAddressById(int id) {
        return addressRepository.findById(id);
    }
}
