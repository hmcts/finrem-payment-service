package uk.gov.hmcts.reform.finrem.payments.model.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
@Builder
@ApiModel(description = "Request body model for Document Generation Request")
public class DocumentRequest {
    @ApiModelProperty(value = "Name of the template", required = true)
    @JsonProperty(value = "template", required = true)
    @NotBlank
    private final String template;
    @JsonProperty(value = "values", required = true)
    @ApiModelProperty(value = "Placeholder key / value pairs", required = true)
    private final Map<String, Object> values;
}
