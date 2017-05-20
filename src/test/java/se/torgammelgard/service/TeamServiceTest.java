package se.torgammelgard.service;
import java.security.Principal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import config.TestConfig;
import se.torgammelgard.persistence.entities.Team;
import se.torgammelgard.persistence.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WithMockUser(username = "Test", password = "test", roles = {"ADMIN", "USER"})
public class TeamServiceTest {

    private static final Team TEAM_ONE = new Team(1L, "Team name 1", "Foo", "Bar");
    private static final Team TEAM_TWO = new Team(2L, "Team name 2", "Foo", "Bar");

    @Autowired
    private TeamService teamService;
    
    private Principal principal;
    private long totalTeamsPreTest;
    private User user;
    
    @Before
    public void setup() {
    	Authentication authentication = Mockito.mock(Authentication.class);
    	principal = Mockito.mock(Principal.class);
    	Mockito.when(authentication.getPrincipal()).thenReturn(principal);
    	Mockito.when(authentication.getName()).thenReturn("Test");
    	// Mockito.whens() for your authorization object
    	SecurityContext securityContext = Mockito.mock(SecurityContext.class);
    	Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
    	SecurityContextHolder.setContext(securityContext);
    	
    	user = new User();
    	user.setUsername("Test");
    	
    	//totalTeamsPreTest = teamService
//    	teamService.save(TEAM_ONE, user);
//    	teamService.save(TEAM_TWO, user);
//        teamRepository.save(TEAM_ONE);
//        teamRepository.save(TEAM_TWO);
    }

    // Naming convention
    // MethodName_StateUnderTest_ExpectedBehavior
    @Test
    @Transactional
    public void findAllBelongingTo_TwoTeams_IterableLengthOfTwo() {

//        List<Team> teams = teamService.findAllBelongingTo(user);
//        Assert.assertEquals("Teams should be of size 2", totalTeamsPreTest + 2, teams.size());
    }

    @After
    public void tearDown() {
        //teamRepository.delete(TEAM_ONE);
        //teamRepository.delete(TEAM_TWO);
    }
}
