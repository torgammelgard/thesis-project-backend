package se.torgammelgard.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

import se.torgammelgard.Views;

/**
 * A dummy entity class, user for testing new things.
 * 
 * @author torgammelgard
 *
 */
@Entity 
@NamedQuery(name = "Game.findByVersion", query = "select g from Game g where g.version = ?1")
@Table(name = "GAMES")
public class Game implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	private Long id;

	@JsonView(Views.Public.class)
	private String name;

	@Version
	@JsonView(Views.Public.class)
	private Long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Game() {
		
	}
	
	public Game(String name) {
		this.name = name;
	}
}
