package accela.coding.consoleapp;

import accela.coding.consoleapp.model.AddressModel;
import accela.coding.consoleapp.model.PersonModel;
import accela.coding.consoleapp.repository.AddressRepository;
import accela.coding.consoleapp.repository.PersonRepository;
import accela.coding.consoleapp.service.PersonComponent;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class AccelaConsoleAppMain implements CommandLineRunner {

	@Autowired
	private PersonRepository per;

	@Autowired
	private AddressRepository addr;

	Scanner sc = new Scanner(System.in).useDelimiter("\n");

	public AccelaConsoleAppMain() {
	}

	public static void main(String[] args) {
		String url = "jdbc:sqlite:C:/sqlite/test.db";
		createNewDatabase(url);
		createNewTables(url);

		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(AccelaConsoleAppMain.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		operations();
		throw  new Exception();
	}

	private void operations() {
		System.out.println("");
		System.out.println("================================================   SELECT OPERATION   ================================================");
		System.out.println("");
		System.out.println("Select from below to perform an operation:");
		System.out.println("1. Add Person (id, firstName, lastName):");
		System.out.println("2. Edit Person (firstName, lastName):");
		System.out.println("3. Delete Person (id):");
		System.out.println("4. Add Address to person [multiple required] (id, street, city, state, postalCode):");
		System.out.println("5. Edit Address (street, city, state, postalCode):");
		System.out.println("6. Delete Address (id):");
		System.out.println("7. Count Number of Persons:");
		System.out.println("8. List Persons:");
		int input = 0;

		if(sc.hasNextInt())
		{
			input = sc.nextInt();
		}
		switch (input) {
			case 1:
				inputPerson();
				break;
			case 2:
				editPerson();
				break;
			case 3:
				deletePerson();
				break;
			case 4:
				inputAddress();
				break;
			case 5:
				editAddress();
				break;
			case 6:
				deleteAddress();
				break;
			case 7:
				countPersons();
				break;
			case 8:
				listPersons();
				break;
		}
		operations();
	}

	private void inputPerson() {
		PersonModel result = null;
		System.out.println("Enter First Name:");
		String firstName = sc.next();
		sc.nextLine();
		System.out.println("Enter Last Name:");
		String lastName = sc.next();
		sc.nextLine();
		PersonModel person = new PersonModel(firstName, lastName);
		System.out.println("Do you have a valid address? (Yes/No)");
		String addressYesNo = sc.next();
		sc.nextLine();
		if("yes".equals(addressYesNo.toLowerCase())) {
			System.out.println("How many addresses do you want to add? (More than 1 allowed)");
			int addressCount = sc.nextInt();
			List<AddressModel> address = new ArrayList<>();
			int count;
			for(int i = 0; i < addressCount; i++) {
				count = i+1;
				System.out.println("Enter address "+count+" details:");
				System.out.println("Enter the street:");
				String street = sc.next();
				sc.nextLine();
				System.out.println("Enter the city:");
				String city = sc.next();
				sc.nextLine();
				System.out.println("Enter the state:");
				String state = sc.next();
				sc.nextLine();
				System.out.println("Enter the zipcode:");
				String zipcode = sc.next();
				sc.nextLine();
				AddressModel addressModel = new AddressModel(street, city, state, zipcode, person);
				address.add(addressModel);
			}
			person.setAddress(address);
			result = per.save(person);
		} else {
			per.save(person);
		}
		if(result != null) {
			System.out.println("******************************************************************************");
			System.out.println("User Added Successfully");
			System.out.println("******************************************************************************");

		}
		operations();
	}

	private void editPerson() {
		System.out.println("Enter The person ID:");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter First Name to change:");
		String firstName = sc.next();
		sc.nextLine();
		System.out.println("Enter Last Name to change:");
		String lastName = sc.next();
		sc.nextLine();
		PersonModel person = new PersonModel(firstName, lastName);
		PersonModel personModel = per.findById(id).orElse(null);
		Optional<PersonModel> personResult = Optional.ofNullable(personModel);
		if(personResult.isPresent()) {
			if(person.getPersonfirstname() != null) {
				personModel.setPersonfirstname(person.getPersonfirstname());
			}
			if(person.getPersonlastname() != null) {
				personModel.setPersonlastname(person.getPersonlastname());
			}
			per.save(personModel);
			System.out.println("******************************************************************************");
			System.out.println("Person with person ID: "+id +" updated successfully.");
			System.out.println("******************************************************************************");
		}
		operations();
	}

	private void deletePerson()
	{
		System.out.println("Enter The person ID:");
		int id = sc.nextInt();
		sc.nextLine();
		per.deleteById(id);
		System.out.println("******************************************************************************");
		System.out.println("Person with person ID: "+id +" deleted successfully.");
		System.out.println("******************************************************************************");
		operations();
	}

	private void inputAddress() {
		System.out.println("Enter The person ID:");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the street:");
		String street = sc.next();
		sc.nextLine();
		System.out.println("Enter the city:");
		String city = sc.next();
		sc.nextLine();
		System.out.println("Enter the state:");
		String state = sc.next();
		sc.nextLine();
		System.out.println("Enter the zipcode:");
		String zipcode = sc.next();
		sc.nextLine();
		PersonModel personModel = per.findById(id).orElse(null);
		Optional<PersonModel> personResult = Optional.ofNullable(personModel);
		if(personResult.isPresent()) {
			AddressModel addressModel = new AddressModel();
			addressModel.setStreet(street);
			addressModel.setCity(city);
			addressModel.setState(state);
			addressModel.setZipcode(zipcode);
			addressModel.setPerson(personModel);
			addr.save(addressModel);
			System.out.println("******************************************************************************");
			System.out.println("New address added to Person ID: "+ id + " successfully");
			System.out.println("******************************************************************************");
		}
		operations();
	}

	private void editAddress() {
		System.out.println("Enter The person ID:");
		int pid = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter The Address ID:");
		int aid = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the street:");
		String street = sc.next();
		sc.nextLine();
		System.out.println("Enter the city:");
		String city = sc.next();
		sc.nextLine();
		System.out.println("Enter the state:");
		String state = sc.next();
		sc.nextLine();
		System.out.println("Enter the zipcode:");
		String zipcode = sc.next();
		sc.nextLine();
		AddressModel address = new AddressModel();
		address.setStreet(street);
		address.setCity(city);
		address.setState(state);
		address.setZipcode(zipcode);

		PersonModel personModel = per.findById(pid).orElse(null);
		Optional<PersonModel> personResult = Optional.ofNullable(personModel);

		AddressModel addressModel = addr.findById(aid).orElse(null);
		Optional<AddressModel> addressResult = Optional.ofNullable(addressModel);

		if(personResult.isPresent() && addressResult.isPresent()) {
			address.setPerson(personModel);
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
			addr.save(addressModel);
			System.out.println("******************************************************************************");
			System.out.println("Address Updated Successfully for Person ID: "+ pid + " and Address ID: "+aid+"successfully");
			System.out.println("******************************************************************************");
		}
		operations();
	}

	private void deleteAddress() {
		System.out.println("Enter The Address ID:");
		int id = sc.nextInt();
		sc.nextLine();
		addr.deleteById(id);
		System.out.println("******************************************************************************");
		System.out.println("Address with address ID: "+id +" deleted successfully.");
		System.out.println("******************************************************************************");
		operations();
	}

	private void countPersons() {
		int count = (int) per.count();
		System.out.println("******************************************************************************");
		System.out.println("No of persons in system: "+ count);
		System.out.println("******************************************************************************");
		operations();
	}

	@Transactional
	private void listPersons() {
		List<PersonModel> list;
		list = (List<PersonModel>) per.findAll();
		int count  = 1;
		if(list.isEmpty()) {
			System.out.println("No info available");
		} else {
			System.out.println("Total result found: "+list.size());
			for(PersonModel l : list) {
				System.out.println("******************************************************************************");
				System.out.println("Person "+count+" details:");
				System.out.println("PersonID: "+l.getPid());
				System.out.println("First Name: "+l.getPersonfirstname());
				System.out.println("Last Name: "+l.getPersonlastname());
				List<AddressModel> addressModel = addr.getAddressByPersonId(l.getPid());
				int addrCount = 1;
				System.out.println("Total address available for person "+count+" is "+addressModel.size());
				for(AddressModel laddr : addressModel) {
					System.out.println("Address "+addrCount+ " details");
					System.out.println("Address ID: "+laddr.getAid());
					System.out.println("Street: "+ laddr.getStreet());
					System.out.println("City: "+laddr.getCity());
					System.out.println("State: "+laddr.getState());
					System.out.println("Zipcode: "+laddr.getZipcode());
					addrCount++;
				}
				count++;
			}
		}
		operations();
	}

	/**
	 * Create database
	 * @param url url to connect to db
	 */
	public static void createNewDatabase(String url) {
		try {
			Connection conn = DriverManager.getConnection(url);
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}
		} catch (SQLException s) {
			System.out.println("SQL Exception: "+s);
		}
	}

	/**
	 * cretae new tables
	 * @param url url to connect to db
	 */
	private static void createNewTables(String url) {
		String sqlPerson = "CREATE TABLE IF NOT EXISTS Person (\"pid\" integer PRIMARY KEY AUTOINCREMENT, \"personfirstname\" text NOT NULL,\"personlastname\" real);";
		String sqlAddress = "CREATE TABLE IF NOT EXISTS Address (\"aid\" integer PRIMARY KEY AUTOINCREMENT, \"street\" text, \"city\" text, \"state\" text, \"zipcode\" text, \"person_id\" integer NOT NULL, \n" +
				"FOREIGN KEY (person_id)\n" +
				"REFERENCES Person(pid) ON DELETE CASCADE)";
		try (Connection conn = DriverManager.getConnection(url);
			 Statement stmt = conn.createStatement()) {
			System.out.println("Tables created succesfully");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
