package ie.accela.accounts.users;

import ie.accela.accounts.models.User;
import ie.accela.accounts.users.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User addUser(User user) {
        return repository.save(user);
    }

    @Override
    public User updateUser(int id, User user) {
        User currentUser = getUserById(id).orElse(null);
        if (currentUser == null) {
            return null;
        }
        if (user.getFirstName() != null) {
            currentUser.firstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            currentUser.lastName(user.getLastName());
        }
        return repository.save(currentUser);
    }

    @Override
    public boolean deleteUser(int id) {
        repository.deleteById(id);
        return !getUserById(id).isPresent();
    }

    @Override
    public List<User> getUsers() {
        return repository.findAll();
    }

    @Override
    public long getUsersCount() {
        return repository.count();
    }

    public Optional<User> getUserById(int id) {
        return repository.findById(id);
    }
}
