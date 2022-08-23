package uk.ac.ucl.cs.covid;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import uk.ac.ucl.cs.covid.model.chartjs.Root;

@HelidonTest
public class ResourceTest {

  @Inject
  private WebTarget target;

  @Test
  public void testGetDataForCountryOk() {
    Response response = target.path("/country/GBR/data")
        .request().get();
    assertThat(response.getStatus(), is(200));
  }

  @Test
  public void testGetDataForCountryBadRequest() {
    Response response = target.path("/country/AAAA/data")
        .request().get();
    assertThat(response.getStatus(), is(400));
  }

  @Test
  public void testGetDataForCountryNotFound() {
    Response response = target.path("/country/AAA/data")
        .request().get();
    assertThat(response.getStatus(), is(404));
  }

  @Test
  public void testGetDataForCountryJsonSizes() {
    Root response = target.path("/country/GBR/data")
        .request().get(Root.class);
    assertThat(response.getDatasets().size(), is(5));
    IntStream.range(0, 5).forEachOrdered(n -> {
      assertThat(response.getDatasets().get(n).getData().size(), is(2));
      });
  }

  @Test
  public void testGetDataForWorldOk() {
    Response response = target.path("/world")
        .request().get();
    assertThat(response.getStatus(), is(200));
  }

}
