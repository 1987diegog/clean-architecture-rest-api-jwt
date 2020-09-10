package uy.com.demente.ideas.user.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uy.com.demente.ideas.transversal.handler.exception.BadRequestException;
import uy.com.demente.ideas.transversal.handler.exception.InternalServerErrorException;
import uy.com.demente.ideas.user.application.mapper.UserMapper;
import uy.com.demente.ideas.user.domain.UsuarioClient;
import uy.com.demente.ideas.user.domain.model.UserList;
import uy.com.demente.ideas.user.domain.model.Usuario;
import uy.com.demente.ideas.user.infrastructure.dto.UserDTO;
import uy.com.demente.ideas.user.infrastructure.dto.UserListDTO;

@Service
public class UserService {

    private Logger log = LoggerFactory.getLogger(UserService.class);

    private final UsuarioClient usuarioClient;

    private final UserMapper userMapper;

    public UserService(UsuarioClient usuarioClient, UserMapper userMapper) {
        this.usuarioClient = usuarioClient;
        this.userMapper = userMapper;
    }

    public UserDTO signIn(UserDTO usuarioDTO) throws BadRequestException, InternalServerErrorException {
        Usuario user = userMapper.toUsuario(usuarioDTO);
        Usuario usuarioCreated = usuarioClient.signIn(user);
        UserDTO returnUserDTO = userMapper.toUserDTO(usuarioCreated);
        return returnUserDTO;
    }

    public UserListDTO getUsers() throws BadRequestException, InternalServerErrorException {
        UserList userList = usuarioClient.getUsers();
        UserListDTO returnUserListDTO = userMapper.toUserListDTO(userList);
        return returnUserListDTO;
    }

    public UserDTO findUserById(Long idUser) throws BadRequestException, InternalServerErrorException {
        Usuario userBQM = usuarioClient.findUserById(idUser);
        UserDTO returnUserDTO = userMapper.toUserDTO(userBQM);
        return returnUserDTO;
    }

    public UserDTO update(Long idUser, UserDTO userDTO) throws BadRequestException, InternalServerErrorException {
        // Todo: Ver que decision se toma...
        // Usuario user = usuarioClient.findUserById(id);
        // Usuario newData = userMapper.toUser(userDTO);
        // set all atributes...
        Usuario newData = userMapper.toUsuario(userDTO);
        Usuario usuarioUpdated = usuarioClient.update(idUser, newData);
        UserDTO returnUserDTO = userMapper.toUserDTO(usuarioUpdated);
        return returnUserDTO;
    }

}
