package accela.coding.consoleapp.service;

import accela.coding.consoleapp.model.AddressModel;
import accela.coding.consoleapp.repository.AddressRepository;
import accela.coding.consoleapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressComponent implements AddressService{

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;

    public void addAddress(AddressModel address) {
        addressRepository.save(address);
        System.out.println(address);
    }

    public String deleteById(Integer id){
        personRepository.deleteById(id);
        return "delete Success";
    }


    public AddressModel getById(Integer id){
        return addressRepository.findById(id).orElse(null);
    }
    /*
        public void addContactDetails(AddressModel addressModel) {
            addressRepository.save(addressModel);
        }
    */
    public String deleteAddressById(Integer addressid) {
        addressRepository.deleteById(addressid);
        return "delete address Success";
    }
}
