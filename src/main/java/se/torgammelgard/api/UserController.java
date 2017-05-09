package se.torgammelgard.api;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@GetMapping
	public @ResponseBody String userInfo(Principal principal) {
		UserDetails u = userDetailsService.loadUserByUsername(principal.getName());
		Collection<? extends GrantedAuthority> authorities = u.getAuthorities();
		StringBuilder builder = new StringBuilder();
		for (GrantedAuthority grantedAuthority : authorities) {
			builder.append(grantedAuthority.getAuthority());
		}
		return "Username is : " + principal.getName() + ". Authorities are : " + builder.toString();
	}
}
