package net.ukr.grygorenko_d.springforum.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("net.ukr.grygorenko_d.springforum")
@EnableTransactionManagement
public class WebMvcConfig implements WebMvcConfigurer {

	@Bean
	public InternalResourceViewResolver setupResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(1);
		return resolver;
	}

	// Local dataSource
//	 @Bean
//	 public DataSource localDataSource() {
//	 DriverManagerDataSource dataSource = new DriverManagerDataSource();
//	 dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//	 dataSource.setUrl("jdbc:mysql://localhost:3306/springforum?useSSL=false");
//	 dataSource.setUsername("springstudent");
//	 dataSource.setPassword("springstudent");
//	
//	 return dataSource;
//	 }

	// Heroku dataSource
	@Bean
	public DataSource herokuDataSource() throws URISyntaxException {
		URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(false);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");

		return adapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
			JpaVendorAdapter adapter) {
		Properties jpaProp = new Properties();
		jpaProp.put("hibernate.hbm2ddl.auto", "update");

		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setJpaVendorAdapter(adapter);
		entityManagerFactory.setJpaProperties(jpaProp);
		entityManagerFactory.setPackagesToScan("net.ukr.grygorenko_d.springforum");

		return entityManagerFactory;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/css/**").addResourceLocations("/resources/css/");
	}

}
