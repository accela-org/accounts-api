package ie.accela.accounts.address;

import ie.accela.accounts.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    private AddressService service;

    @Autowired
    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping(path = "/users/{userId}/address")
    public ResponseEntity addAddress(@PathVariable int userId, @RequestBody Address address) {
        Address newAddress;
        try {
            newAddress = service.addAddress(userId, address);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(newAddress, HttpStatus.CREATED);
    }

    @PutMapping(path = "/users/address/{id}")
    public ResponseEntity updateAddress(@PathVariable int id, @RequestBody Address address) {
        Address updatedAddress = service.updateAddress(id, address);
        return new ResponseEntity(updatedAddress, HttpStatus.OK);
    }

    @DeleteMapping(path = "/users/address/{id}")
    public ResponseEntity deleteAddress(@PathVariable int id) {
        return service.deleteAddress(id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
