package uy.com.demente.ideas.user.infrastructure.client;

import uy.com.demente.ideas.user.domain.mocks.FactoryMockDomain;
import uy.com.demente.ideas.user.domain.model.Role;
import uy.com.demente.ideas.user.domain.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestTemplateUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(RestTemplateUserDetailsService.class);

    @Override
    //@Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //clientRepository.findByUsername(username);
        Usuario usuario = FactoryMockDomain.getUser();

        if (usuario == null) {
            logger.error("Error login: Client not exist: " + username);
            throw new UsernameNotFoundException("Client not exist: " + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : usuario.getRoles()) {
            logger.info("Role:".concat(role.getAuthority()));
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }


        if (authorities.isEmpty()) {
            logger.error("Error login: User " + username + ", dont have any roles");
            throw new UsernameNotFoundException("User " + username + ", dont have any roles");
        }

        return new User(
                usuario.getDatosCredenciales().getUsername(),
                usuario.getDatosCredenciales().getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
