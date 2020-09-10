package uy.com.demente.ideas.user.infrastructure.dto;

import uy.com.demente.ideas.shared.constanst.PatternConstants;
import uy.com.demente.ideas.user.domain.model.enums.Sexo;
import uy.com.demente.ideas.user.domain.model.enums.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonalDTO implements Serializable {

	private String name;

	private String lastname;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PatternConstants.DATE_PATTERN)
	private LocalDate birthday;

	private Sexo gender;

	private String document;

	private TipoDocumento typeDocument;

	private String serie;

	private int folioNumber;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
