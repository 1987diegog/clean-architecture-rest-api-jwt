package uy.com.demente.ideas.user.infrastructure.client;

import uy.com.demente.ideas.shared.annotations.RestClient;
import uy.com.demente.ideas.shared.utils.RestCallEndpointBuilder;
import uy.com.demente.ideas.transversal.handler.exception.BadRequestException;
import uy.com.demente.ideas.transversal.handler.exception.InternalServerErrorException;
import uy.com.demente.ideas.transversal.handler.response.ExceptionDetails;
import uy.com.demente.ideas.user.domain.UsuarioClient;
import uy.com.demente.ideas.user.domain.model.MensajeRespuesta;
import uy.com.demente.ideas.user.domain.model.UserList;
import uy.com.demente.ideas.user.domain.model.Usuario;
import uy.com.demente.ideas.user.domain.model.enums.Sexo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestClient
class RestTemplateUsuarioClient implements UsuarioClient {

	private Logger log = LoggerFactory.getLogger(RestTemplateUsuarioClient.class);

	private final RestTemplate restTemplate;

	public RestTemplateUsuarioClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value("${restclient.bqm.base-url}")
	private String endpoint;

	@Value("${restclient.bqm.users.path-user}")
	private String pathUsers;

	@Value("${restclient.bqm.users.path-user-identifier}")
	private String pathUserIdentifier;

	public Usuario signIn(Usuario user) throws BadRequestException, InternalServerErrorException {
		try {
			String url = endpoint + pathUsers;
			HttpEntity<Usuario> httpEntity = new HttpEntity<>(user, RestCallEndpointBuilder.getDefaultHttpHeaders());
			ResponseEntity<MensajeRespuesta> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
					MensajeRespuesta.class);
			log.info("Respuesta BQM", response.getBody().getMensajeRespuesta());
			return user;
		} catch (HttpClientErrorException e) {
			log.error("HttpClientErrorException!!", e);
			ExceptionDetails exceptionDetails = getExceptionDetails(e.getMessage());
			throw new BadRequestException(exceptionDetails, e);
		} catch (HttpServerErrorException e) {
			e.printStackTrace();
			log.error("HttpServerErrorException!!", e);
			ExceptionDetails exceptionDetails = getExceptionDetails(e.getMessage());
			throw new InternalServerErrorException(exceptionDetails, e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error generico!!", e);
			ExceptionDetails exceptionDetails = getExceptionDetails(e.getMessage());
			throw new InternalServerErrorException(exceptionDetails, e);
		}
	}

	public UserList getUsers() throws BadRequestException, InternalServerErrorException {
		try {

			MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
			queryParams.add("sexo", Sexo.FEMENINO.name());
			queryParams.add("betBySMS", "true");

			String url = RestCallEndpointBuilder.builderWhitOnlyQueryParams(endpoint + pathUsers, queryParams);
			HttpEntity httpEntity = new HttpEntity<>(RestCallEndpointBuilder.getDefaultHttpHeaders());
			ResponseEntity<UserList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, UserList.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			log.error("HttpClientErrorException!!", e);
			String messageCauseError = "ERROR BQM - Bad Request";
			ExceptionDetails exceptionDetails = getExceptionDetails(messageCauseError);
			throw new BadRequestException(exceptionDetails, e);
		} catch (HttpServerErrorException e) {
			log.error("HttpServerErrorException!!", e);
			String messageCauseError = "ERROR BQM - Internal Server Error";
			ExceptionDetails exceptionDetails = getExceptionDetails(messageCauseError);
			throw new InternalServerErrorException(exceptionDetails, e);
		} catch (Exception e) {
			log.error("Error generico!!", e);
			String messageCauseError = "ERROR ALABAMA - Internal Server Error";
			ExceptionDetails exceptionDetails = getExceptionDetails(messageCauseError);
			throw new InternalServerErrorException(exceptionDetails, e);
		}
	}

	public Usuario findUserById(Long idUser) throws BadRequestException, InternalServerErrorException {
		try {
			Map<String, String> urlParams = new HashMap<>();
			urlParams.put("idUser", String.valueOf(idUser));
			String url = RestCallEndpointBuilder.builderWhitOnlyPathParams(endpoint + pathUserIdentifier, urlParams);
			HttpEntity httpEntity = new HttpEntity<>(RestCallEndpointBuilder.getDefaultHttpHeaders());
			ResponseEntity<Usuario> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Usuario.class);
			return response.getBody();

		} catch (HttpClientErrorException e) {
			log.error("HttpClientErrorException!!", e);
			ExceptionDetails exceptionDetails = getExceptionDetails("ERROR BQM - Bad Request");
			throw new BadRequestException(exceptionDetails, e);
		} catch (HttpServerErrorException e) {
			log.error("HttpServerErrorException!!", e);
			ExceptionDetails exceptionDetails = getExceptionDetails("ERROR BQM - Internal Server Error");
			throw new InternalServerErrorException(exceptionDetails, e);
		} catch (Exception e) {
			log.error("Error generico!!", e);
			ExceptionDetails exceptionDetails = getExceptionDetails("ERROR ALABAMA - Internal Server Error");
			throw new InternalServerErrorException(exceptionDetails, e);
		}
	}

	public Usuario update(Long idUser, Usuario usuario) throws BadRequestException, InternalServerErrorException {
		try {
			Map<String, String> urlParams = new HashMap<>();
			urlParams.put("idUser", String.valueOf(idUser));
			String url = RestCallEndpointBuilder.builderWhitOnlyPathParams(endpoint + pathUserIdentifier, urlParams);
			HttpEntity<Usuario> httpEntity = new HttpEntity<>(usuario, RestCallEndpointBuilder.getDefaultHttpHeaders());
			ResponseEntity<Usuario> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Usuario.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			log.error("HttpClientErrorException!!", e);
			ExceptionDetails exceptionDetails = getExceptionDetails("ERROR BQM - Bad Request");
			throw new BadRequestException(exceptionDetails, e);
		} catch (HttpServerErrorException e) {
			log.error("HttpServerErrorException!!", e);
			ExceptionDetails exceptionDetails = getExceptionDetails("ERROR BQM - Internal Server Error");
			throw new InternalServerErrorException(exceptionDetails, e);
		} catch (Exception e) {
			log.error("Error generico!!", e);
			ExceptionDetails exceptionDetails = getExceptionDetails("ERROR ALABAMA - Internal Server Error");
			throw new InternalServerErrorException(exceptionDetails, e);
		}
	}

	private ExceptionDetails getExceptionDetails(String messageCauseError) {
		return ExceptionDetails.builder().cause(messageCauseError).build();
	}

}