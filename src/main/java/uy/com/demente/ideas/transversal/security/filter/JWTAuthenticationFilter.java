package uy.com.demente.ideas.transversal.security.filter;

import uy.com.demente.ideas.transversal.security.service.JWTService;
import uy.com.demente.ideas.transversal.security.service.JWTServiceImpl;
import uy.com.demente.ideas.user.domain.model.DatosCredenciales;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Filtro configurado en security config por eje
 * [addFilter(new JWTAuthenticationFilter(authenticationManager()))]
 * Previo se debe configurar el userDetailsService que utilizara el authentication manager,
 * por ejemplo [AuthenticationManagerBuilder.userDetailsService(jpaUserDetailsService)]
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String API_VERSION = "/v1";
    public static final String ROOT_PATH = "/login";

    private AuthenticationManager authenticationManager;
    private JWTService jwtService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
                                   JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;

        setRequiresAuthenticationRequestMatcher(
                new AntPathRequestMatcher(API_VERSION + ROOT_PATH,
                        RequestMethod.POST.name()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        String token = jwtService.create(authResult);

        response.addHeader(JWTServiceImpl.HEADER_STRING, JWTServiceImpl.TOKEN_PREFIX + token);

        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        body.put("user", authResult.getPrincipal());
        body.put("mensaje", String.format("Hola %s, has iniciado sesión con éxito!",
                ((User) authResult.getPrincipal()).getUsername()));

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException {

        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", "Error de autenticación: username o password incorrecto!");
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * Realiza la autentificacion mediante un user y password,
     * para lograrlo utiliza el AuthenticationManager
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {

        String username = this.obtainUsername(request);
        String password = this.obtainPassword(request);

        if (username != null && password != null) {
            logger.info("Username desde request parameter (form-data): " + username);
            logger.info("Password desde request parameter (form-data): " + password);
        } else {
            try {
                DatosCredenciales userCredential = new ObjectMapper()
                        .readValue(request.getInputStream(), DatosCredenciales.class);
                username = userCredential.getUsername();
                password = userCredential.getPassword();
                logger.info("Username desde request InputStream (raw): " + username);
                logger.info("Password desde request InputStream (raw): " + password);
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        username.trim();
        password.trim();

        if (username != null && password != null) {
            logger.info("Username desde request parameter (form-data): " + username);
            logger.info("Password desde request parameter (form-data): " + password);
        }

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(username, password);

        // para realizar la autentificacion el AuthenticationManager utiliza por debajo
        // la clase UserDetailsService la misma puede ser sobreescrita indicando el mecanismo
        // de logeo, por ejemplo en memoria, bd, etc.
        return authenticationManager.authenticate(authToken);
    }
}
