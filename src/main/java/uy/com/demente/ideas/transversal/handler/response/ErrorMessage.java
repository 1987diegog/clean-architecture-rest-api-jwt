package uy.com.demente.ideas.transversal.handler.response;


import uy.com.demente.ideas.shared.constanst.PatternConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class ErrorMessage implements Serializable {

    private Integer status;

    private String error;

    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PatternConstants.DATE_PATTERN)
    private LocalDateTime timestamp;
}