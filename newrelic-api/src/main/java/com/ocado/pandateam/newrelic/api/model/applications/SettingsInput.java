package com.ocado.pandateam.newrelic.api.model.applications;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class SettingsInput {
    @JsonProperty("app_apdex_threshold")
    Float appApdexThreshold;
    @JsonProperty("end_user_apdex_threshold")
    Float endUserApdexThreshold;
    @JsonProperty("enable_real_user_monitoring")
    Boolean enableRealUserMonitoring;
}
