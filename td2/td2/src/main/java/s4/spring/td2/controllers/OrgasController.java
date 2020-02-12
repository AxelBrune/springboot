package s4.spring.td2.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import s4.spring.td2.entities.Groupe;
import s4.spring.td2.entities.Organization;
import s4.spring.td2.entities.User;
import s4.spring.td2.repositories.OrgaRepository;

@Controller
@RequestMapping("/orgas/")
@SessionAttributes({"listeOrgas","orgaEdit","orgaSearch"})
public class OrgasController {
	
	@ModelAttribute("listeOrgas")
	public List<Organization> getOrgas(){
		return repo.findAll();
	}
	
	@ModelAttribute("orgaEdit")
	public List<Organization> getOrgasEdit(){
		return new ArrayList<Organization>();
	}
	
	@ModelAttribute("orgaSearch")
	public List<Organization> getOrgasSearch(){
		return new ArrayList<Organization>();
	}
	
	@GetMapping("index")
	public  String index(){
		return "Index";
	}
	@GetMapping("new")
	public String newOrga() {
		return "nouvOrga";
	}
	@GetMapping("edit/{id}")
	public String editOrga(@PathVariable int id,@SessionAttribute List<Organization> orgaEdit) {
		List<Organization> listOrg=repo.findAll();
		for(Organization o:listOrg) {
			if(o.getId()==id) {
				orgaEdit.clear();
				orgaEdit.add(o);
			}
		}
		return "editOrga";
	}
	@Autowired
    private OrgaRepository repo;
     
    @PostMapping("new")
    @ResponseBody
    public RedirectView newOrga(Organization orga,@SessionAttribute List<Organization> listeOrgas) {
    	Groupe grp=new Groupe();
    	Groupe grp2=new Groupe("etudiant");
    	grp.setOrganization(orga);
    	grp2.setOrganization(orga);
    	List<Groupe> listeGrp = new ArrayList<Groupe>();
    	/*User u1=new User();
    	User u2 = new User("Axel", "Brune");
    	u1.setOrganization(orga);
    	u2.setOrganization(orga);
    	List<User> listeU = new ArrayList<User>();
    	listeU.add(u1);
    	listeU.add(u2);
    	grp.setUsers(listeU);*/
    	listeGrp.add(grp);
    	listeGrp.add(grp2);
    	orga.setGroupes(listeGrp);
        repo.saveAndFlush(orga);
        listeOrgas.add(orga);
        return new RedirectView("http://localhost:8091/orgas/index");
    }
    
    @PostMapping("new/{id}")
    @ResponseBody
    public RedirectView editOrga(Organization orga,@SessionAttribute List<Organization> listeOrgas) {
        return new RedirectView("http://localhost:8091/orgas/index");
    }
    
    @PostMapping("index/{id}")
    @ResponseBody
    public RedirectView Orgaedit(Organization orga,@SessionAttribute List<Organization> orgaEdit,@SessionAttribute List<Organization> listeOrgas) {
        int id = orga.getId();
        String name = orga.getName();
        String domain = orga.getDomain();
        String aliases = orga.getAliases();
        repo.getOne(id).setName(name);
        repo.getOne(id).setDomain(domain);
        repo.getOne(id).setAliases(aliases);
        repo.saveAndFlush(repo.getOne(id));
        for(Organization o:listeOrgas) {
        	if (o.getId()==id) {
        		o.setName(name);
        		o.setDomain(domain);
        		o.setAliases(aliases);
        	}
        }
        return new RedirectView("http://localhost:8091/orgas/index");
    }
    
    @GetMapping("delete/{idsup}")
	public String deleteOrga(@PathVariable int idsup) {
		return "deleteOrga";
	}
    @PostMapping("delete/{id}")
    @ResponseBody
    public RedirectView supOrga(Organization orga,@SessionAttribute List<Organization> listeOrgas,@PathVariable int id) {
        repo.deleteById(id);
        for(int i=0;i<listeOrgas.size();i++) {
        	if (listeOrgas.get(i).getId()==id) {
        		listeOrgas.remove(i);
        	}
        }
        return new RedirectView("http://localhost:8091/orgas/index");
    }
    @GetMapping("display/{idedit}")
	public String displayOrga(@PathVariable int idedit,@SessionAttribute List<Organization> orgaEdit) {
    	List<Organization> listOrg=repo.findAll();
		for(Organization o:listOrg) {
			if(o.getId()==idedit) {
				orgaEdit.clear();
				orgaEdit.add(o);
			}
		}
		return "displayOrga";
	}
    
    @PostMapping("search")
    public String searchOrga(@RequestParam String search,@SessionAttribute List<Organization> orgaSearch) {
    	List<Organization> listeO = repo.search(search);
    	orgaSearch.clear();
    	for (Organization o : listeO) {
    		orgaSearch.add(o);
    	}
    	return "searchOrga";
    }
}
