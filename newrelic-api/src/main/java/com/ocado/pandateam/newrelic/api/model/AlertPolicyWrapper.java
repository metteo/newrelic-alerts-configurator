package com.ocado.pandateam.newrelic.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class AlertPolicyWrapper {
    @JsonProperty
    AlertPolicy policy;
}
