package ie.accela.accounts.address;

import ie.accela.accounts.models.Address;

public interface AddressService {

    Address addAddress(int userId, Address address) throws Exception;

    Address updateAddress(int id, Address address);

    boolean deleteAddress(int id);
}
