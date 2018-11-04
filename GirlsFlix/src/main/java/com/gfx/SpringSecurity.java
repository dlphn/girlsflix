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
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

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
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { //config globale
        /*auth
            .inMemoryAuthentication() //La base des utilisateurs est en mémoire. En production, nous aurions très certainement une base de données afin de stocker les utilisateurs.
            .withUser("user").password("password").roles("USER"); //On ajoute un utilisateur avec le role ROLE_USER.*/
    	auth
	        .jdbcAuthentication() //base de données afin de stocker les utilisateurs.
	        .dataSource(dataSource)
	        .usersByUsernameQuery("select login, password, enabled from users where login=?")
	        .authoritiesByUsernameQuery("select login, role from user_roles where login=?");
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception { 
    	//Encoding UTF-8 pour ne pas avoir de problème d'accents (prénom, affinities..)
    	CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        //Configuration des requêtes http
        http.addFilterBefore(filter,CsrfFilter.class);
    	http
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