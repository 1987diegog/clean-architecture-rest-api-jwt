package uy.com.demente.ideas.user.application.mapper;

import org.mapstruct.*;
import uy.com.demente.ideas.user.domain.model.DatosContacto;
import uy.com.demente.ideas.user.domain.model.UserList;
import uy.com.demente.ideas.user.infrastructure.dto.ContactDTO;
import uy.com.demente.ideas.user.infrastructure.dto.UserListDTO;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserContactMapper {

    @Mappings({
            @Mapping(source = "cellphone", target = "celular"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "location", target = "departamentoResidencia"),
            @Mapping(source = "betBySMS", target = "apostarPorSms"),
            @Mapping(source = "receiveEmails", target = "recibeEmail")
    })
    DatosContacto toDatosContacto(ContactDTO contactDTO);

    @InheritInverseConfiguration
    ContactDTO toContactDTO(DatosContacto datosContacto);
}
