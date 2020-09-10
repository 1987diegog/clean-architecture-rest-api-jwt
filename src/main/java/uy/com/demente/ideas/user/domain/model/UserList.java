package uy.com.demente.ideas.user.domain.model;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class UserList implements Serializable {

    @Singular
    private List<Usuario> users;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
