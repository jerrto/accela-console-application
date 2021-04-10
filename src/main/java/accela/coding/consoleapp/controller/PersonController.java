package accela.coding.consoleapp.controller;


import accela.coding.consoleapp.CustomException.InvalidUserInputException;
import accela.coding.consoleapp.CustomException.ItemNotFoundException;
import accela.coding.consoleapp.model.AddressModel;
import accela.coding.consoleapp.model.PersonModel;
import accela.coding.consoleapp.service.AddressService;
import accela.coding.consoleapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class PersonController {
	
	@Autowired
	private PersonService personService;

	@Autowired
	private AddressService addressService;

	/*
	Add Person - POST
	 */
	@PostMapping("/person/add")
	public String addPerson(@RequestBody PersonModel person) {
		System.out.println(person);
		List<AddressModel> add = person.getAddress();
		if(!add.isEmpty()) {
			for (AddressModel item : add) {
				item.setPerson(person);
			}
		}
		personService.addUser(person);
		return "success";
	}

	/*
	Delete Person - DELETE
	 */
	@DeleteMapping("/person/delete/{id}")
	public String deletePersonById(@PathVariable Integer id){
		return personService.deletePersonById(id);
	}

	/*
	Update Person Details - PUT
	 */
	@PatchMapping("/person/update/{perso35nid}")
	public String updatePersonById(@PathVariable Integer personid, @RequestBody PersonModel person) throws Exception {
		PersonModel personModel = personService.getById(personid);
		Optional<PersonModel> result = Optional.ofNullable(person);
		if(result.isPresent()) {
			PersonModel pm = null;
			if(person.getPersonfirstname() != null) {
				personModel.setPersonfirstname(person.getPersonfirstname());
			}
			if(person.getPersonlastname() != null) {
				personModel.setPersonlastname(person.getPersonlastname());
			}
			if(person.getAddress() != null) {
				personModel.setAddress(person.getAddress());
			}
		} else {
			throw new Exception("Person ID is invalid. Please enter a valid Person ID");
		}
		return "updated record";
	}

	/*
	Get all details -  GET
	 */
	@GetMapping("/person/listdetails")
	public List<PersonModel> getPersonDetails(){
		List<PersonModel> personList =  personService.getAllPersonDetails();
		return personList;
	}

	/*
	Get person count -  GET
	 */
	@GetMapping("/person/count")
	public long getPersonCount() {
		return personService.getCount();
	}
	/*
	*//*
	Add Person - POST
	 */
	@PostMapping("/person/{personid}/address/add")
	public String addContactDetails(@PathVariable Integer personid, @RequestBody AddressModel address) throws ItemNotFoundException {
		PersonModel person = personService.getById(personid);
		Optional<PersonModel> result = Optional.ofNullable(person);
		if(!result.isPresent()) {
			throw new ItemNotFoundException(personid);
		}
		address.setPerson(person);
		personService.addContactDetails(address);
		return "New address added to person ID: "+personid+ " successfully";
	}


	/*Update aDDRESS Details - PUT
	 */
	@PatchMapping("/person/update/{personid}/address/{addressid}")
	public String updateAddress(@PathVariable Integer personid, @PathVariable Integer addressid, @RequestBody AddressModel address){
		AddressModel addressModel = addressService.getById(addressid);
		//Optional<AddressModel> addressModel = Optional.ofNullable(addressService.getById(addressid));
		/*if(addressModel.isPresent()){

		}*/
		//Use optional Java 8
		if(addressModel == null) {
			return "Invalid Address ID";
		}
		if(!address.getStreet().isEmpty()) {
			addressModel.setStreet(address.getStreet());
		}
		if(!address.getCity().isEmpty()) {
			addressModel.setCity(address.getCity());
		}
		if(!address.getState().isEmpty()) {
			addressModel.setState(address.getState());
		}
		if(!address.getZipcode().isEmpty()) {
			addressModel.setZipcode(address.getZipcode());
		}
		addressService.addAddress(addressModel);
		return "updated address";


	}

	/*
	Delete Person - DELETE
	 */
	@DeleteMapping("/person/update/{personid}/address/{addressid}/delete")
	public String deleteAddressById(@PathVariable Integer addressid){
		addressService.deleteAddressById(addressid);
		return "deleted +address ID: "+addressid;
		//throw new RuntimeException("Employee not found");
	}
}
