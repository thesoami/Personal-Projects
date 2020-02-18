package com.company.SoamiCohlyU1Capstone;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception{

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        authBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery(
                        "select username, authority from authorities where username = ?")
                .passwordEncoder(encoder);
    }

    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .httpBasic();

        /*


        httpSecurity
                .csrf().disable;
                //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

         */

        httpSecurity.authorizeRequests()
//                .mvcMatchers("/login").authenticated()

                .mvcMatchers(HttpMethod.PUT, "/tshirt/*","/game/*","/console/*").hasAnyAuthority("STAFF","MANAGER","ADMIN")
                .mvcMatchers(HttpMethod.DELETE,"/tshirt/*","/game/*","/console/*").hasAuthority("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/tshirt","/game","/console").hasAnyAuthority("MANAGER","ADMIN")






//                .mvcMatchers(HttpMethod.PUT, "/tshirt/*","/game/*","/console/*").hasAnyAuthority("STAFF","MANAGER","ADMIN")
//                .mvcMatchers(HttpMethod.DELETE,"/tshirt/*","/game/*","/console/*").hasAuthority("ADMIN")
//                .mvcMatchers(HttpMethod.POST,"/tshirt","/game","/console").hasAnyAuthority("MANAGER","ADMIN")





                .anyRequest().permitAll();


        httpSecurity
                .logout()
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .deleteCookies("XSRF-TOKEN")
                .invalidateHttpSession(true);

        httpSecurity
                .csrf()
//                .csrf().disable();
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }


}

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import javax.sql.DataSource;
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    DataSource dataSource;
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
//
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        authBuilder.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select username, password, enabled from users where username = ?")
//                .authoritiesByUsernameQuery(
//                        "select username, authority from authorities where username = ?")
//                .passwordEncoder(encoder);
////        System.out.println(authBuilder.getDefaultUserDetailsService().loadUserByUsername("adminUser"));
//    }
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.httpBasic();
//
//        httpSecurity.authorizeRequests()
//                .mvcMatchers("/loggedin").authenticated()
//                .mvcMatchers(HttpMethod.POST, "/invoice").hasAuthority("STAFF")
//                .mvcMatchers(HttpMethod.PUT, "/console").hasAuthority("STAFF")
//                .mvcMatchers(HttpMethod.PUT, "/game").hasAuthority("STAFF")
//                .mvcMatchers(HttpMethod.PUT, "/tShirt").hasAuthority("STAFF")
//                .mvcMatchers(HttpMethod.POST, "/console").hasAuthority("MANAGER")
//                .mvcMatchers(HttpMethod.POST, "/game").hasAuthority("MANAGER")
//                .mvcMatchers(HttpMethod.POST, "/tShirt").hasAuthority("MANAGER")
//                .mvcMatchers(HttpMethod.DELETE, "/console/{id}").hasAuthority("ADMIN")
//                .mvcMatchers(HttpMethod.DELETE, "/game/{id}").hasAuthority("ADMIN")
//                .mvcMatchers(HttpMethod.DELETE, "/tShirt/{id}").hasAuthority("ADMIN")
//                .anyRequest().permitAll();
//
//        httpSecurity
//                .logout()
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/allDone")
//                .deleteCookies("JSESSIONID")
//                .deleteCookies("XSRF-TOKEN")
//                .invalidateHttpSession(true);
//        httpSecurity
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//    }
//}