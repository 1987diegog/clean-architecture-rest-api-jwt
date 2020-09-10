package uy.com.demente.ideas.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class DatosContacto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String celular;

    private String email;
    
    private String departamentoResidencia;

    private boolean apostarPorSms;

    private boolean recibeEmail;
 
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
