package com.ocadotechnology.newrelic.apiclient.internal;

import com.ocadotechnology.newrelic.apiclient.PolicyItemApi;
import com.ocadotechnology.newrelic.apiclient.internal.client.NewRelicClient;
import com.ocadotechnology.newrelic.apiclient.internal.model.AlertsConditionList;
import com.ocadotechnology.newrelic.apiclient.internal.model.AlertsConditionWrapper;
import com.ocadotechnology.newrelic.apiclient.model.conditions.AlertsCondition;

import javax.ws.rs.client.Entity;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

public class DefaultAlertsConditionsApi extends ApiBase implements PolicyItemApi<AlertsCondition> {

    private static final String CONDITIONS_URL = "/v2/alerts_conditions";
    private static final String CONDITION_URL = "/v2/alerts_conditions/{condition_id}.json";
    private static final String CONDITION_POLICY_URL = "/v2/alerts_conditions/policies/{policy_id}.json";

    public DefaultAlertsConditionsApi(NewRelicClient client) {
        super(client);
    }

    @Override
    public List<AlertsCondition> list(int policyId) {
        return getPageable(
                client.target(CONDITIONS_URL).queryParam("policy_id", policyId).request(APPLICATION_JSON_TYPE),
                AlertsConditionList.class)
                .getList();
    }

    @Override
    public AlertsCondition create(int policyId, AlertsCondition condition) {
        return client
                .target(CONDITION_POLICY_URL)
                .resolveTemplate("policy_id", policyId)
                .request(APPLICATION_JSON_TYPE)
                .post(Entity.entity(new AlertsConditionWrapper(condition), APPLICATION_JSON_TYPE),
                        AlertsConditionWrapper.class)
                .getCondition();
    }

    @Override
    public AlertsCondition update(int conditionId, AlertsCondition condition) {
        return client
                .target(CONDITION_URL)
                .resolveTemplate("condition_id", conditionId)
                .request(APPLICATION_JSON_TYPE)
                .put(Entity.entity(new AlertsConditionWrapper(condition), APPLICATION_JSON_TYPE),
                        AlertsConditionWrapper.class)
                .getCondition();
    }

    @Override
    public AlertsCondition delete(int policyId, int conditionId) {
        return client
                .target(CONDITION_URL)
                .resolveTemplate("condition_id", conditionId)
                .request(APPLICATION_JSON_TYPE)
                .delete(AlertsConditionWrapper.class)
                .getCondition();
    }
}
