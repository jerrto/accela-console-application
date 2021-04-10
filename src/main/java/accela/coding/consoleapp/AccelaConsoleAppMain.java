package accela.coding.consoleapp;

import accela.coding.consoleapp.model.AddressModel;
import accela.coding.consoleapp.model.PersonModel;
import accela.coding.consoleapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@EnableSwagger2
public class AccelaConsoleAppMain implements  CommandLineRunner{

	public static void main(String[] args) {
		//ConfigurableApplicationContext configurableApplicationContext =

		String url = "jdbc:sqlite:C:/sqlite/db/test.db";
		createNewDatabase(url);
		System.out.println(
				"LOY");
		createNewTables(url);

		//String name = sc.nextLine();
		System.out.println("Select from below to perform an operation:\n");
		System.out.println("1. Add Person (id, firstName, lastName)");
		System.out.println("2. Edit Person (firstName, lastName) - DONE");
		System.out.println("3. Delete Person (id)");
		System.out.println("4. Add Address to person [multiple required] (id, street, city, state, postalCode) DONE");
		System.out.println("5. Edit Address (street, city, state, postalCode) DONE");
		System.out.println("6. Delete Address (id) DONE");
		System.out.println("7. Count Number of Persons - DONE");
		System.out.println("8. List Persons:");
		SpringApplication.run(AccelaConsoleAppMain.class, args);



		/*PersonRepository per = configurableApplicationContext.getBean(PersonRepository.class);
		PersonModel person = new PersonModel("testfirst1", "testlast2");
		AddressModel address1 = new AddressModel("123 craftsman st", "boston", "GA", "90815", person);
		AddressModel address2 = new AddressModel("456 craftsman st", "Atlanta", "MA", "30097", person);
		List<AddressModel> address = Arrays.asList(address1,address2);
		person.setAddress(address);
		per.save(person);
		per.delete(person);*/

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Inside run function..");
		int input = 0;
		Scanner scanner = new Scanner(System.in);

		if(scanner.hasNextInt())
		{
			input = scanner.nextInt();
		}
		System.out.println("input: "+input);
		if(input == 1) {
			System.out.println("Enter First Name:");
			String firstName = scanner.nextLine();
			System.out.println("Enter Last Name:");
			String lastName = scanner.nextLine();
			final String uri = "http://localhost:8086/rest/person/add";
			//personService.addUser(person);
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(uri, String.class);
		} else {
			System.out.println("else");
		}

	}
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("accela.coding.consoleapp")).build();
	}

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

	private static void createNewTables(String url) {
		String sqlPerson = "CREATE TABLE IF NOT EXISTS Person (pid integer PRIMARY KEY AUTOINCREMENT, personfirstname text NOT NULL,personlastname real);";
		String sqlAddress = "CREATE TABLE IF NOT EXISTS Address (aid integer PRIMARY KEY AUTOINCREMENT, street text, city text, state text, zipcode text, person_id integer NOT NULL, \n" +
				"FOREIGN KEY (person_id)\n" +
				"REFERENCES Person(pid) ON DELETE CASCADE)";


		try (Connection conn = DriverManager.getConnection(url);
			 Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(sqlPerson);
			stmt.execute(sqlAddress);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	//PRAGMA foreign_keys = ON;
/*	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/accelaconsoleapp/**")
						.allowedOrigins("http://localhost:8085",
								"http://localhost:8082");
			}
		};
	}*/
}
