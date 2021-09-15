package com.cognixia.jump.springcloud.config;

import com.cognixia.jump.springcloud.filters.JwtRequestFilter;
import com.cognixia.jump.springcloud.service.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity      // enables Spring Security's web security support and provide the Spring MVC integration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    MyUserDetailsService service;

    @Autowired
    JwtRequestFilter filter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service);
    }

    // defines which URL paths should be secured and which should not
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CSRF (Cross-Site Request Forgery)
        // Recommended to use for requests that could be processed by a browser by normal users
        // If only creating a service that is used by non-browser clients, will likely want to disable CSRF protection
        http.csrf().disable()

            // TODO: Need to configure request authorizations with endpoints

            .authorizeRequests()
                // configures /authenticate to not require any authentication
                .antMatchers("/authenticate", "/api/member/**", "/api/add/member", "/api/restaurants", "/api/restaurants/name/**", "/api/restaurants/*", "/api/reviews", "/api/reviews/**").permitAll()
                // http method options does not required any authentication
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/api/add/review").hasAuthority("ROLE_USER")
                .antMatchers("/api/update/restaurant").hasAuthority("ROLE_ADMIN")
                // all other paths must be authenticated
                .anyRequest().authenticated()
                .and()
            // when a user successfully logs in, they are redirected to the previously requested page that required authentication
            // custom /login page (specified by loginPage()) and everyone is allowed to view it
            // .formLogin()
            //      .loginPage("/login")
            //      .permitAll()
            //      .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
