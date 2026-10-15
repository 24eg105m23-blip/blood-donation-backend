package com.bloodcare.config;

import com.bloodcare.model.*;
import com.bloodcare.model.Models.*;
import com.bloodcare.repository.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.*;
import java.util.*;

@Configuration
public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean UserDetailsService userDetailsService(UserRepo users){return email->{User u=users.findByEmailIgnoreCase(email).orElseThrow(()->new UsernameNotFoundException("Account not found")); return org.springframework.security.core.userdetails.User.withUsername(u.email).password(u.passwordHash).disabled(!u.enabled).authorities("ROLE_"+u.role.name()).build();};}
 @Bean AuthenticationManager authenticationManager(AuthenticationConfiguration c)throws Exception{return c.getAuthenticationManager();}
 @Bean CorsConfigurationSource corsConfigurationSource(@Value("${app.cors-origin}") String origin){CorsConfiguration c=new CorsConfiguration();c.setAllowedOrigins(List.of(origin));c.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));c.setAllowedHeaders(List.of("Content-Type","X-XSRF-TOKEN"));c.setAllowCredentials(true);UrlBasedCorsConfigurationSource s=new UrlBasedCorsConfigurationSource();s.registerCorsConfiguration("/**",c);return s;}
 @Bean SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
  var csrfRepository=org.springframework.security.web.csrf.CookieCsrfTokenRepository.withHttpOnlyFalse();
  var csrfHandler=new org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler();
  csrfHandler.setCsrfRequestAttributeName(null);
  return http.cors(c->{}).csrf(c->c.csrfTokenRepository(csrfRepository).csrfTokenRequestHandler(csrfHandler)).authorizeHttpRequests(a->a.requestMatchers("/api/auth/**","/api/dashboard/stats","/api/donors/search","/api/donors/available").permitAll().requestMatchers(HttpMethod.GET,"/api/donors").permitAll().requestMatchers("/api/admin/**").hasRole("ADMIN").anyRequest().authenticated()).formLogin(f->f.disable()).httpBasic(b->b.disable()).build();
 }
}
