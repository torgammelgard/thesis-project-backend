import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import config.TestConfig;
import se.torgammelgard.persistence.entities.Match;
import se.torgammelgard.persistence.entities.Team;
import se.torgammelgard.persistence.entities.TennisSet;
import se.torgammelgard.persistence.entities.TennisSetScore;
import se.torgammelgard.service.MatchService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
//@WebAppConfiguration("se.torgammelgard.config")
public class MatchServiceTest {

//    private static final Team TEAM_1 = new Team(1L, "First team", "Foo1", "Foo2", null, null);
//    private static final Team TEAM_2 = new Team(2L, "Second team", "Bar1", "Bar2", null, null);


//    private static final Match MATCH_1 = new Match("MATCH 1", TEAM_1, TEAM_2);
//    private static final Match MATCH_2 = new Match("MATCH 2", TEAM_1, TEAM_2);

    @Autowired
    private MatchService matchService;

//    @Autowired
//    private MatchRepository matchRepository;

//    @Autowired
//    private TeamService teamService;

//    @Autowired
//    private TeamRepository teamRepository;

//    @PersistenceContext
//    private EntityManager entityManager;

    @Before
    public void setup() {
    }

    @Test
    @Transactional               // to get lazy initialized fields
    public void findAll_TwoMatches_IterableLengthOfTwo() {

        Team team1 = new Team();
        Team team2 = new Team();
        team1.setTeamName("First Team");
        team2.setTeamName("Second Team");

        TennisSet tennisSet1 = new TennisSet();
        //TennisSet tennisSet2 = new TennisSet();
        TennisSetScore tennisSetScore = new TennisSetScore();
        tennisSetScore.setScoreTeamOne(4);
        tennisSetScore.setScoreTeamTwo(6);
        tennisSet1.setTennisSetScore(Arrays.asList(tennisSetScore));

        List<Match> matches = matchService.findAll();
        Assert.assertEquals("Emtpy list", 0, matches.size());

        Match match1 = new Match();
        match1.setTeamOne(team1);
        match1.setTeamTwo(team2);
        match1.setTennisSets(Arrays.asList(tennisSet1));

        //matchService.save(match1);

        //matches = matchService.findAll();
        //Assert.assertEquals("Matches found should be 1", 1, matches.size());

    }

    @After
    public void tearDown() {
        //List<Team> teams = teamService.findAll();
    }

}
