package uy.com.demente.ideas.user.infrastructure.controller;

import uy.com.demente.ideas.shared.constanst.HttpCodeConstants;
import uy.com.demente.ideas.transversal.handler.exception.BadRequestException;
import uy.com.demente.ideas.transversal.handler.exception.InternalServerErrorException;
import uy.com.demente.ideas.user.application.UserService;
import uy.com.demente.ideas.user.infrastructure.dto.UserDTO;
import uy.com.demente.ideas.user.infrastructure.dto.UserListDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(UsuarioController.ROOT_PATH)
@CrossOrigin(origins = "*", allowedHeaders = "*")
class UsuarioController {

    private Logger log = LoggerFactory.getLogger(UsuarioController.class);

    public static final String API_VERSION = "/v1";
    public static final String RESOURCE = "/users";
    public static final String ROOT_PATH = API_VERSION + RESOURCE;
    public static final String BY_ID = "/{idUser}";

    private final UserService userService;

    public UsuarioController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Registra un nuevo usuario en el sistema de cuentas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodeConstants.CREATED, description = "Usuario registrado exitosamente"),
            @ApiResponse(responseCode = HttpCodeConstants.BAD_REQUEST, description = "Error de datos"),
            @ApiResponse(responseCode = HttpCodeConstants.BAD_REQUEST, description = "Error de negocio controlado"),
            @ApiResponse(responseCode = HttpCodeConstants.INTERNAL_SERVER_ERROR, description = "Error interno del servidor")})
    public ResponseEntity<UserDTO> signIn(@Valid @RequestBody UserDTO user) throws Exception {
        UserDTO usuarioCreado = userService.signIn(user);
        this.userService.signIn(user);
        return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
    }

    @GetMapping(path = BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retorna los datos de un usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodeConstants.OK, description = "Datos devueltos exitosamente"),
            @ApiResponse(responseCode = HttpCodeConstants.BAD_REQUEST, description = "Error de datos"),
            @ApiResponse(responseCode = HttpCodeConstants.UNAUTHORIZED, description = "Error de autentificacion, token invalido"),
            @ApiResponse(responseCode = HttpCodeConstants.INTERNAL_SERVER_ERROR, description = "Error interno del servidor")})
    public ResponseEntity<UserDTO> getUserById(@PathVariable("idUser") final Long idUser)
            throws InternalServerErrorException, BadRequestException {
        UserDTO usuarioCreado = userService.findUserById(idUser);
        return new ResponseEntity<>(usuarioCreado, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retorna una lista de usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodeConstants.OK, description = "Datos devueltos exitosamente"),
            @ApiResponse(responseCode = HttpCodeConstants.BAD_REQUEST, description = "Error de datos"),
            @ApiResponse(responseCode = HttpCodeConstants.UNAUTHORIZED, description = "Error de autentificacion, token invalido"),
            @ApiResponse(responseCode = HttpCodeConstants.INTERNAL_SERVER_ERROR, description = "Error interno del servidor")})
    public ResponseEntity<UserListDTO> getUsers() throws InternalServerErrorException, BadRequestException {
        UserListDTO userList = userService.getUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PutMapping(path = BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifica los datos de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodeConstants.OK, description = "Datos modificados exitosamente"),
            @ApiResponse(responseCode = HttpCodeConstants.BAD_REQUEST, description = "Error de datos"),
            @ApiResponse(responseCode = HttpCodeConstants.UNAUTHORIZED, description = "Error de autentificacion, token invalido"),
            @ApiResponse(responseCode = HttpCodeConstants.INTERNAL_SERVER_ERROR, description = "Error interno del servidor")})
    public ResponseEntity<UserDTO> update(@PathVariable("idUser") final Long idUser, @RequestBody UserDTO user)
            throws InternalServerErrorException, BadRequestException {
        UserDTO usuarioUpdated = userService.update(idUser, user);
        return new ResponseEntity<>(usuarioUpdated, HttpStatus.OK);
    }

}
