package uy.com.demente.ideas.user.infrastructure.dto.mock;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class ContactMockDTO implements Serializable {

    @NonNull
    private String celular;

    @NonNull
    private String email;

    private boolean apostarPorSms;

    private boolean recibeEmail;

    private String localidad; // Ej. Montevideo

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
