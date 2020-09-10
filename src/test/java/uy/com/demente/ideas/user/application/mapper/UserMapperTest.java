package uy.com.demente.ideas.user.application.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uy.com.demente.ideas.user.domain.mocks.FactoryMockDomain;
import uy.com.demente.ideas.user.domain.model.UserList;
import uy.com.demente.ideas.user.domain.model.Usuario;
import uy.com.demente.ideas.user.infrastructure.dto.UserDTO;
import uy.com.demente.ideas.user.infrastructure.dto.UserListDTO;
import uy.com.demente.ideas.user.infrastructure.mocks.FactoryMockDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserMapperTest {

    private Logger log = LoggerFactory.getLogger(UserMapperTest.class);

    @Autowired
    private UserMapper userMapper;

    @Test
    public void shouldCreateUserDTOFromUserDomainWhitMapper() {

        Usuario user = FactoryMockDomain.getUser();
        UserDTO userDTO = userMapper.toUserDTO(user);

        log.info(userDTO.toString());

        assertObjects(userDTO, user);
    }

    @Test
    public void shouldCreateUserDomainFromUserDTOWithMapper() {

        UserDTO userDTO = FactoryMockDTO.getUser();
        Usuario user = userMapper.toUsuario(userDTO);

        log.info(user.toString());

        assertObjects(userDTO, user);
    }

    @Test
    public void shouldCreateUserListDomainFromUserListDTOWithMapper() {

        UserListDTO userListDTO = FactoryMockDTO.getUserList();
        UserList userList = userMapper.toUserList(userListDTO);

        log.info(userList.toString());

        assertEquals(userListDTO.getUsers().size(), userList.getUsers().size());
    }

    @Test
    public void shouldCreateUserListDTOFromUserListWithMapper() {

        UserList userList = FactoryMockDomain.getUserList();
        UserListDTO userListDTO = userMapper.toUserListDTO(userList);

        assertEquals(userListDTO.getUsers().size(), userList.getUsers().size());
    }

    private void assertObjects(UserDTO userDTO, Usuario user) {

        assertEquals(userDTO.getContact().getCellphone(), user.getDatosContacto().getCelular());
        assertEquals(userDTO.getContact().getEmail(), user.getDatosContacto().getEmail());
        assertEquals(userDTO.getContact().getLocation(), user.getDatosContacto().getDepartamentoResidencia());
        assertEquals(userDTO.getContact().isBetBySMS(), user.getDatosContacto().isApostarPorSms());
        assertEquals(userDTO.getContact().isReceiveEmails(), user.getDatosContacto().isRecibeEmail());

        assertEquals(userDTO.getPersonal().getName(), user.getDatosPersonales().getNombres());
        assertEquals(userDTO.getPersonal().getLastname(), user.getDatosPersonales().getApellidos());
        assertEquals(userDTO.getPersonal().getBirthday(), user.getDatosPersonales().getFechaNacimiento());
        assertEquals(userDTO.getPersonal().getDocument(), user.getDatosPersonales().getNumeroDocumento());
        assertEquals(userDTO.getPersonal().getTypeDocument(), user.getDatosPersonales().getTipoDocumento());
        assertEquals(userDTO.getPersonal().getSerie(), user.getDatosPersonales().getSerie());
        assertEquals(userDTO.getPersonal().getFolioNumber(), user.getDatosPersonales().getNumeroFolio());

        assertEquals(userDTO.getCredential().getUsername(), user.getDatosCredenciales().getUsername());
        assertEquals(userDTO.getCredential().getPassword(), user.getDatosCredenciales().getPassword());
    }
}