package com.oraclejava.werim_lending_app;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            //.antMatchers("/").permitAll()  //메인 페이지를 막고 싶을 때는 주석처리함
            .antMatchers("/index").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/registration").permitAll()
            .antMatchers("/admin/**").permitAll()
		    //.antMatchers("/admin/**").hasAnyAuthority("role_admin").anyRequest().authenticated()
		    .and()
		    .formLogin()
		    .loginPage("/login").loginProcessingUrl("/loginProcess")
		    .failureUrl("/login?error=true")
		    .usernameParameter("username")
		    .passwordParameter("password")
		    .and()
		    .logout();	// 로그아웃 사용
		    
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring()
		    .antMatchers("/resources/**", "/statci/**", "/css/**", "/js/**","/images/**");
	}
}