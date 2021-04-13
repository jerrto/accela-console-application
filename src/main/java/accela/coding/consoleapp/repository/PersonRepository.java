package accela.coding.consoleapp.repository;

import accela.coding.consoleapp.model.PersonModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonModel, Integer> {

}
