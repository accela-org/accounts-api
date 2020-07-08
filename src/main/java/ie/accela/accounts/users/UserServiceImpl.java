package ie.accela.accounts.users;

import ie.accela.accounts.models.User;
import ie.accela.accounts.users.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public ResponseEntity addUser(User user) {
        repository.save(user);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity updateUser(int id, User user) {
        getUserById(id).ifPresent(u -> {
            if (user.getFirstName() != null) {
                u.firstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                u.lastName(user.getLastName());
            }
            repository.save(u);
        });
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteUser(int id) {
        repository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity getUsers() {
        List<User> users = repository.findAll();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getUsersCount() {
        return null;
    }

    public Optional<User> getUserById(int id) {
        return repository.findById(id);
    }
}
