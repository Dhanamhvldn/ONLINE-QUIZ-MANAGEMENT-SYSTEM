package org.OnlineQuizApplication.Security;

import java.util.ArrayList;
import java.util.List;

import org.OnlineQuizApplication.Entity.Users;
import org.OnlineQuizApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserRepository userRepository;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests().requestMatchers("/user","/addPassword").permitAll()
				.requestMatchers("/user/*").hasRole("USER")
				.requestMatchers("/admin/*").hasRole("ADMIN")
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
				.successHandler((request, response, authentication) -> {
					String role = authentication.getAuthorities().iterator().next().getAuthority();
					if ("ROLE_USER".equals(role)) {
						response.sendRedirect("/user");
					} else if ("ROLE_ADMIN".equals(role)) {
						response.sendRedirect("/admin");
					} else {
						response.sendRedirect("/user");
					}
				}).and().logout().logoutSuccessUrl("/login").permitAll();
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepository) {
		return username -> {
			Users user = userRepository.findByUsername(username);
			if (user == null) {
				throw new UsernameNotFoundException(username);
			}
			System.out.println("User fetched: " + user.getUsername());
			System.out.println("Roles fetched: " + user.getRole());
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));

			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					authorities);
		};
	}
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	

}
