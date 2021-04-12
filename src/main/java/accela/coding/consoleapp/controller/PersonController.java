package accela.coding.consoleapp.controller;

import accela.coding.consoleapp.CustomException.InvalidUserInputException;
import accela.coding.consoleapp.model.AddressModel;
import accela.coding.consoleapp.model.PersonModel;
import accela.coding.consoleapp.service.AddressService;
import accela.coding.consoleapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class PersonController {
	
	@Autowired
	private PersonService personService;

	@Autowired
	private AddressService addressService;

	/**
	 * Function to add new person.
	 * @param person
	 * @return result string
	 */
	@PostMapping("/person/add")
	public String addPerson(@RequestBody PersonModel person) {
		return personService.addUser(person);
	}

	/**
	 * Delete person using personId
	 * @param personid
	 * @return
	 */
	@DeleteMapping("/person/delete/{personid}")
	public String deletePersonById(@PathVariable Integer personid){
		return personService.deletePersonById(personid);
	}

	/**
	 * Update Person using person ID
	 * @param personid
	 * @param person
	 * @return success or failure message
	 * @throws Exception
	 */
	@PatchMapping("/person/update/{personid}")
	public String updatePersonById(@PathVariable Integer personid, @RequestBody PersonModel person) throws Exception {
		return personService.updatePersonByPersonId(personid, person);
	}

	/*
	Get all details - GET
	 */
	@GetMapping("/person/listdetails")
	public List<PersonModel> getPersonDetails(){
		return personService.getAllPersonDetails();
	}

	/**
	 * Get person count
	 * @return long count
	 */
	@GetMapping("/person/count")
	public long getPersonCount() {
		return personService.getCount();
	}

	/**
	 * Add address details to person using person
	 * @param personid
	 * @param address
	 * @return update success or failure message
	 * @throws InvalidUserInputException
	 */
	@PostMapping("/person/{personid}/address/add")
	public String addAddressDetails(@PathVariable Integer personid, @RequestBody AddressModel address){
		return addressService.addAddressDetails(personid, address);
	}

	/*
	Update ADDRESS Details - PUT
	*/
	@PatchMapping("/person/update/{personid}/address/{addressid}")
	public String updateAddressDetails(@PathVariable Integer personid, @PathVariable Integer addressid, @RequestBody AddressModel address){
		return addressService.updateAddressDetails(personid, addressid, address);
	}

	/*
	Delete Person - DELETE
	 */
	@DeleteMapping("/person/update/{personid}/address/{addressid}/delete")
	public String deleteAddressById(@PathVariable Integer addressid){
		return addressService.deleteAddressById(addressid);
		//throw new RuntimeException("Employee not found");
	}
}
