package uy.com.demente.ideas.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class UsersConfirmPostParams implements Serializable {
    private static final long serialVersionUID = 1L;
    private String clave;
}
