package com.project.MyApp.Security;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.MyApp.entity.EmailToken;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	 @Autowired
	  private DataSource dataSource;
	 
	 @Bean
		public BCryptPasswordEncoder encoder() {
		    return new BCryptPasswordEncoder(10);
		}
	RedirectLoginSuccessHandler redirect = new RedirectLoginSuccessHandler();
	 public class RedirectLoginSuccessHandler implements AuthenticationSuccessHandler {

		    @Override
		    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, 
		            HttpServletResponse httpServletResponse, 
		            Authentication authentication) throws IOException, ServletException {

		        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, 
		            authentication.getName());
		    }
		}
	 
	 
	  @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  
	    auth.jdbcAuthentication().dataSource(dataSource)
	    .usersByUsernameQuery("select user.username, user.password, user.enabled " 
	    		+ "from user where user.username=?").
	    authoritiesByUsernameQuery("select user.username, user.authority " 
	    		+ "from user where user.username=?")
	    .passwordEncoder(encoder());
	  }

	 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	EmailToken token = new EmailToken();
    	
      

    	http.csrf().disable();
    	http.authorizeRequests()
		.antMatchers("/").authenticated()
		.and()
		.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/aU")
			.successHandler(redirect)
			.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied");
	
    }

  
}