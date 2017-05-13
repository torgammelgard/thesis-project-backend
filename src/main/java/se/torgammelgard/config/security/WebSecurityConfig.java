package se.torgammelgard.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import se.torgammelgard.web.MyLogoutSuccessHandler;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final int PASSWORD_ENCODER_STRENGTH = 8;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public MyLogoutSuccessHandler myLogoutSuccessHandler() {
		return new MyLogoutSuccessHandler();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	 	authenticationProvider.setUserDetailsService(userDetailsService);
	 	authenticationProvider.setPasswordEncoder(passwordEncoder());
	 	return authenticationProvider;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(PASSWORD_ENCODER_STRENGTH);
	}
	
    @Autowired
    public void configureItAll(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("u").password("p").roles("USER");
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //.anyRequest().permitAll() // TODO change this
                //.antMatchers("**/api/team").permitAll()
                .antMatchers(new String[]{"/registration", "/successfulregistration", "/successful_logout", "/perform_login"}).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll().loginProcessingUrl("/perform_login").failureUrl("/login?error")
                .and()
                .logout().logoutUrl("/perform_logout").logoutSuccessHandler(myLogoutSuccessHandler()).deleteCookies("JSESSIONID")
                .and()
                .csrf().disable();
    }

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(new String[]{"/styles/**", "/images/**", "/scripts/**"});
	}
}
