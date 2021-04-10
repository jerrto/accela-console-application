package accela.coding.consoleapp.service;

import accela.coding.consoleapp.model.AddressModel;
import accela.coding.consoleapp.model.PersonModel;
import accela.coding.consoleapp.repository.AddressRepository;
import accela.coding.consoleapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public interface PersonService {

	public void addUser(PersonModel person);
	public String deletePersonById(Integer id);
	public List<PersonModel> getAllPersonDetails();
	public long getCount();

	public PersonModel getById(Integer id);

	public void addContactDetails(AddressModel addressModel);

}

