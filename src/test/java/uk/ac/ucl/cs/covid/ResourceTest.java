package uk.ac.ucl.cs.covid;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import uk.ac.ucl.cs.covid.model.ScoresRow;
import uk.ac.ucl.cs.covid.model.chartjs.Dataset;
import uk.ac.ucl.cs.covid.model.chartjs.DatasetTag;
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
  public void testGetDataForCountryJsonLabel() {
    Root response = target.path("/country/GBR/data")
      .request().get(Root.class);
    List<String> expected = List.of(
      "historical trend",
      "historical trend lower",
      "historical trend upper",
      "weighted",
      "weighted debiased"
    );
    List<String> actual = response.getDatasets().stream()
      .map(d -> d.getDatasetTag().getLabel())
      .sorted()
      .toList();
    assertThat(actual, is(expected));
  }

  @Test
  public void testGetDataForCountryJsonTag() {
    Root response = target.path("/country/GBR/data")
      .request().get(Root.class);
    List<String> expected = List.of(
      "HISTORICAL",
      "HISTORICAL_LOWER",
      "HISTORICAL_UPPER",
      "WEIGHTED",
      "WEIGHTED_DEBIASED"
    );
    List<String> actual = response.getDatasets().stream()
      .map(d -> d.getDatasetTag().toString())
      .sorted()
      .toList();
    assertThat(actual, is(expected));
  }

  @Test
  public void testGetScoresForCountrySize() {
    ScoresRow[] response = target.path("/country/GBR/table")
      .request().get(ScoresRow[].class);
    assertThat(response.length, is(2));
  }

  @Test
  public void testGetDataForWorldOk() {
    Response response = target.path("/world")
        .request().get();
    assertThat(response.getStatus(), is(200));
  }

}
