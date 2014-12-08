package game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;

@ComponentScan
@EnableAutoConfiguration
public class Launcher {

	public static void main(String[] args) {
		SpringApplication.run(Launcher.class, args);
	}

	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

		@Autowired
		private SecurityProperties security;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.authorizeRequests()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/lib/**").permitAll();

			http
			.formLogin().failureUrl("/login?error")
			.defaultSuccessUrl("/")
			.loginPage("/login")
			.permitAll();

//			http
//			.sessionManagement()
//			.maximumSessions(1)
//			.expiredUrl("/login?expired")
//			.maxSessionsPreventsLogin(true)
//			.and()
//			.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//			.invalidSessionUrl("/");

			http
			.authorizeRequests().anyRequest().authenticated();
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("admin").password("admin")
			.roles("ADMIN", "USER").and().withUser("user").password("user")
			.roles("USER");
		}

	}
}