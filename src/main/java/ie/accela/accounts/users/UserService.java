package ie.accela.accounts.users;

import ie.accela.accounts.models.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity addUser(User user);

    ResponseEntity updateUser(int id, User user);

    ResponseEntity deleteUser(int id);

    ResponseEntity getUsers();

    ResponseEntity getUsersCount();
}
