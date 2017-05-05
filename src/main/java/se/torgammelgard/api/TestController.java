package se.torgammelgard.api;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import se.torgammelgard.persistence.entities.TestEntity;

@Controller
@RequestMapping("/test")
public class TestController {

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody String testGetter(@RequestBody TestEntity testEntity) {
		//return new ResponseEntity<String>("Everything looks good", HttpStatus.OK);
		return "OK";
	}
	
	@ExceptionHandler
	public @ResponseBody String exHandle(Exception e) {
		return e.getClass().toGenericString();
	}
}
