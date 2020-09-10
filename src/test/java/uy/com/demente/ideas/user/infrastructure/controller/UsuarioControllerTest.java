package uy.com.demente.ideas.user.infrastructure.controller;

import uy.com.demente.ideas.ConfigTestRestController;
import uy.com.demente.ideas.shared.utils.RestCallEndpointBuilder;
import uy.com.demente.ideas.transversal.handler.exception.BadRequestException;
import uy.com.demente.ideas.transversal.handler.exception.InternalServerErrorException;
import uy.com.demente.ideas.user.domain.mocks.FactoryMockDomain;
import uy.com.demente.ideas.user.domain.model.Usuario;
import uy.com.demente.ideas.user.infrastructure.dto.UserDTO;
import uy.com.demente.ideas.user.infrastructure.mocks.FactoryMockDTO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
class UsuarioControllerTest extends ConfigTestRestController {

    private static final Logger logger =
            LoggerFactory.getLogger(UsuarioControllerTest.class);

    @Test
    void shouldSignInWhenCallPost() throws BadRequestException, InternalServerErrorException {

        // given
        Usuario returnMockUser = FactoryMockDomain.getUser();

        // when
        when(userClient.signIn(any(Usuario.class))).thenReturn(returnMockUser);

        // prepare url endpoint
        String url = getRootUrlWithPort() + UsuarioController.ROOT_PATH;
        UserDTO inputUserDTO = FactoryMockDTO.getUser();
        HttpEntity httpEntity = new HttpEntity<>(inputUserDTO, RestCallEndpointBuilder.getDefaultHttpHeaders());

        // execute
        ResponseEntity<UserDTO> response = restTemplate.exchange(url,
                HttpMethod.POST, httpEntity, UserDTO.class);

        assertTrue(response.getStatusCode().equals(HttpStatus.CREATED), "The http code is CREATED");
        logger.info("[RESPONSE-BODY] shouldSignInWhenCallPost " + response.getBody());

        assertNotNull(response.getBody(), "The response is not null");
        assertNotNull(response.getBody().getCredential(), "The Credentials data is not null");
        assertNotNull(response.getBody().getPersonal(), "The Personal data is not null");
        assertNotNull(response.getBody().getContact(), "The Contact data is not null");
    }
}