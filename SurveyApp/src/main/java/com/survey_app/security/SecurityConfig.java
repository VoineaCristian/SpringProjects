
package com.survey_app.security;



import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetail;

    private AuthenticationSuccessHandler authenticationSuccessHandler;
    
 
    @Autowired
    public void WebSecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    } 
    
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetail);

	}
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
	    return (request, response, ex) -> {
	        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

	        ServletOutputStream out = response.getOutputStream();
	        new ObjectMapper().writeValue(out, "Acces Denied!");
	        out.flush();
	    };
	}
	@Bean
	public AuthenticationFailureHandler failureHandler() {
	    return (request, response, ex) -> { throw ex; };
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/login").permitAll()
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/registration").permitAll()
				.antMatchers("/**").hasRole("USER")
				.anyRequest().authenticated()
				.and()
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler())
				.and()
				.httpBasic();

		  http.formLogin()
          .loginPage("/login").permitAll()
          .failureHandler(failureHandler())
          .successHandler(authenticationSuccessHandler)
        .failureUrl("/bad-credentials")
        .and()
          .logout().permitAll();
		  
		http.csrf().disable();
		http.headers().frameOptions().disable();

	}

}