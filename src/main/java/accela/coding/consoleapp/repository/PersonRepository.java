package accela.coding.consoleapp.repository;

import accela.coding.consoleapp.model.PersonModel;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonModel, Integer> {


}
