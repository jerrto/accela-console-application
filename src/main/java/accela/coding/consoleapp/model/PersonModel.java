
package accela.coding.consoleapp.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Person")
public class PersonModel {

/*    @Id
    @Column(name = "pid")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")*/

	private int pid;


	private String personfirstname;


	private String personlastname;


    private List<AddressModel> address = new ArrayList<>();

    public PersonModel() {
    }

    public PersonModel(String personfirstname, String personlastname) {
        this.personfirstname = personfirstname;
        this.personlastname = personlastname;
    }

    @Id
    @Column(name = "pid",nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Column(name = "personfirstname")
    public String getPersonfirstname() {
        return personfirstname;
    }

    public void setPersonfirstname(String personfirstname) {
        this.personfirstname = personfirstname;
    }

    @Column(name = "personlastname")
    public String getPersonlastname() {
        return personlastname;
    }

    public void setPersonlastname(String personlastname) {
        this.personlastname = personlastname;
    }

    @OneToMany( mappedBy = "person", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("person")
    public List<AddressModel> getAddress() {
        return address;
    }

    public void setAddress(List<AddressModel> address) {
        this.address = address;
    }
}

