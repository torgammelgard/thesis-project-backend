package se.torgammelgard.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class TestEntity {

//	@JsonView(Views.Public.class)
//	private String name;

//	@Id
//	@GeneratedValue
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public TestEntity() {
	}
	
	private String description;
	
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

//	private int age;
	
//	private JunkEntity junk;
}
