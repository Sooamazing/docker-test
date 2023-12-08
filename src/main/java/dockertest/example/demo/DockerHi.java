package dockertest.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerHi {

	@GetMapping("/")
	public String hi(){
		return "hi";
	}
}
