package au.com.commbank.bookcatalogue.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
					.authorizeRequests()
					.anyRequest()
					.fullyAuthenticated()
					.and().httpBasic();		
		httpSecurity.csrf().disable();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password("{noop}commbank").roles("ADMIN").and()
			.withUser("user").password("{noop}user").roles("USER");
	}
}


