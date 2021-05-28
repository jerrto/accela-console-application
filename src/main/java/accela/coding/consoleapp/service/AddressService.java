package accela.coding.consoleapp.service;

import accela.coding.consoleapp.CustomException.InvalidUserInputException;
import accela.coding.consoleapp.model.AddressModel;

public interface AddressService {
    public String addAddressDetails(Integer id, AddressModel address) throws InvalidUserInputException;
    public String updateAddressDetails(Integer pid, Integer aId, AddressModel addressModel) throws InvalidUserInputException;
    public String deleteAddressById(Integer addressid);
    default void log(String str) {
        System.out.println("default method in interface");
    }
    static void print(String str) {
        System.out.println("static method in interface");
    }
}
