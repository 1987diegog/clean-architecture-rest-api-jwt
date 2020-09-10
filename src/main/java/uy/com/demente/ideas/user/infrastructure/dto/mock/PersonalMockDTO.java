package uy.com.demente.ideas.user.infrastructure.dto.mock;

import uy.com.demente.ideas.user.domain.model.enums.Sexo;
import uy.com.demente.ideas.user.domain.model.enums.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class PersonalMockDTO implements Serializable {

	@NonNull
	private String nombres;

	@NonNull
	private String apellidos;

	@NonNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate fechaNacimiento;

	@NonNull
	private Sexo sexo;

	@NonNull
	private String numeroDocumento;

	@NonNull
	private TipoDocumento tipoDocumento;

	@NonNull
	private String serie;

	private int numeroFolio;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
