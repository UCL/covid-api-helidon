package uk.ac.ucl.cs.covid.model.chartjs;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * An (x,y) data point.
 * It expects the x coordinate to be a date.
 */
public class Data {

  /**
   * The date of the score.
   * It represents the X coordinate of the data point.
   */
  @JsonbProperty("x")
  @NotNull
  private LocalDate scoreDate;

  /**
   * The value of the score.
   * It represents the y coordinate of the data point.
   */
  @JsonbProperty("y")
  private Double scoreValue;

  /**
   * Default constructor for JSONB.
   */
  public Data() {
  }

  /**
   * Obtains the date of the score.
   * @return The date of the score
   */
  public LocalDate getScoreDate() {
    return scoreDate;
  }

  /**
   * Sets the date of the score.
   * @param aScoreDate The date of the score
   */
  public void setScoreDate(final LocalDate aScoreDate) {
    this.scoreDate = aScoreDate;
  }

  /**
   * Obtains the value of the score.
   * @return The value of the score
   */
  public Double getScoreValue() {
    return scoreValue;
  }

  /**
   * Sets the value of the score.
   * @param aScoreValue The value of the score
   */
  public void setScoreValue(final Double aScoreValue) {
    this.scoreValue = aScoreValue;
  }
}
