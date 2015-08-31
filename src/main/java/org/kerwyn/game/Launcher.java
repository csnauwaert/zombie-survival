package org.kerwyn.game;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//import org.springframework.security.config.http.SessionCreationPolicy;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EnableConfigurationProperties
public class Launcher {
	
	public static void main(String[] args) {
		SpringApplication.run(Launcher.class, args);
	}

	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class ApplicationSecurity extends
			WebSecurityConfigurerAdapter {

		@Autowired
		private SecurityProperties security;

		@Autowired
		private DataSource dataSource;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/css/**").permitAll()
					.antMatchers("/lib/**").permitAll().antMatchers("/")
					.permitAll().antMatchers("/error").permitAll()
					.antMatchers("/game/**").authenticated();

			http.formLogin().failureUrl("/login?error").defaultSuccessUrl("/")
					.loginPage("/login").permitAll().and().logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/");
			// This ensure that we can logout using a GET request on page
			// /logout, should be changed
			// to prevent some security issue where another user can disconnect
			// current user
			// The correct way to do it would be by performing a POST request
			// via a form or js
			// see:
			// http://docs.spring.io/spring-security/site/docs/3.2.4.RELEASE/reference/htmlsingle/#csrf-logout

			// http
			// .sessionManagement()
			// .maximumSessions(1)
			// .expiredUrl("/login?expired")
			// .maxSessionsPreventsLogin(true)
			// .and()
			// .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			// .invalidSessionUrl("/");

		}

		@Override
		public void configure(AuthenticationManagerBuilder auth)
				throws Exception {
			auth.jdbcAuthentication().dataSource(this.dataSource);
		}
	}
}