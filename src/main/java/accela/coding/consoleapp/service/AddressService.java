package accela.coding.consoleapp.service;

import accela.coding.consoleapp.CustomException.InvalidUserInputException;
import accela.coding.consoleapp.model.AddressModel;

public interface AddressService {
    public String addAddressDetails(Integer id, AddressModel address) throws InvalidUserInputException;
    public String updateAddressDetails(Integer pid, Integer aId, AddressModel addressModel) throws InvalidUserInputException;
    public String deleteAddressById(Integer addressid);
}
