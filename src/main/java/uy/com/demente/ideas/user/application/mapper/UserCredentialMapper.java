package uy.com.demente.ideas.user.application.mapper;

import org.mapstruct.*;
import uy.com.demente.ideas.user.domain.model.DatosCredenciales;
import uy.com.demente.ideas.user.domain.model.DatosPersonales;
import uy.com.demente.ideas.user.infrastructure.dto.CredentialDTO;
import uy.com.demente.ideas.user.infrastructure.dto.PersonalDTO;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserCredentialMapper {

    @Mappings({
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "password", target = "password")
    })
    DatosCredenciales toDatosCredenciales(CredentialDTO credentialDTO);

    @InheritInverseConfiguration
    CredentialDTO toCredentialDTO(DatosCredenciales datosCredenciales);



}
