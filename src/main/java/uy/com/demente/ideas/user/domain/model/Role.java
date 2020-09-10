package uy.com.demente.ideas.user.domain.model;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class Role implements Serializable {

    private Long id;

    private String authority;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
