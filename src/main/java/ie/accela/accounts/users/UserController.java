package ie.accela.accounts.users;

import ie.accela.accounts.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/users")
    public ResponseEntity addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping(path = "/users/{id}")
    public ResponseEntity updateUser(@PathVariable int id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @GetMapping(path = "/users")
    public ResponseEntity getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/users/count")
    public ResponseEntity getUsersCount() {
        return userService.getUsersCount();
    }
}
