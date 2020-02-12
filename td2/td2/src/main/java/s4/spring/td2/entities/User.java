package s4.spring.td2.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class User {	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    private String firstname;
    private String lastname;
    
    public User() {
    	this.setFirstname("Prenom");
    	this.setLastname("Nom");
    }
    
    public User(String p, String n) {
    	this.setFirstname(p);
    	this.setLastname(n);
    }
    
    
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}

	private String email;
    private String password;
     
    @ManyToOne
    private Organization organization;
     
    @ManyToMany(mappedBy="users")
    private List<Groupe> groupes;
}