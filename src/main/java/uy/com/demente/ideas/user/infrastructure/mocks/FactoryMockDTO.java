package uy.com.demente.ideas.user.infrastructure.mocks;

import uy.com.demente.ideas.user.domain.model.enums.Sexo;
import uy.com.demente.ideas.user.domain.model.enums.TipoDocumento;
import uy.com.demente.ideas.user.infrastructure.dto.*;

import java.time.LocalDate;

public class FactoryMockDTO {

    public static UserDTO getUser() {

        CredentialDTO userCredential = CredentialDTO
                .builder()
                .username("Arruk")
                .password("Quinquenio")
                .build();

        PersonalDTO userPersonal = PersonalDTO.builder()
                .birthday(LocalDate.of(1987, 4, 20))
                .document("4566884-9")
                .folioNumber(1234568)
                .gender(Sexo.MASCULINO)
                .lastname("Gonzalez")
                .name("Diego")
                .serie("OLAZ567")
                .typeDocument(TipoDocumento.CON_CHIP)
                .build();

        ContactDTO userContact = ContactDTO.builder()
                .cellphone("099456789")
                .email("arruk@xperteleven.com")
                .receiveEmails(true)
                .betBySMS(true)
                .location("Montevideo")
                .build();

        return UserDTO.builder()
                .credential(userCredential)
                .personal(userPersonal)
                .contact(userContact)
                .build();
    }

    public static UserListDTO getUserList() {
        UserListDTO userListDTO =
                UserListDTO.builder()
                        .user(getUser())
                        .user(getUser())
                        .user(getUser())
                        .build();
        return userListDTO;
    }
}
