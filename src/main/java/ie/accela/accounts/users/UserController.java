package ie.accela.accounts.users;

import ie.accela.accounts.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/users")
    public ResponseEntity addUser(@RequestBody User user) {
        User createdUser = userService.addUser(user);
        return new ResponseEntity(createdUser, HttpStatus.OK);
    }

    @PutMapping(path = "/users/{id}")
    public ResponseEntity updateUser(@PathVariable int id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        if (!userService.deleteUser(id)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = "/users")
    public ResponseEntity getAllUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @GetMapping(path = "/users/count")
    public ResponseEntity getUsersCount() {
        return new ResponseEntity(userService.getUsersCount(), HttpStatus.OK);
    }
}
