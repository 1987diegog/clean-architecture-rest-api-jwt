package uy.com.demente.ideas.user.domain.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
 
    private DatosContacto datosContacto;

    private DatosPersonales datosPersonales;

    private DatosCredenciales datosCredenciales;

    @Singular
    private List<Role> roles;

}


