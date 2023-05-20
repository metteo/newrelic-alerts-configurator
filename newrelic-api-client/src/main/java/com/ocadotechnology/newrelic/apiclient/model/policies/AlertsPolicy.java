package com.ocadotechnology.newrelic.apiclient.model.policies;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * See <a href="https://rpm.newrelic.com/api/explore/alerts_policies/list">Doc</a>
 */
@Value
@Builder
@AllArgsConstructor
public class AlertsPolicy {
    @JsonProperty
    Integer id;
    @JsonProperty("incident_preference")
    @JsonAlias({"incidentPreference"})
    String incidentPreference;
    @JsonProperty
    String name;
    @JsonProperty("created_at")
    Long createdAt;
    @JsonProperty("updated_at")
    Long updatedAt;
}
