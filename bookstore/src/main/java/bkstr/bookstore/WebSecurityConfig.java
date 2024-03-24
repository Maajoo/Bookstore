package bkstr.bookstore;

//import static method antMatcher
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;

	// with lambda
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(antMatcher("/css/**")).permitAll() // Enable css when logged out
						.anyRequest().authenticated())
				.formLogin(formlogin -> formlogin
						.loginPage("/login").permitAll()
						.defaultSuccessUrl("/booklist", true).permitAll())
				.logout(logout -> logout
						.permitAll());
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// @Bean
	// public UserDetailsService userDetailsService() {

	// System.out.println("in-memory users - luodaan näitä kaksi kappaletta");
	// List<UserDetails> users = new ArrayList<UserDetails>();

	// PasswordEncoder passwordEncoder =
	// PasswordEncoderFactories.createDelegatingPasswordEncoder();

	// UserDetails user1 =
	// User.withUsername("user").password(passwordEncoder.encode("user")).roles("USER").build();

	// users.add(user1);

	// UserDetails user2 =
	// User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("USER",
	// "ADMIN")
	// .build();

	// users.add(user2);

	// return new InMemoryUserDetailsManager(users);
	// }
}