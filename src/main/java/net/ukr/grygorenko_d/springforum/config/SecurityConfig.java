package net.ukr.grygorenko_d.springforum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import net.ukr.grygorenko_d.springforum.entity.RoleTypes;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// add our users for in-memory authentication
		
		auth.inMemoryAuthentication().withUser("Admin").password("test123").roles(RoleTypes.ADMIN.toString()).and()
		.withUser("Moderator").password("test123").roles(RoleTypes.MODERATOR.toString());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest().permitAll()
			.and()
			.formLogin()
				.loginPage("/showLoginPage")
				.loginProcessingUrl("/authenticateUser")
				.permitAll();
	}
	
	
	
	

}
