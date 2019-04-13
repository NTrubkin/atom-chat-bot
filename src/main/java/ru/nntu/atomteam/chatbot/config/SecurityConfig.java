package ru.nntu.atomteam.chatbot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.nntu.atomteam.chatbot.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configure(AuthenticationManagerBuilder auth, UserService service) throws Exception {
		auth.userDetailsService(service);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/registration").permitAll()
				.antMatchers("/api/user").permitAll()
				.antMatchers("/").authenticated()
				.antMatchers("/learning").authenticated()
				.antMatchers("/api/**").authenticated()
				.and()
				.formLogin()
				.usernameParameter("login")
				.passwordParameter("password")
				.loginPage("/login")
				.defaultSuccessUrl("/", true)
				.failureUrl("/login?error=true")
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				.deleteCookies("JSESSIONID");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
				.ignoring()
				.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
