package uy.com.demente.ideas.transversal.security.filter;

import uy.com.demente.ideas.transversal.security.service.JWTService;
import uy.com.demente.ideas.transversal.security.service.JWTServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private JWTService jwtService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
                                  JWTService jwtService) {
        super(authenticationManager);
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(JWTServiceImpl.HEADER_STRING);

        if (!requiresAuthentication(header)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = null;

        if (jwtService.validate(header)) {
            authentication = new UsernamePasswordAuthenticationToken(
                    jwtService.getUsername(header), null, jwtService.getRoles(header));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    protected boolean requiresAuthentication(String header) {

        if (header == null || !header.startsWith(JWTServiceImpl.TOKEN_PREFIX)) {
            return false;
        }
        return true;
    }

    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest request,
                                                HttpServletResponse response,
                                                AuthenticationException failed) throws IOException {

        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", "Error de autorizacion: el usuarion no tiene los "
                + "permisos para acceder al recurso!");
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
