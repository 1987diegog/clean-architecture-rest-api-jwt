package uy.com.demente.ideas.user.application.mapper;

import org.mapstruct.*;
import uy.com.demente.ideas.user.domain.model.*;
import uy.com.demente.ideas.user.infrastructure.dto.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UserContactMapper.class,
        UserCredentialMapper.class, UserPersonalMapper.class})
public interface UserMapper {

    @Mappings({
            @Mapping(source = "contact", target = "datosContacto"),
            @Mapping(source = "personal", target = "datosPersonales"),
            @Mapping(source = "credential", target = "datosCredenciales"),
    })
    Usuario toUsuario(UserDTO userDTO);

    @InheritInverseConfiguration
    UserDTO toUserDTO(Usuario usuario);

    UserList toUserList(UserListDTO userListDTO);

    UserListDTO toUserListDTO(UserList userList);
}
