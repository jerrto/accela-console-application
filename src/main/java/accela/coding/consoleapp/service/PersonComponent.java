package accela.coding.consoleapp.service;

import accela.coding.consoleapp.model.AddressModel;
import accela.coding.consoleapp.model.PersonModel;
import accela.coding.consoleapp.repository.AddressRepository;
import accela.coding.consoleapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonComponent implements PersonService{

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;

    public void addUser(PersonModel person) {
        personRepository.save(person);
        System.out.println(person);
    }


    public String deletePersonById(Integer id){
        personRepository.deleteById(id);
        return "delete Success";
    }

    public List<PersonModel> getAllPersonDetails(){
        return (List<PersonModel>) personRepository.findAll();
    }

    public long getCount() {
        return personRepository.count();
    }

    public PersonModel getById(Integer id){
        return personRepository.findById(id).orElse(null);
    }

    public void addContactDetails(AddressModel addressModel) {
        addressRepository.save(addressModel);
    }

}
