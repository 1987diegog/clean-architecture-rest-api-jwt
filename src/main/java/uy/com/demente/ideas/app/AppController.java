package uy.com.demente.ideas.app;

import uy.com.demente.ideas.shared.constanst.HttpCodeConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppController.ROOT_PATH)
@CrossOrigin(origins = "*", allowedHeaders = "*")
class AppController {

    private Logger log = LoggerFactory.getLogger(AppController.class);

    public static final String API_VERSION = "/v1";
    public static final String RESOURCE = "/app";
    public static final String ROOT_PATH = API_VERSION + RESOURCE;

    @Value("${application.name}")
    private String name;

    @Value("${build.version}")
    private String buildVersion;

    @Value("${build.timestamp}")
    private String buildTimestamp;

    private String activeProfile;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retorna los datos del build actual de la aplicacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodeConstants.OK, description = "Datos devueltos exitosamente"),
            @ApiResponse(responseCode = HttpCodeConstants.BAD_REQUEST, description = "Error de datos"),
            @ApiResponse(responseCode = HttpCodeConstants.UNAUTHORIZED, description = "Error de autentificacion, token invalido"),
            @ApiResponse(responseCode = HttpCodeConstants.INTERNAL_SERVER_ERROR, description = "Error interno del servidor")})
    ResponseEntity<DataApplicationBuild> getApplicationData() {

        DataApplicationBuild dataApplicationBuild = DataApplicationBuild.builder()
                .name(name)
                .buildVersion(buildVersion)
                .buildTimestamp(buildTimestamp)
                .build();

        return new ResponseEntity<>(dataApplicationBuild, HttpStatus.OK);
    }
}
