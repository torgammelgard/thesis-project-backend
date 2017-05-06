package se.torgammelgard.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonView;

import se.torgammelgard.Views;
import se.torgammelgard.persistence.entities.TennisSet;
import se.torgammelgard.service.TennisSetService;

@Controller
@RequestMapping("/api/tennisset")
public class TennisSetController {

	@Autowired
	private TennisSetService tennisSetService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@JsonView(Views.Public.class)
	public @ResponseBody List<TennisSet> listAll() {
		return tennisSetService.findAll();
	}
	
	@PostMapping
	@JsonView(Views.Public.class)
	public @ResponseBody TennisSet saveTennisSet(@RequestBody TennisSet tennisSet) {
		return tennisSetService.save(tennisSet);
	}
}
