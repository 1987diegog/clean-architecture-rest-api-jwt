package uy.com.demente.ideas.user.domain.mocks;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uy.com.demente.ideas.user.domain.model.DatosContacto;
import uy.com.demente.ideas.user.domain.model.DatosCredenciales;
import uy.com.demente.ideas.user.domain.model.DatosPersonales;
import uy.com.demente.ideas.user.domain.model.Role;
import uy.com.demente.ideas.user.domain.model.UserList;
import uy.com.demente.ideas.user.domain.model.Usuario;
import uy.com.demente.ideas.user.domain.model.enums.TipoDocumento;
import uy.com.demente.ideas.user.domain.model.enums.RoleType;

public class FactoryMockDomain {

    private Logger log = LoggerFactory.getLogger(FactoryMockDomain.class);

    public static Usuario getUser() {

        Role roleAdmin = Role.builder().authority(RoleType.ROLE_ADMIN.name()).build();

        DatosCredenciales userCredential = DatosCredenciales
                .builder()
                .username("Arruk")
                .password("$2a$10$68GILt4wsH2Xnwd4vHdXVOwMOTntqW3xmkZJ25WuvjEFDNic29kRi")
                .build();

        DatosPersonales userPersonal = DatosPersonales.builder()
                .fechaNacimiento(LocalDate.of(1987, 4, 20))
                .numeroDocumento("4566884-9")
                .numeroFolio(1234568)                
                .apellidos("Gonzalez")
                .nombres("Diego")
                .serie("OLAZ567")
                .tipoDocumento(TipoDocumento.CON_CHIP)
                .build();

        DatosContacto userContact = DatosContacto.builder()
                .celular("099456789")
                .email("arruk@xperteleven.com")
                .recibeEmail(true)
                .apostarPorSms(true)
                .departamentoResidencia("Montevideo")
                .build();

        return Usuario.builder() 
                .datosCredenciales(userCredential)
                .datosPersonales(userPersonal)
                .datosContacto(userContact)
                .role(roleAdmin)
                .build();
    }


    public static UserList getUserList() {
        UserList userList =
                UserList.builder()
                        .user(getUser())
                        .user(getUser())
                        .user(getUser())
                        .build();
        return userList;
    }
}
