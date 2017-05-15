package se.torgammelgard.service;
import java.security.Principal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import config.TestConfig;
import se.torgammelgard.persistence.entities.Team;
import se.torgammelgard.repository.TeamRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class TeamServiceTest {

    private static final Team TEAM_ONE = new Team(1L, "Team name 1", "Foo", "Bar");
    private static final Team TEAM_TWO = new Team(2L, "Team name 2", "Foo", "Bar");

    @Autowired
    private TeamRepository teamRepository;

    
    @Autowired
    private TeamService teamService;
    
    Principal principal;
    
    @Before
    public void setup() {
    	Authentication authentication = Mockito.mock(Authentication.class);
    	// Mockito.whens() for your authorization object
    	SecurityContext securityContext = Mockito.mock(SecurityContext.class);
    	Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
    	SecurityContextHolder.setContext(securityContext);
    	Principal p = (Principal) authentication.getPrincipal();
        teamRepository.save(TEAM_ONE);
        teamRepository.save(TEAM_TWO);
    }

    // Naming convention
    // MethodName_StateUnderTest_ExpectedBehavior
    @Test
    @Transactional
    public void findAll_TwoTeams_IterableLengthOfTwo() {
        Iterable<Team> allteams = teamService.findAllFor(principal);
        int size = 0;
        for (@SuppressWarnings("unused") Team t : allteams) {
            size++;
        }
        Assert.assertEquals("Teams should be of size 2", 2, size);
    }

    @After
    public void tearDown() {
        teamRepository.delete(TEAM_ONE);
        teamRepository.delete(TEAM_TWO);
    }
}
