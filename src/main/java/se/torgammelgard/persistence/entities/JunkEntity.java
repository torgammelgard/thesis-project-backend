package se.torgammelgard.persistence.entities;

import lombok.Data;
import lombok.NonNull;

@Data
public class JunkEntity {

	@NonNull
	private String name;
	
	private int age;
}
