package accela.coding.consoleapp.service;

import accela.coding.consoleapp.model.AddressModel;

public interface AddressService {
    public void addAddress(AddressModel address);
    public String deleteById(Integer id);
    public AddressModel getById(Integer id);
    public String deleteAddressById(Integer addressid);
    /*
        public void addContactDetails(AddressModel addressModel) {
            addressRepository.save(addressModel);
        }
    */

}
