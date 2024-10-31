package ca.bcit.assignment03.comp4016.controller;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.bcit.assignment03.comp4016.model.Name;

@RestController
public class AppController {
//	@Value("${ENV_VAL}")
//	private String envVal;
	private final String filePath = "/data/jsalto/savedString.txt";  // Persistent volume path
	
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
	
//	@GetMapping("/envValue")
//	public String getEnvVal() {
//		return envVal;
//	}
	
    @GetMapping("/getString")
    public String getString() {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            try {
                return Files.readString(path);
            } catch (IOException e) {
                return "Error reading string.";
            }
        }
        return "No saved string found.";
    }
    
    @GetMapping("/busywait")
    public String busyWait() {
        long end = System.currentTimeMillis() + 180_000;  // Run for 3 minutes
        while (System.currentTimeMillis() < end) {
            System.out.print("busy waiting...\t");
        }
        return "CPU load triggered.";
    }
	
    @GetMapping("/isAlive")
    public String isAlive() {
        return "true";
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
	

    // Endpoint to save a string to the volume
    @PostMapping("/saveString")
    public String saveString(@RequestBody String data) {
        try (FileWriter writer = new FileWriter(filePath)) {
//            writer.write(data);
//            return "String saved successfully.";
            // Parse the JSON data
            JSONObject jsonObject = new JSONObject(data);

            // Write each key-value pair to the file
            for (String key : jsonObject.keySet()) {
                writer.write(jsonObject.get(key) + "\n");
            }
            
            return "String saved successfully.";
        } catch (IOException e) {
            return "Error saving string.";
        }
    }
	
}
