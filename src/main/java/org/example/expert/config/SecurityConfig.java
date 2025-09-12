package org.example.expert.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtUtil jwtUtil;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	private static final String[] AUTH_WHITELIST = {
		"/auth/signup", "/auth/signin"
	};

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http

			.csrf((csrf) -> csrf.disable())
			.cors(Customizer.withDefaults())

			.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
			SessionCreationPolicy.STATELESS))

			.formLogin((form) -> form.disable())
			.httpBasic(AbstractHttpConfigurer::disable)


			.addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)

			.exceptionHandling((exceptionHandling) -> exceptionHandling
			.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.accessDeniedHandler(jwtAccessDeniedHandler)
		)

		.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(AUTH_WHITELIST).permitAll()
				.anyRequest().permitAll()
		);
		return http.build();
	}
}
