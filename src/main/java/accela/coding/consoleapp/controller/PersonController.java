package accela.coding.consoleapp.controller;

import accela.coding.consoleapp.model.AddressModel;
import accela.coding.consoleapp.model.PersonModel;
import accela.coding.consoleapp.service.AddressService;
import accela.coding.consoleapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class PersonController {
	
	@Autowired
	private PersonService personService;

	@Autowired
	private AddressService addressService;

	/**
	 * Function to add new person.
	 * @param person person model
	 * @return person added confirm message: String
	 */
	@PostMapping("/person/add")
	public String addPerson(@RequestBody PersonModel person) {
		return personService.addUser(person);
	}

	/**
	 * Delete person using personId
	 * @param personid ID of the person
	 * @return person deleted confirm message: String
	 */
	@DeleteMapping("/person/delete/{personid}")
	public String deletePersonById(@PathVariable Integer personid){
		return personService.deletePersonById(personid);
	}

	/**
	 * Update Person using person ID
	 * @param personid Person ID
	 * @param person Person Model contains person details
	 * @return success or failure message
	 */
	@PatchMapping("/person/update/{personid}")
	public String updatePersonById(@PathVariable Integer personid, @RequestBody PersonModel person) {
		return personService.updatePersonByPersonId(personid, person);
	}

	/**
	 *
	 * Get all details - GET
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
	 * @param personid person ID
	 * @param address Address model contains address details
	 * @return update success or failure message
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
