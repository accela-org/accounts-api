package ie.accela.accounts.users;

import ie.accela.accounts.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    User addUser(User user);

    User updateUser(int id, User user);

    boolean deleteUser(int id);

    List<User> getUsers();

    long getUsersCount();
}
