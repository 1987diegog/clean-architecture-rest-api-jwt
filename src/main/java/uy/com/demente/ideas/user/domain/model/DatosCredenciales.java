package uy.com.demente.ideas.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class DatosCredenciales implements Serializable {

    private String username;

    private String password;

    @Override
    public String toString() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(this, ToStringStyle.JSON_STYLE);
        builder.setExcludeFieldNames("password");
        return builder.build();
    }
}
