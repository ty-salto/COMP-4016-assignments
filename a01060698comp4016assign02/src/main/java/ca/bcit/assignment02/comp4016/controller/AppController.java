package ca.bcit.assignment02.comp4016.controller;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.bcit.assignment02.comp4016.model.Name;

@RestController
public class AppController {
	@Value("${ENV_VAL}")
	private String envVal;
	
	// GET Request
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
	
	@GetMapping("/configValue")
	public String getConfigVal() throws IOException {
		StringBuilder str = new StringBuilder();
		
		try (Scanner scanner = new Scanner(new File("/config/configValue"))) {
			while(scanner.hasNextLine()) {
				str.append(scanner.nextLine());
			}
		}
	
		return str.toString();
		
	}
	
	@GetMapping("/secretValue")
	public String getSecretVal() throws IOException {
	    return Files.readString(Paths.get("/secret/secretValue"));
	}
	
	@GetMapping("/envValue")
	public String getEnvVal() {
		return envVal;
	}
	
	// POST Request
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