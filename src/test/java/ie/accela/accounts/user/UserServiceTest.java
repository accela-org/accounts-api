package ie.accela.accounts.user;

import ie.accela.accounts.models.User;
import ie.accela.accounts.users.UserService;
import ie.accela.accounts.users.UserServiceImpl;
import ie.accela.accounts.users.dao.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    private static final int ID_1 = 1;
    private static final int ID_2 = 2;
    private static final String FIRST_NAME_USER_1 = "firstName_1";
    private static final String LAST_NAME_USER_1 = "lastName_1";
    private static final String FIRST_NAME_USER_2 = "firstName_2";
    private static final String LAST_NAME_USER_2 = "lastName_2";

    private User user1;
    private User user2;
    List<User> allUsers;

    @TestConfiguration
    static class UserServiceTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository repository;

    @Before
    public void setUp() {

        user1 = new User()
                .id(ID_1)
                .firstName(FIRST_NAME_USER_1)
                .lastName(LAST_NAME_USER_1);

        user2 = new User()
                .id(ID_2)
                .firstName(FIRST_NAME_USER_2)
                .lastName(LAST_NAME_USER_2);

        allUsers = Arrays.asList(user1, user2);
    }

    @Test
    public void addUserTest() {
        // Given
        Mockito.when(repository.save(user1))
                .thenReturn(user1);

        // When
        User result = userService.addUser(user1);

        // Then
        Assert.assertEquals(FIRST_NAME_USER_1, result.getFirstName());
        Assert.assertEquals(LAST_NAME_USER_1, result.getLastName());
    }

    @Test
    public void updateUserTest() {
        // Given
        User updatedUser = new User()
                .id(ID_1)
                .firstName(FIRST_NAME_USER_2)
                .lastName(LAST_NAME_USER_2);

        Optional<User> optionalUser = Optional.of(user1);

        Mockito.when(repository.findById(ID_1)).thenReturn(optionalUser);
        Mockito.when(repository.save(updatedUser))
                .thenReturn(updatedUser);

        // When
        User result = userService.updateUser(ID_1, new User().firstName(FIRST_NAME_USER_2).lastName(LAST_NAME_USER_2));

        // Then
        Assert.assertEquals(ID_1, (int)result.getId());
        Assert.assertEquals(FIRST_NAME_USER_2, result.getFirstName());
        Assert.assertEquals(LAST_NAME_USER_2, result.getLastName());
    }

    @Test
    public void getAllUsersTest() {
        // Given
        Mockito.when(repository.findAll()).thenReturn(allUsers);

        // When
        List<User> result = userService.getUsers();

        // Then
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void getUsersCountTest() {
        // Given
        Mockito.when(repository.count()).thenReturn(2L);

        // When
        long result = userService.getUsersCount();

        // Then
        Assert.assertEquals(2L, result);
    }
}
