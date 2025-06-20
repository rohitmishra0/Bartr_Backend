package com.bartr.security;

import com.bartr.filter.JwtFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    // main filter setting the domain to authorize and urls to check for authentication/jwt token
    @Bean
    public SecurityFilterChain customSecurityFilterChain(HttpSecurity httpSec) throws Exception {
        httpSec.csrf(AbstractHttpConfigurer::disable);
        httpSec.authorizeHttpRequests(req -> req
                //permit all with/without all
                .requestMatchers(HttpMethod.GET,"api/categories").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/categories/getCategoryById/**").permitAll()
                .requestMatchers(HttpMethod.GET,"api/courses").permitAll()
                .requestMatchers(HttpMethod.GET,"api/courses/creator/{creatorId}").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/categories/category/{categoryId}").permitAll()
                .requestMatchers(HttpMethod.POST,"api/users/register", "login").permitAll()
                .requestMatchers(HttpMethod.GET,"api/users/register/byEmail").permitAll()


                // Admin-only endpoints
                .requestMatchers(HttpMethod.POST, "/api/categories/insertCategory").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/categories/updateCategory/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/categories/deleteCategory/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/enrollments/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/categories/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/categories/updateCourse/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/categories/updateCourse/{creatorId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/enrollments/").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/enrollments/insertEnrollment").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/enrollments/learner/{learnerId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/enrollments/course/{courseId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/enrollments/deleteEnrollment").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/transactions").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/transactions/insert").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/transactions/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/transactions/course/{courseId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")


                //Authenticated Person
                .requestMatchers(HttpMethod.DELETE, "/api/transactions/deleteTransaction/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/categories/names").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/courses/insertCourse").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/courses/{id}").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/enrollments/{id}").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/transactions/user/{userId}").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/users/updateXP").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/users/{id}/xp").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/enrollments/insert/{userId}/{courseId}").authenticated()
//                        .anyRequest().permitAll()
        );


        // Temporarly Permiting everyone
//                .anyRequest().permitAll());
        httpSec.cors(Customizer.withDefaults());
        httpSec.httpBasic(Customizer.withDefaults());
        httpSec.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSec.build();
    }

    // for jwt internal use
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // to encode and save password in db
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // filter to allow your frontend to communicate with backend
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:5173")); // Ensure correct frontend URL
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true); // Must match frontend requests

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
