package se.torgammelgard.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import se.torgammelgard.persistence.entities.Game;
import se.torgammelgard.persistence.entities.User;

@Repository
public class GameRepoImpl implements GameRepository {

	@PersistenceContext
	EntityManager em;
	

	public List<Game> findAllGamesByOwner(User user) {
		Criteria c =  
	}


	@Override
	public List<Game> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Game> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Game> findAll(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Game> List<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <S extends Game> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteInBatch(Iterable<Game> entities) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Game getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Game> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Game> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Page<Game> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Game> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Game findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(Game entity) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(Iterable<? extends Game> entities) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <S extends Game> S findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Game> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Game> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public <S extends Game> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
}
