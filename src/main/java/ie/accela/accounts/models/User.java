package ie.accela.accounts.models;

import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Objects;

@ApiModel
public class User {

    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private List<Address> address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User id(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddresses(List<Address> address) {
        this.address = address;
    }

    public User address(List<Address> address) {
        this.address = address;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                firstName.equals(user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                address.equals(user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                '}';
    }
}
