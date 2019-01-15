package uk.gov.hmcts.reform.finrem.payments.model.ccd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderRefusal {
    @JsonProperty("orderRefusal")
    private List<String> orderRefusal;
    @JsonProperty("orderRefusalOther")
    private String orderRefusalOther;
    @JsonProperty("orderRefusalNotEnough")
    private List<String> orderRefusalNotEnough;
    @JsonProperty("orderRefusalNotEnoughOther")
    private String orderRefusalNotEnoughOther;
    @JsonProperty("orderRefusalDocs")
    private CaseDocument orderRefusalDocs;
    @JsonProperty("orderRefusalJudge")
    private String orderRefusalJudge;
    @JsonProperty("orderRefusalJudgeName")
    private String orderRefusalJudgeName;
    @JsonProperty("orderRefusalDate")
    private Date orderRefusalDate;
    @JsonProperty("estimateLengthOfHearing")
    private String estimateLengthOfHearing;
    @JsonProperty("whenShouldHearingTakePlace")
    private String whenShouldHearingTakePlace;
    @JsonProperty("whereShouldHearingTakePlace")
    private String whereShouldHearingTakePlace;
    @JsonProperty("otherHearingDetails")
    private String otherHearingDetails;
    @JsonProperty("orderRefusalAddComments")
    private String orderRefusalAddComments;
}
