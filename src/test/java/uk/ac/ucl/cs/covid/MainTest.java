
package uk.ac.ucl.cs.covid;

import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

import io.helidon.microprofile.tests.junit5.HelidonTest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@HelidonTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class MainTest {

    @Inject
    private WebTarget target;

    @Test
    public void testMetrics() throws Exception {
        Response response = target
                .path("metrics")
                .request()
                .get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void testHealth() throws Exception {
        Response response = target
                .path("health")
                .request()
                .get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void testHealthLive() {
      Response response = target
          .path("health/live")
          .request()
          .get();
      assertThat(response.getStatus(), is(200));
    }

    @Test
    public void testHealthReady() {
      Response response = target
          .path("health/ready")
          .request()
          .get();
      assertThat(response.getStatus(), is(200));
    }

    @Test
    public void testHealthStarted() {
      Response response = target
          .path("health/started")
          .request()
          .get();
      assertThat(response.getStatus(), is(200));
    }
}
