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
                        //Category
                        .requestMatchers(HttpMethod.GET,"api/categories").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/categories/insertCategory").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/categories/updateCategory/{categoryId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/categories/deleteCategory/{categoryId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/categories/getCategoryByID/{categoryId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/categories/names").permitAll()

                        //Course
                        .requestMatchers(HttpMethod.GET,"/api/courses").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/courses/insertCourse").authenticated()
                        .requestMatchers(HttpMethod.PUT,"/api/courses/updateCourse/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/courses/deleteCourse/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/courses/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/courses/creator/{creatorId}").permitAll()
                        .requestMatchers(HttpMethod.GET,"api/courses/category/{categoryId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/categories/getCategoryById/**").permitAll()


//                  //Enrollments
                        .requestMatchers(HttpMethod.POST, "/api/enrollments/insert").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/enrollments/insert/{userId}/{courseId}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/enrollments").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/enrollments/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/enrollments/learner/{learnerId}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/enrollments/course/{courseId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/enrollments/{id}").hasRole("ADMIN")


                        //Transactions
                        .requestMatchers(HttpMethod.GET, "/api/transactions").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/transactions/insert").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/transactions/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/transactions/user/{userId}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/transactions/course/{courseId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/transactions").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/transactions/{id}").hasRole("ADMIN")


                        //User
                        .requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/byEmail").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/users/updateXP").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/users/{id}/xp").authenticated()

                        .requestMatchers(HttpMethod.GET,"/me").permitAll()


                        // Login
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()

//                        .anyRequest().permitAll()
        );

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

        config.setAllowedOriginPatterns(List.of("*")); // Ensure correct frontend URL
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true); // Must match frontend requests

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
