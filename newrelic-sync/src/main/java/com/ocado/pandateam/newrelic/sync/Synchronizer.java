package com.ocado.pandateam.newrelic.sync;

import com.ocado.pandateam.newrelic.api.NewRelicApi;
import com.ocado.pandateam.newrelic.api.exception.NewRelicApiException;
import com.ocado.pandateam.newrelic.api.model.Application;

import java.util.Optional;

public class Synchronizer {

    private final NewRelicApi api;

    private final Configuration config;

    public Synchronizer(NewRelicApi api, Configuration config) {
        this.api = api;
        this.config = config;
    }

    public void sync() throws NewRelicApiException, NewRelicSyncException {
        Optional<Application> applicationOptional = api.getApplicationByName(config.getApplicationName());
        Application application = applicationOptional.orElseThrow(NewRelicSyncException::new);
        //Optional<AlertPolicy> policyOptional = api.getAlertPolicyByName(config.getApplicationName());
    }
}
