package uy.com.demente.ideas.user.infrastructure.dto.mock;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
public class UserMockDTO implements Serializable {

    private ContactMockDTO datosContacto;

    private PersonalMockDTO datosPersonales;

    private CredentialMockDTO datosCredenciales;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
