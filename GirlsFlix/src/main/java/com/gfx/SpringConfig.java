package com.gfx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.gfx.Keys;

@Configuration
@ComponentScan("com.gfx.service")
public class SpringConfig {
	
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl("jdbc:mysql://" + Keys.mysqlHost);
	    driverManagerDataSource.setUsername(Keys.mysqlUser);
	    driverManagerDataSource.setPassword(Keys.mysqlPwd);
	    return driverManagerDataSource;
	}
}
