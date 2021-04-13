package com.oraclejava.werim_lending_app;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/","/login","/registration").permitAll()
		    .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
		    .anyRequest().authenticated()
		    .and()
		    .formLogin()
		    .loginPage("/login").loginProcessingUrl("/loginProcess")
		    .defaultSuccessUrl("/")
		    .failureUrl("/login?error=true")
		    .and()
		    .logout();	// 로그아웃 사용
	}
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring()
		.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**","/images/**","/vendor/**");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}
}