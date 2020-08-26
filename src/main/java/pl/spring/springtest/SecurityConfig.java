package pl.spring.springtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.spring.springtest.component.CustomDaoProvider;
import pl.spring.springtest.service.impl.JpaUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
JpaUserDetailsService userDetailsService;
@Autowired
CustomDaoProvider authenticationProvider;

@Autowired
public SecurityConfig(JpaUserDetailsService userDetailsService){
this.userDetailsService = userDetailsService;


}


//Tworzenia menadżera auth.
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.userDetailsService(userDetailsService);
auth.authenticationProvider(authenticationProvider);




}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/css/**").permitAll()
				.antMatchers("/graphic/**").permitAll()
				.antMatchers("/templates/**").permitAll()
				.antMatchers("/js/**").permitAll()
                .antMatchers("/sign_up").permitAll()
				.antMatchers("/about_me").permitAll()
                .antMatchers("/contact").permitAll()
				.antMatchers("/index").permitAll()
				.antMatchers("/companyall").permitAll()
				.antMatchers("/confirm_email").permitAll()
				.antMatchers("/admin_panel")
				.hasAuthority("ADMIN")
			.anyRequest().hasAnyAuthority("USER")

			.and()
		.formLogin()
			.loginPage("/login")
			.usernameParameter("foo")
			.passwordParameter("bar")
				.defaultSuccessUrl("/user_panel",true)
			.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/index");


	}
	//Tylko do testowania aplikacji
//	@SuppressWarnings("deprecation")
//	@Bean
//	public static NoOpPasswordEncoder passwordEncoder (){
//	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//	}

	@Bean
	public PasswordEncoder passwordEncoder(){
	return new BCryptPasswordEncoder();
	}
	
}
