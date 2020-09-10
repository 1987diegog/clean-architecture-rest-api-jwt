package uy.com.demente.ideas.user.application.mapper;

import org.mapstruct.*;
import uy.com.demente.ideas.user.domain.model.DatosPersonales;
import uy.com.demente.ideas.user.infrastructure.dto.PersonalDTO;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserPersonalMapper {

    @Mappings({
            @Mapping(source = "name", target = "nombres"),
            @Mapping(source = "lastname", target = "apellidos"),
            @Mapping(source = "birthday", target = "fechaNacimiento"),
            @Mapping(source = "document", target = "numeroDocumento"),
            @Mapping(source = "typeDocument", target = "tipoDocumento"),
            @Mapping(source = "serie", target = "serie"),
            @Mapping(source = "folioNumber", target = "numeroFolio")
    })
    DatosPersonales toDatosPersonales(PersonalDTO personalDTO);

    @InheritInverseConfiguration
    PersonalDTO toPersonalDTO(DatosPersonales datosPersonales);
}
