package s4.spring.td2.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Groupe{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
     
    @ManyToOne
    private Organization organization;
     
    @ManyToMany
    @JoinTable(name = "user_group")
    private List<User> users;
    
    private String name;
    private String email;
    private String aliazes;
    public Groupe() {
    	this("Groupe par défaut");
    }
    
    
	public Groupe(String name){
		this.setId(id);
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAliazes() {
		return aliazes;
	}
	public void setAliazes(String aliazes) {
		this.aliazes = aliazes;
	}
    
    
}
