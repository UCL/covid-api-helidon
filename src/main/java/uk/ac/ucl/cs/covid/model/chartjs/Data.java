package uk.ac.ucl.cs.covid.model.chartjs;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class Data {

  @JsonbProperty("x")
  @NotNull
  private LocalDate scoreDate;

  @JsonbProperty("y")
  private Double scoreValue;

  /**
   * Default constructor for JSONB.
   */
  public Data() {
  }

  public LocalDate getScoreDate() {
    return scoreDate;
  }

  public void setScoreDate(LocalDate scoreDate) {
    this.scoreDate = scoreDate;
  }

  public Double getScoreValue() {
    return scoreValue;
  }

  public void setScoreValue(Double scoreValue) {
    this.scoreValue = scoreValue;
  }
}
