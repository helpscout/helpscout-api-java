package net.helpscout.api;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

public class UsersApiTest extends AbstractApiClientTest {


    @Test
    @SneakyThrows
    public void shouldReturnPageOfUsers() {
        stubGET("/v1/users.json", "users");

        val users = client.getUsers();

        assertThat(users.getItems(), hasSize(1));
        assertThat(users.getCount(), is(1));
    }

    @Test
    @SneakyThrows
    public void shouldReturnPageOfUsers_WhenUsingFieldsParams() {
        stubGET("/v1/users.json?fields=id", "users");

        val users = client.getUsers(ImmutableList.of("id"));

        assertThat(users.getItems(), hasSize(1));
    }


    @Test
    @SneakyThrows
    public void shouldReturnPageOfUsers_WhenCustomParams() {
        stubGET("/v1/users.json?param=value", "users");

        val Users = client.getUsers(ImmutableMap.of("param","value"));

        assertThat(Users.getItems(), hasSize(1));
    }


    @Test
    @SneakyThrows
    public void shouldReturnUser() {
        stubGET("/v1/users/110.json", "user");

        val user = client.getUser(110L);

        assertThat(user.getId(), equalTo(110L));
    }

    @Test
    @SneakyThrows
    public void shouldReturnUser_WhenUsingFieldsParams() {
        stubGET("/v1/users/110.json?fields=id", "user");

        val user = client.getUser(110L, ImmutableList.of("id"));

        assertThat(user.getId(), equalTo(110L));
    }

}
