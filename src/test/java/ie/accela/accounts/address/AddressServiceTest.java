package ie.accela.accounts.address;

import ie.accela.accounts.address.dao.AddressRepository;
import ie.accela.accounts.models.Address;
import ie.accela.accounts.models.User;
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
public class AddressServiceTest {

    private static final int ID_1 = 1;
    private static final int ID_2 = 2;
    private static final String STREET_1 = "1 Street";
    private static final String STREET_2 = "2 Street";
    private static final String CITY_1 = "City_1";
    private static final String CITY_2 = "City_2";
    private static final String STATE_1 = "State_1";
    private static final String STATE_2 = "State_2";
    private static final String POSTAL_CODE_1 = "A1";
    private static final String POSTAL_CODE_2 = "B2";
    private static final int USER_ID = 1;
    private static final String FIRST_NAME_USER = "firstName_1";
    private static final String LAST_NAME_USER = "lastName_1";

    private Address address1;
    private Address address2;
    private List<Address> addressList;
    private User user;

    @TestConfiguration
    static class AddressServiceTestContextConfiguration {
        @Bean
        public AddressService addressService() {
            return new AddressServiceImpl();
        }
    }

    @Autowired
    private AddressService addressService;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private UserRepository userRepository;


    @Before
    public void setUp() {
        user = new User()
                .id(USER_ID)
                .firstName(FIRST_NAME_USER)
                .lastName(LAST_NAME_USER);

        address1 = new Address()
                .id(ID_1)
                .city(CITY_1)
                .state(STATE_1)
                .postalCode(POSTAL_CODE_1)
                .user(user);

        address2 = new Address()
                .id(ID_2)
                .city(CITY_2)
                .state(STATE_2)
                .postalCode(POSTAL_CODE_2)
                .user(user);
    }

    @Test
    public void addAddressTest() throws Exception{
        // Given
        Optional<User> optionalUser = Optional.of(user);
        Mockito.when(userRepository.findById(USER_ID)).thenReturn(optionalUser);
        Mockito.when(addressRepository.save(address1)).thenReturn(address1);

        // When
        Address result = addressService.addAddress(USER_ID, address1);

        // Then
        Assert.assertEquals(CITY_1, result.getCity());
        Assert.assertEquals(user, result.getUser());
    }

    @Test
    public void updateAddressTest() {
        // Given
        Optional<Address> optionalAddress = Optional.of(address1);

        Mockito.when(addressRepository.findById(ID_1))
                .thenReturn(optionalAddress);
        Address newAddress = address2.id(ID_1);
        Mockito.when(addressRepository.save(newAddress))
                .thenReturn(newAddress);

        // When
        Address result = addressService.updateAddress(ID_1, newAddress);

        // Then
        Assert.assertEquals(ID_1, (int) result.getId());
        Assert.assertEquals(CITY_2, result.getCity());
    }
}
