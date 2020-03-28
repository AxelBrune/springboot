package s4.spring.td3.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import io.github.jeemv.springboot.vuejs.VueJS;
import s4.spring.td3.entities.Organization;
import s4.spring.td3.repositories.OrgaRepository;

@Controller
public class OrgasControllerVue {
	@Autowired
	private OrgaRepository repo;
	
	@Autowired
	private VueJS vue;
	
	@GetMapping("/testVue")
	public String index(ModelMap model) {
		List<String> names=new ArrayList<String>();
		for (Organization o:repo.findAll()) {
			names.add(o.getName());
		}
		String headers= "[{text: 'Nom',value: 'name',width: '20%'},{text: 'Alias',value: 'aliases'},{text: 'Domain',value: 'domain'}]";
				
		
		vue.addDataRaw("headers", "[{text: 'Nom',value: 'name',width: '20%'},{text: 'Alias',value: 'aliases'},{text: 'Domain',value: 'domain'}]");
		//vue.addData("headers", headers);
		vue.addData("orgas", repo.findAll());
		vue.addData("liste", names);
		vue.addData("message", "Test");
		model.put("vue", vue);
		return "index";
		
	}
	
}
