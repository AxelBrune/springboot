package s4.spring.td3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.jeemv.springboot.vuejs.VueJS;

@Controller
@RequestMapping("/ui/")
public class UiTest {
	@GetMapping("test")
	public String index(ModelMap model) {
		VueJS vue=new VueJS("#app");
		vue.setDelimiters("<%", "%>");
		vue.addData("message", "Hello world!");
		model.put("vue", vue);
		return "index";
	}
}
