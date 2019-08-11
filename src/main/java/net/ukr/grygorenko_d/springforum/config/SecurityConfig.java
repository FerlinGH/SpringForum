package net.ukr.grygorenko_d.springforum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String MODERATOR = "MODERATOR";
	private static final String ADMIN = "ADMIN";
	private static final String MEMBER = "MEMBER";
	private UserDetailsService userDetailsService;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		super();
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}
	
	public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		  CharacterEncodingFilter filter = new CharacterEncodingFilter();
	      filter.setEncoding("UTF-8");
	      filter.setForceEncoding(true);
	      http.addFilterBefore(filter,CsrfFilter.class);
	        
		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/showBoard/**").permitAll()
				.antMatchers("/topic/show/**").permitAll()
				.antMatchers("/topic/new/**").hasRole(MEMBER)
				.antMatchers("/topic/validateTopic/**").hasRole(MEMBER)
				.antMatchers("/topic/delete/**").hasRole(ADMIN)
				.antMatchers("/topic/rename/**").hasRole(MODERATOR)
				.antMatchers("/topic/setNewTopicName/**").hasRole(MODERATOR)
				.antMatchers("/message/new/**").hasRole(MEMBER)
				.antMatchers("/message/validateMessage/**").hasRole(MEMBER)
				.antMatchers("/message/edit/**").hasRole(MEMBER)
				.antMatchers("/message/delete/**").hasRole(ADMIN)
				.antMatchers("/forumMember/create/**").permitAll()
				.antMatchers("/forumMember/validateProfile/**").permitAll()				
			.and()
			.formLogin()
				.permitAll()
				.loginPage("/showLoginPage")
				.loginProcessingUrl("/authenticateUser")
			.and()
			.logout()
				.permitAll()
				.logoutUrl("/logout")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
	}

}
