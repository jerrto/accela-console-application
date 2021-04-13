package accela.coding.consoleapp.service;

import accela.coding.consoleapp.CustomException.InvalidUserInputException;
import accela.coding.consoleapp.model.AddressModel;
import accela.coding.consoleapp.model.PersonModel;
import accela.coding.consoleapp.repository.AddressRepository;
import accela.coding.consoleapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonComponent implements PersonService{

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public String addUser(PersonModel person) throws InvalidUserInputException {
        Optional<PersonModel> personResult = Optional.ofNullable(person);
        String result;
        if(personResult.isPresent()) {
            List<AddressModel> add = person.getAddress();
            if(!add.isEmpty()) {
                for (AddressModel item : add) {
                    item.setPerson(person);
                }
            }
            personRepository.save(person);
            result = "Person with first name: "+ person.getPersonfirstname() + " and last name: "+ person.getPersonlastname()+ " added successfully";
        } else {
            throw new InvalidUserInputException();
        }
        return result;
    }

    @Override
    public String deletePersonById(Integer personid) throws InvalidUserInputException{
        personRepository.deleteById(personid);
        return "Person with person ID: "+personid+ " deleted successfully";
    }

    @Override
    public String updatePersonByPersonId(Integer id, PersonModel person) throws InvalidUserInputException {
        String result;
        PersonModel personModel = personRepository.findById(id).orElse(null);
        Optional<PersonModel> personResult = Optional.ofNullable(personModel);
        if(personResult.isPresent()) {
            if(person.getPersonfirstname() != null) {
                personModel.setPersonfirstname(person.getPersonfirstname());
            }
            if(person.getPersonlastname() != null) {
                personModel.setPersonlastname(person.getPersonlastname());
            }
            if(person.getAddress() != null) {
                personModel.setAddress(person.getAddress());
            }
            personRepository.save(personModel);
            result = "Person with person ID: "+id +" updated successfully.";
        } else {
            throw new InvalidUserInputException();
        }
        return result;
    }

    public List<PersonModel> getAllPersonDetails(){
        return (List<PersonModel>) personRepository.findAll();
    }

    public long getCount() {
        return personRepository.count();
    }
}
