package s4.spring.td3.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.td3.entities.Organization;
import s4.spring.td3.repositories.OrgaRepository;

@RestController
@RequestMapping("/rest/")
public class OrgasController {
	@Autowired
	private OrgaRepository repo;
	
	@GetMapping("/orgas")
	public List<Organization> read() {
		return repo.findAll();
	}
	
	@GetMapping("/orgas/{id}")
	public Optional<Organization> read(@PathVariable int id) {
		return repo.findById(id);
	}
	
	@PostMapping("/orgas/create")
	public void create(@RequestBody Organization orga) {
		repo.saveAndFlush(orga);
	}
	
	@PutMapping("/orgas/update/{id}")
	public void update(@PathVariable int id,@RequestBody Organization orga) {
		for(Organization o:repo.findAll()) {
			if (o.getId()==id) {
				int ids=o.getId();
				if (orga.getName()!=null) {
					repo.getOne(ids).setName(orga.getName());
				}
				if(orga.getDomain()!=null) {
					repo.getOne(ids).setDomain(orga.getDomain());		
				}
				if(orga.getAliases()!=null)
				repo.getOne(ids).setAliases(orga.getAliases());
			}
		}
		repo.flush();
	}
	
	@DeleteMapping("/orgas/delete")
	public void delete(@RequestBody Organization orga) {
		for(Organization o:repo.findAll()) {
			if (o.getId()==orga.getId()) {
				repo.deleteById(orga.getId());
			}
		}
		repo.flush();
	}
	
	@GetMapping("orgas/{member}/{id}")
	public String get(@PathVariable String member,@PathVariable int id) {
		switch(member) {
		case "name":return repo.getOne(id).getName();
		case "id":return Integer.toString(id);
		case "domain":return repo.getOne(id).getDomain();
		case "aliases":return repo.getOne(id).getAliases();
		default:return "Rien";
		}
	}
}
