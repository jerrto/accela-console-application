package accela.coding.consoleapp.model;

import javax.persistence.*;

    @Entity
    @Table(name = "Address")
    public class AddressModel {

	private int aid;
	private String street;
	private String city;
	private String state;
    private String zipcode;

    private PersonModel person;

    public AddressModel() {
    }

    public AddressModel(String street, String city, String state, String zipcode, PersonModel person) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.person = person;
    }


    @Column(name="street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name="city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name="state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Id
    @Column(name = "aid")
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Column(name="zipcode")
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person", nullable = false)
    public PersonModel getPerson() {
        return person;
    }

    public void setPerson(PersonModel person) {
        this.person = person;
    }
}

