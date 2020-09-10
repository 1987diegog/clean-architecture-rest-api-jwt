package uy.com.demente.ideas.user.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import uy.com.demente.ideas.user.domain.model.enums.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class DatosPersonales implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombres;

	private String apellidos;
	
	//TODO: Asi viene desde BQM, estandarizar!
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;

	private String numeroDocumento;

	private TipoDocumento tipoDocumento;

	private String serie;

	private int numeroFolio;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
