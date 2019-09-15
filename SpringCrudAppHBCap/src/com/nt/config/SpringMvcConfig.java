package com.nt.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.nt.dao.ContactDao;
import com.nt.dao.ContactDaoImpl;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.nt")
public class SpringMvcConfig implements WebMvcConfigurer {
	
	@Bean
	public DataSource getDataSource() {

	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	dataSource.setUrl("jdbc:mysql://localhost:3306/contactdb");
	dataSource.setUsername("root");
	dataSource.setPassword("Pratik@123");
	
	return dataSource;
	
	}
	
	@Bean
	public ViewResolver getViewResolver() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		return resolver;
		
	}
	
	@Bean
	public ContactDao getContactDao() {
		return new ContactDaoImpl(getDataSource());
		 
	}
	
	
}
