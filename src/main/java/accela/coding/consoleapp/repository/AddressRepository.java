package accela.coding.consoleapp.repository;

import accela.coding.consoleapp.model.AddressModel;
import accela.coding.consoleapp.model.PersonModel;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressModel, Integer> {

}
