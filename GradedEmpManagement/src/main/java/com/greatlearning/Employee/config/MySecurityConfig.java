package com.greatlearning.Employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.Employee.service.UserDetailsServiceImpl;


@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public UserDetailsService getDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider getProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(getDetailsService());
		auth.setPasswordEncoder(getEncoder());
		return auth;
	}

	@Override
	public void configure(AuthenticationManagerBuilder builder) {
		builder.authenticationProvider(getProvider());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/list")
				.hasAnyAuthority("USER")
				.antMatchers("/api/list","/api/employees", "/api/addRole","/api/addUser",
						"/api/employees/{id}","/api/updateEmp","/api/deleteEmp/{id}","/api/employees/search/{firstName}",
						"/api/employees/sort")
				.hasAuthority("ADMIN").anyRequest().authenticated()
		        .and()
		        .formLogin().permitAll()
		        .and()
		        .logout().permitAll().and().exceptionHandling().and().csrf().and().cors().disable();
	}
	
	
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/api/list")
//				.hasAnyAuthority("ADMIN", "USER")
//				.antMatchers("/api/list","/api/employees", "/api/addRole","/api/addUser",
//						"/api/employees/{id}","/api/updateEmp","/api/deleteEmp/{id}","/api/employees/search/{firstName}",
//						"/api/employees/sort")
//				.hasAuthority("ADMIN").anyRequest().authenticated().and().formLogin().loginProcessingUrl("/login")
//				.defaultSuccessUrl("/api/list").permitAll().and().logout().logoutSuccessUrl("/login").permitAll()
//				.and().exceptionHandling().and().csrf().and().cors().disable();
//	}

}
