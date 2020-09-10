package uy.com.demente.ideas.user.infrastructure.dto.mock;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class CredentialMockDTO implements Serializable {

    @NonNull
    private String usuario;

    @NonNull
    private String password;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
