package com.gft.palmirinha.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
public class InMemorySecutiryConfig {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder
			.inMemoryAuthentication()
			.withUser("admin@gft.com").password("{noop}Gft@1234").roles("USER", "ADMIN")
			.and()
			.withUser("usuario@gft.com").password("{noop}Gft@1234").roles("USER");
	}
}
