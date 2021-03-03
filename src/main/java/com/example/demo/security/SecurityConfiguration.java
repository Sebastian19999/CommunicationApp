package com.example.demo.security;

import static com.example.demo.security.PermissionEnum.COURSE_WRITE;
import static com.example.demo.security.RollesEnum.ADMIN;
import static com.example.demo.security.RollesEnum.ADMINTREINEE;
import static com.example.demo.security.RollesEnum.STUDENT;

import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.jwt.JwtConfig;
import com.example.demo.jwt.JwtTokenVerifier;
import com.example.demo.jwt.JwtUsernameAndPasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(99)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	private final PasswordEncoder passwordEncoder;
	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;
	
	@Autowired
	public SecurityConfiguration(PasswordEncoder passwordEncoder,JwtConfig jwtConfig,SecretKey secretKey) {
		this.passwordEncoder=passwordEncoder;
		this.jwtConfig=jwtConfig;
		this.secretKey=secretKey;
	}
	
	


	/*@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/loginBrowser");
	}*/




	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			//.and()
			//.addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(),jwtConfig,secretKey))
			//.addFilterAfter(new JwtTokenVerifier(jwtConfig,secretKey), JwtUsernameAndPasswordAuthenticationFilter.class)
			//.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			//.and()
			.authorizeRequests()
			.antMatchers("/","/home-api/**","css/*","js/*")
			.permitAll()
			.antMatchers("/api/**").hasRole(RollesEnum.STUDENT.name())
			.antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
			.antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
			.antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
			.antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ADMIN.name(),ADMINTREINEE.name())
			.anyRequest()
			.authenticated()
			.and()
			////.httpBasic();
			.formLogin().loginPage("/loginBrowser").permitAll()
			.defaultSuccessUrl("/index",true)
			.and()
			.rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
			.key("somethingverysecured")
			.and()
			.logout()
			.logoutUrl("/logout")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
			.clearAuthentication(true)
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID","remember-me")
			.logoutSuccessUrl("/loginBrowser");
			
			//.and()
			//.rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
					//.key("somethingverysecured");*/
		
		http
		.csrf().disable()
		//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		//.and()
		.addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(),jwtConfig,secretKey))
		.addFilterAfter(new JwtTokenVerifier(jwtConfig,secretKey), JwtUsernameAndPasswordAuthenticationFilter.class);
		//.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	
		
	}

	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails annaSmithUser=User.builder()
				.username("0742045678")
				.password(passwordEncoder.encode("password"))
				.authorities(STUDENT.getGrantedAuthorities())
				//.roles(STUDENT.name())
				.build();
		
		UserDetails lindaUser=User.builder()
				.username("0742038591")
				.password(passwordEncoder.encode("password"))
				.authorities(ADMIN.getGrantedAuthorities())
				//.roles(ADMIN.name())
				.build();
		
		UserDetails tomUser=User.builder()
				.username("0742123456")
				.password(passwordEncoder.encode("password"))
				.authorities(ADMINTREINEE.getGrantedAuthorities())
				//.roles(ADMINTREINEE.name())
				.build();
		
		
		return new InMemoryUserDetailsManager(
			annaSmithUser,
			lindaUser,
			tomUser
		);
		
		
		
		
	}
	
	
	
	/*@Bean
	public DaoAuthenticationProvider daoAuthentificationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(applicationUserService);
		return provider;
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthentificationProvider());
	}*/
	
	
	
	
	

}
