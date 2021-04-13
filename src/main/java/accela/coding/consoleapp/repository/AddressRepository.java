package accela.coding.consoleapp.repository;

import accela.coding.consoleapp.model.AddressModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<AddressModel, Integer> {

    @Query(value = "SELECT * FROM Address WHERE person = ?1", nativeQuery = true)
    public List<AddressModel> getAddressByPersonId(Integer id);
}
