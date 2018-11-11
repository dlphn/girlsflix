package com.gfx;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.security.web.csrf.CsrfFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	
    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
	        .jdbcAuthentication()
	        .dataSource(dataSource) //check credentials in database
	        .usersByUsernameQuery("SELECT login, password, enabled FROM users WHERE login=?")
	        .authoritiesByUsernameQuery("SELECT login, role FROM user_roles WHERE login=?");
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception { //Configuration of http requests
    	//Encoding UTF-8 to avoid accents issues (prénom, affinities..)
    	CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");	
        filter.setForceEncoding(true);
        http
        	.addFilterBefore(filter, CsrfFilter.class)
            .authorizeRequests()
            	.antMatchers("/GirlsFlix/favoris", "/GirlsFlix/notifications").authenticated()
            	.anyRequest().permitAll()
	            .and()
            .formLogin()
	            .loginPage("/login").failureUrl("/login?error")
	  		  	.usernameParameter("username").passwordParameter("password")
            	.permitAll()
                .and()
            .logout()
	            .logoutSuccessUrl("/")
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .and()
  		  	.csrf();
    }
}