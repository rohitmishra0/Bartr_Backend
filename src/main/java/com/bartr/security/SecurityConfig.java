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
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
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
                        .requestMatchers(HttpMethod.GET,"/api/categories").permitAll()
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
                        .requestMatchers(HttpMethod.GET,"/api/courses/category/{categoryId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/categories/getCategoryById/**").permitAll()


//                  //Enrollments
                        .requestMatchers(HttpMethod.GET, "/api/enrollments/isEnrolled").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/enrollments/insert").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/enrollments/insert/{userId}/{courseId}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/enrollments").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/enrollments/{learnerId}/courses").authenticated()
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

                        //Payment
                        .requestMatchers(HttpMethod.GET,"/api/payments/price").authenticated()
                        .requestMatchers(HttpMethod.GET,"/api/payments/buy-xp").authenticated()
                        .requestMatchers(HttpMethod.GET,"/api/payments/user/{userId}").authenticated()

                        //User
                        .requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/users/update/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/users/byEmail").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/users/updateXP").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/users/{id}/xp").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/users/byUsername").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/users/changePassword/{userId}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/users/{userId}").authenticated()

                        .requestMatchers(HttpMethod.GET, "/api/search").permitAll()


                        .requestMatchers(HttpMethod.GET,"/me").permitAll()


                        // Login
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()

//                        .anyRequest().permitAll()
        );


        httpSec.cors(cors -> cors.configurationSource(corsConfigurationSource()));



        //httpSec.cors(Customizer.withDefaults());
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



    /**
     * Configures the CORS (Cross-Origin Resource Sharing) policy.
     * This allows web applications from other origins (e.g., a frontend running on localhost:4200)
     * to make requests to this API.
     *
     * @return A CorsConfigurationSource bean.
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Allow requests from the Angular frontend development server.
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        // Allow common HTTP methods.
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        // Allow all headers to be sent in requests.
        configuration.setAllowedHeaders(List.of("*"));
        // Allow sending credentials (like cookies or HTTP authentication headers, though JWT is typically in Authorization header).
        configuration.setAllowCredentials(true);
        // Expose the "Authorization" header to the client, which is needed to read the JWT.
        configuration.setExposedHeaders(List.of("Authorization"));

        // Apply this CORS configuration to all incoming paths.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }





//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//
//        config.setAllowedOriginPatterns(List.of("*")); // Ensure correct frontend URL
//        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
//        config.setAllowedHeaders(List.of("*"));
//        config.setAllowCredentials(true); // Must match frontend requests
//
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
}
