package com.ftn.Taverna.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(
            AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {

        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean()
            throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter
                .setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.headers().cacheControl().disable();
        httpSecurity.cors();
        httpSecurity.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/login").permitAll()
                .antMatchers(HttpMethod.POST, "/korisnici/register-prodavac").permitAll()
                .antMatchers(HttpMethod.POST, "/korisnici/register-kupac").permitAll()
                .antMatchers(HttpMethod.POST, "/elastic/naziv").permitAll()
                .antMatchers(HttpMethod.POST, "/elastic/tekst-komentara").permitAll()
                .antMatchers(HttpMethod.POST, "/elastic/price").permitAll()
                .antMatchers(HttpMethod.POST, "/elastic/rating").permitAll()
                .antMatchers(HttpMethod.POST, "/elastic/naziv-cena").permitAll()
                .antMatchers(HttpMethod.POST, "/elastic/komentar-ocena").permitAll()
                .antMatchers(HttpMethod.POST, "/elastic/rating-artikla").permitAll()
                .antMatchers(HttpMethod.POST, "/elastic/komentari-artikla").permitAll()
                .antMatchers(HttpMethod.POST, "/elastic/ukupna-cena").permitAll()

                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }


}
