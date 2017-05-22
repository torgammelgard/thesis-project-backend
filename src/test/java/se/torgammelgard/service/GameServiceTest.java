package se.torgammelgard.service;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.TestConfig;
import se.torgammelgard.persistence.entities.Game;
import se.torgammelgard.repository.GameRepository;

/*
 * Tests the Game service
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
//@WebAppConfiguration("se.torgammelgard.config")
public class GameServiceTest {

    private static final Game TEST_GAME_1 = new Game("Test game 1");
    private static final Game TEST_GAME_2 = new Game("Test game 2");

    private int totalGamesAtStart;
    
    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameService gameService;

    @Before
    public void setup() {
    	totalGamesAtStart = gameService.findAll().size();
    }

    @Test
    public void testGameRepository() {
        gameRepository.save(TEST_GAME_1);
        gameRepository.save(TEST_GAME_2);
        List<Game> games = gameService.findAll();
        Assert.assertEquals("There should be two games", totalGamesAtStart + 2, games.size());
    }

    @After
    public void tearDown() {
        gameRepository.delete(TEST_GAME_1);
        gameRepository.delete(TEST_GAME_2);
    }

}
