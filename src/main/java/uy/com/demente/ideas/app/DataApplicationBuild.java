package uy.com.demente.ideas.app;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
public class DataApplicationBuild implements Serializable {

    @NonNull
    private String name;

    @NonNull
    private String buildVersion;

    @NonNull
    private String buildTimestamp;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
