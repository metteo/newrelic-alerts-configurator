package com.ocadotechnology.newrelic.apiclient.internal;


import com.ocadotechnology.newrelic.apiclient.UsersApi;
import com.ocadotechnology.newrelic.apiclient.internal.client.NewRelicClient;
import com.ocadotechnology.newrelic.apiclient.internal.model.UserList;
import com.ocadotechnology.newrelic.apiclient.internal.model.UserWrapper;
import com.ocadotechnology.newrelic.apiclient.model.users.User;
import org.glassfish.jersey.uri.UriComponent;

import javax.ws.rs.client.Invocation;
import java.util.Optional;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.glassfish.jersey.uri.UriComponent.Type.QUERY_PARAM_SPACE_ENCODED;

public class DefaultUsersApi extends ApiBase implements UsersApi {
    private static final String USERS_URL = "/v2/users.json";
    private static final String USER_URL = "/v2/users/{user_id}.json";

    public DefaultUsersApi(NewRelicClient client) {
        super(client);
    }

    @Override
    public Optional<User> getByEmail(String userEmail) {
        String userEmailEncoded = UriComponent.encode(userEmail, QUERY_PARAM_SPACE_ENCODED);
        Invocation.Builder builder = client
                .target(USERS_URL)
                .queryParam("filter[email]", userEmailEncoded)
                .request(APPLICATION_JSON_TYPE);
        return getPageable(builder, UserList.class)
                .filter(user -> user.getEmail().equals(userEmail))
                .getSingle();
    }

    @Override
    public User getById(int userId) {
        return client
                .target(USER_URL)
                .resolveTemplate("user_id", userId)
                .request(APPLICATION_JSON_TYPE)
                .get(UserWrapper.class)
                .getUser();
    }
}
