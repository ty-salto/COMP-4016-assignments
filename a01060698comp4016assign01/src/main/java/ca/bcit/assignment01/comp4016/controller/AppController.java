package ca.bcit.assignment01.comp4016.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.bcit.assignment01.comp4016.model.Name;

@RestController
public class AppController {

	@GetMapping("/{input}")
	public String getInputResponse(@PathVariable String input) {
		
		if (input.equalsIgnoreCase("foo")) {
			return "bar";
		} else if (input.equalsIgnoreCase("kill")) {
			new Thread(()->{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.exit(0);
				}
				System.exit(0);
			}).start();
			
			return "bye bye";
		} 
		return "";
	}
	
	@PostMapping("/{input}")
	public String postInputResponse(@PathVariable String input, @RequestBody Name name) {
		StringBuilder str = new StringBuilder();
		if(input != null && !input.isEmpty()) {
			str.append(input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase() + " ");
		}
		str.append(name.getName() + "!");
		return str.toString();
	}
	
	
}
