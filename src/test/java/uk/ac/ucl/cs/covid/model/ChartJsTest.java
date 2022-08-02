package uk.ac.ucl.cs.covid.model;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.junit.jupiter.api.Test;
import uk.ac.ucl.cs.covid.model.chartjs.Data;
import uk.ac.ucl.cs.covid.model.chartjs.Dataset;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ChartJsTest {

  private static final Jsonb JSONB = JsonbBuilder.create();

  @Test
  public void testData() {
    Data instance = new Data();
    instance.setScoreDate(LocalDate.now());
    instance.setScoreValue(0.1d);
    String expected = String.format("{\"x\":\"%s\",\"y\":0.1}", LocalDate.now());
    String actual = JSONB.toJson(instance);
    assertThat(actual, equalTo(expected));
  }

  @Test
  public void testDataset() {
    Dataset instance = new Dataset();
    Data data = new Data();
    data.setScoreDate(LocalDate.now());
    data.setScoreValue(0.1d);
    instance.setData(List.of(data));
    String expected = String.format("{\"data\":[{\"x\":\"%s\",\"y\":0.1}]}", LocalDate.now());
    String actual = JSONB.toJson(instance);
    assertThat(actual, equalTo(expected));
  }
}
