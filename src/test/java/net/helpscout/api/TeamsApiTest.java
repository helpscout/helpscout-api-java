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

public class TeamsApiTest extends AbstractApiClientTest {


    @Test
    @SneakyThrows
    public void shouldReturnPageOfTeams() {
        stubGET("/v1/teams.json", "teams");

        val teams = client.getTeams();

        assertThat(teams.getItems(), hasSize(1));
        assertThat(teams.getCount(), is(1));
    }

    @Test
    @SneakyThrows
    public void shouldReturnPageOfTeams_WhenUsingFieldsParams() {
        stubGET("/v1/teams.json?fields=id", "teams");

        val teams = client.getTeams(ImmutableList.of("id"));

        assertThat(teams.getItems(), hasSize(1));
    }


    @Test
    @SneakyThrows
    public void shouldReturnPageOfTeams_WhenCustomParams() {
        stubGET("/v1/teams.json?param=value", "teams");

        val teams = client.getTeams(ImmutableMap.of("param","value"));

        assertThat(teams.getItems(), hasSize(1));
    }


    @Test
    @SneakyThrows
    public void shouldReturnTeam() {
        stubGET("/v1/teams/100.json", "team");

        val team = client.getTeam(100L);

        assertThat(team.getId(), equalTo(100L));
    }

    @Test
    @SneakyThrows
    public void shouldReturnTeam_WhenUsingFieldsParams() {
        stubGET("/v1/teams/100.json?fields=id", "team");

        val team = client.getTeam(100L, ImmutableList.of("id"));

        assertThat(team.getId(), equalTo(100L));
    }

    @Test
    @SneakyThrows
    public void shouldReturnTeamMembers() {
        stubGET("/v1/teams/1/members.json", "users");

        val users = client.getTeamMembers(1L);

        assertThat(users.getItems(), hasSize(1));
    }

}
