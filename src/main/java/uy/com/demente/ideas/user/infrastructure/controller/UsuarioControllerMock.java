package uy.com.demente.ideas.user.infrastructure.controller;

import uy.com.demente.ideas.transversal.handler.exception.BussinesServiceException;
import uy.com.demente.ideas.transversal.handler.exception.InternalServerErrorException;
import uy.com.demente.ideas.user.domain.model.enums.Sexo;
import uy.com.demente.ideas.user.domain.model.enums.TipoDocumento;
import uy.com.demente.ideas.user.infrastructure.dto.mock.ContactMockDTO;
import uy.com.demente.ideas.user.infrastructure.dto.mock.CredentialMockDTO;
import uy.com.demente.ideas.user.infrastructure.dto.mock.PersonalMockDTO;
import uy.com.demente.ideas.user.infrastructure.dto.mock.UserMockDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(UsuarioControllerMock.ROOT_PATH)
class UsuarioControllerMock {

    private Logger log = LoggerFactory.getLogger(UsuarioControllerMock.class);

    public static final String API_VERSION = "/v1";
    public static final String RESOURCE = "/users";
    public static final String ROOT_PATH = API_VERSION + RESOURCE;

    public static final String MOCK = "/mock";

    @PostMapping(path = MOCK,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Registra un nuevo usuario en el sistema de cuentas")
    @ApiResponses(value = { //
            @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente"), //
            @ApiResponse(responseCode = "400", description = "Error de negocio controlado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")})
    public ResponseEntity<UserMockDTO> registrar(@RequestBody UserMockDTO registro) throws Exception {


        if (registro.getDatosContacto().getCelular().equals("0")) {
            throw new BussinesServiceException("Celular incorrecto");
        }

        //Tarea de prueba
        if (registro.getDatosContacto().getCelular().equals("1")) {
            throw new Exception();
        }

        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    @GetMapping(path = MOCK,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retorna los datos de un usuario logueado")
    @ApiResponses(value = { //
            @ApiResponse(responseCode = "200", description = "Datos devueltos exitosamente"), //
            @ApiResponse(responseCode = "401", description = "Error de autenticacion, token invalido"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")})
    public UserMockDTO getDatos() throws InternalServerErrorException {

        // DTO
        UserMockDTO usuarioDTO = new UserMockDTO();

        // Datos contacto
        ContactMockDTO datosContacto = new ContactMockDTO("098515503", "crecoba@alabamasolutions.com");
        datosContacto.setLocalidad("Montevideo");
        usuarioDTO.setDatosContacto(datosContacto);

        // Datos personales
        PersonalMockDTO datosPersonales = new PersonalMockDTO("Alvaro Alexander", "Recoba Rivero",
                LocalDate.now(), Sexo.MASCULINO, "4.050.774-4", TipoDocumento.CON_CHIP, "A");
        usuarioDTO.setDatosPersonales(datosPersonales);

        // Datos usuario
        CredentialMockDTO datosCredenciales = new CredentialMockDTO("chino20", "password");
        usuarioDTO.setDatosCredenciales(datosCredenciales);
        return usuarioDTO;

    }

    @PutMapping(path = MOCK,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifica los datos de un usuario")
    @ApiResponses(value = { //
            @ApiResponse(responseCode = "200", description = "Datos modificados  exitosamente"), //
            @ApiResponse(responseCode = "400", description = "Error de negocio controlado"),
            @ApiResponse(responseCode = "401", description = "Error de autenticacion, token invalido"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")})
    public UserMockDTO modificarDatos(@RequestBody UserMockDTO datos) throws InternalServerErrorException {

        // DTO
        UserMockDTO usuarioDTO = new UserMockDTO();

        // Datos contacto
        ContactMockDTO datosContacto = new ContactMockDTO("098515503", "crecoba@alabamasolutions.com");
        usuarioDTO.setDatosContacto(datosContacto);

        // Datos personales
        PersonalMockDTO datosPersonales = new PersonalMockDTO("Alvaro Alexander", "Recoba Rivero",
                LocalDate.now(), Sexo.MASCULINO, "4.050.774-4", TipoDocumento.CON_CHIP, "A");
        usuarioDTO.setDatosPersonales(datosPersonales);

        // Datos usuario
        CredentialMockDTO datosCredenciales = new CredentialMockDTO("chino20", "password");
        usuarioDTO.setDatosCredenciales(datosCredenciales);

        return usuarioDTO;
    }

}
