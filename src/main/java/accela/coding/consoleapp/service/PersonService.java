package accela.coding.consoleapp.service;

import accela.coding.consoleapp.CustomException.InvalidUserInputException;
import accela.coding.consoleapp.model.PersonModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {

	public String addUser(PersonModel person) throws InvalidUserInputException;
	public String deletePersonById(Integer id) throws InvalidUserInputException;
	public String updatePersonByPersonId(Integer id, PersonModel person) throws InvalidUserInputException;
	public List<PersonModel> getAllPersonDetails();
	public long getCount();

}

