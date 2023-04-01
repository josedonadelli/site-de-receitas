package com.gft.palmirinha.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				 
				  .antMatchers("/ingrediente/editar").hasAnyRole("ADMIN")
				  .antMatchers("/um/editar").hasAnyRole("ADMIN")
				  .antMatchers("/receita/editar").hasAnyRole("ADMIN")
				  .antMatchers("/ingrediente/excluir").hasAnyRole("ADMIN")
				  .antMatchers("/um/excluir").hasAnyRole("ADMIN")
				  .antMatchers("/receita/excluir").hasAnyRole("ADMIN")
				  .antMatchers("/popular").hasAnyRole("ADMIN")
				 
			.antMatchers("/ingrediente").hasAnyRole("USER")
			.antMatchers("/um").hasAnyRole("USER")
			.antMatchers("/receita").hasAnyRole("USER")
			.antMatchers("/").hasAnyRole("USER")
				.anyRequest()//Retirar anyRequest no final
				.authenticated()
			.and()
			.formLogin()
				.loginPage("/entrar") 
				.permitAll()
				.and()
				.logout()
					.logoutSuccessUrl("/entrar?logout")
					.permitAll();
	}
}
