package ie.accela.accounts.address;

import ie.accela.accounts.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {

    private AddressService service;

    @Autowired
    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping(path = "/users/{userId}/address")
    public ResponseEntity addAddress(@PathVariable int userId, @RequestBody Address address) {
        return service.addAddress(userId, address);
    }

    @PutMapping(path = "/users/address/{id}")
    public ResponseEntity updateAddress(@PathVariable int id, @RequestBody Address address) {
        return service.updateAddress(id, address);
    }

    @DeleteMapping(path = "/users/address/{id}")
    public ResponseEntity deleteAddress(@PathVariable int id) {
        return service.deleteAddress(id);
    }
}
