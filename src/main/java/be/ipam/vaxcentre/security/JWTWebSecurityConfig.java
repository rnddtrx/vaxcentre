package be.ipam.vaxcentre.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class JWTWebSecurityConfig {

    //USER DETAIL SERVICE
	@Autowired
    private JwtInMemoryUserDetailsService uds;
	
	//BCrypt
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	//1. Publish a SecurityFilterChain Bean
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    	 httpSecurity
    	 //.exceptionHandling().authenticationEntryPoint(jwtUnAuthorizedResponseAuthenticationEntryPoint).and()
         //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
    	 .authorizeHttpRequests()
         .requestMatchers("/api/persons/register").permitAll()
         .requestMatchers("/api/authenticate").permitAll()
         .anyRequest().authenticated()
         .and()
         //.formLogin()
         //.and()
         .csrf().disable();
    	
    	
    	 httpSecurity
            .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); 

       
       return httpSecurity.build();  
    }
    
//    //2. Publish a WebSecurityCustomizer Bean
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web)-> web
//            .ignoring()
//            .requestMatchers(
//                HttpMethod.POST,
//                authenticationPath
//            )
//            .requestMatchers(HttpMethod.OPTIONS, "/**")
//            .and()
//            .ignoring()
//            .requestMatchers(
//                HttpMethod.POST,
//                "api/persons/register"
//            )
//            .requestMatchers(HttpMethod.OPTIONS, "/**")
//            .and()
//            .ignoring()
//            .requestMatchers(
//                HttpMethod.GET,
//                "/index.html" //Other Stuff You want to Ignore
//            );
//    }
    
    //3. AuthenticationManager bean
    @Bean
    public AuthenticationProvider  authenticationProvider() {
    	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(uds);
		authenticationProvider.setPasswordEncoder(encoder);
		return authenticationProvider;
    }
            
	@Autowired
    private JwtUnAuthorizedResponseAuthenticationEntryPoint jwtUnAuthorizedResponseAuthenticationEntryPoint;

    @Autowired
    private JwtTokenAuthorizationOncePerRequestFilter jwtAuthenticationTokenFilter;

    @Value("${jwt.get.token.uri}")
    private String authenticationPath;

}

