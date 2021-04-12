package accela.coding.consoleapp.service;

import accela.coding.consoleapp.CustomException.InvalidUserInputException;
import accela.coding.consoleapp.model.AddressModel;
import accela.coding.consoleapp.model.PersonModel;
import accela.coding.consoleapp.repository.AddressRepository;
import accela.coding.consoleapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddressComponent implements AddressService{

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;


    @Override
    public String addAddressDetails(Integer id, AddressModel address) throws InvalidUserInputException {
        String result;
        PersonModel personModel = personRepository.findById(id).orElse(null);
        Optional<PersonModel> personResult = Optional.ofNullable(personModel);
        if(personResult.isPresent()) {
            address.setPerson(personModel);
            addressRepository.save(address);
            result = "New address added to Person ID: "+ id + " successfully";
        } else {
            throw new InvalidUserInputException();
        }
        return result;
    }

    @Override
    public String updateAddressDetails(Integer pid, Integer aid, AddressModel address) throws InvalidUserInputException {
        String result;
        PersonModel personModel = personRepository.findById(pid).orElse(null);
        Optional<PersonModel> personResult = Optional.ofNullable(personModel);
        AddressModel addressModel = addressRepository.findById(aid).orElse(null);
        Optional<AddressModel> addressResult = Optional.ofNullable(addressModel);
        if(personResult.isPresent() && addressResult.isPresent()) {
            if(!address.getStreet().isEmpty()) {
                addressModel.setStreet(address.getStreet());
            }
            if(!address.getCity().isEmpty()) {
                addressModel.setCity(address.getCity());
            }
            if(!address.getState().isEmpty()) {
                addressModel.setState(address.getState());
            }
            if(!address.getZipcode().isEmpty()) {
                addressModel.setZipcode(address.getZipcode());
            }
            addressRepository.save(addressModel);
            result = "Address Updated Successfully";
        } else {
            throw new InvalidUserInputException();
        }
        return result;
    }

    @Override
    public String deleteAddressById(Integer id) {
        addressRepository.deleteById(id);
        return "Person with person ID: "+id+ " deleted successfully";
    }
}
