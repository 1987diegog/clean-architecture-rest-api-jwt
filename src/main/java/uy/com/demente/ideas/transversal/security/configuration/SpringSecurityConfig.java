package uy.com.demente.ideas.transversal.security.configuration;

import uy.com.demente.ideas.transversal.security.filter.JWTAuthenticationFilter;
import uy.com.demente.ideas.transversal.security.filter.JWTAuthorizationFilter;
import uy.com.demente.ideas.transversal.security.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    private final JWTService jwtService;

    public SpringSecurityConfig(BCryptPasswordEncoder passwordEncoder,
                                UserDetailsService userDetailsService,
                                JWTService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // H2-console config
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
        http.headers().frameOptions().disable();

        http.authorizeRequests().antMatchers(
                "/v3/api-docs/**",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/webjars/**").permitAll();


        ////////////////// <- Securizar todas las APIs menos POST Users -> //////////////////
        // ---> remove: .requestMatchers(req -> req.getRequestURI().contains("mock")).permitAll()
        // ---> Add: UsuarioController.ROOT_PATH + UsuarioController.PATH_USERS + "/mock").permitAll()
        http.authorizeRequests()
                .requestMatchers(
                        req -> req.getRequestURI().contains("mock"),
                        req -> req.getRequestURI().contains("users") && req.getMethod().equals("POST")).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {

        // se configura la forma de acceder a los usuarios (userByUsername) y el encoder de
        // la password de los mismos.
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

    }
}
