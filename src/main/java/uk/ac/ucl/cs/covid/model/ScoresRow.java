package uk.ac.ucl.cs.covid.model;

import java.time.LocalDate;

public class ScoresRow {

  /**
   * The date of the scores.
   */
  private LocalDate date;

  /**
   * Search score.
   */
  private Double weighted;

  /**
   * Search score with reduced effects from media.
   */
  private Double weightedDebiased;

  /**
   * Historical trend (2011-2019).
   */
  private Double historical;

  /**
   * The lower limit of the historical trend.
   */
  private Double historicalLower;

  /**
   * The upper limit of the historical trend.
   */
  private Double historicalUpper;

  /**
   * Sets the date of the score.
   * @param aDate the local date of the score.
   */
  public void setDate(final LocalDate aDate) {
    this.date = aDate;
  }

  /**
   * Sets the weighted search score.
   * @param aWeighted the search score.
   */
  public void setWeighted(final Double aWeighted) {
    this.weighted = aWeighted;
  }

  /**
   * Sets the weighted search score with reduced effects from media.
   * @param aWeightedDebiased the search score with reduced bias from media.
   */
  public void setWeightedDebiased(final Double aWeightedDebiased) {
    this.weightedDebiased = aWeightedDebiased;
  }

  /**
   * Sets the historical trend (2011-2019).
   * @param aHistorical the historical trend.
   */
  public void setHistorical(final Double aHistorical) {
    this.historical = aHistorical;
  }

  /**
   * Sets the lower limit of the historical trend score.
   * @param aHistoricalLower the lower limit.
   */
  public void setHistoricalLower(final Double aHistoricalLower) {
    this.historicalLower = aHistoricalLower;
  }

  /**
   * Sets the upper limit of the historical trend score.
   * @param aHistoricalUpper the upper limit.
   */
  public void setHistoricalUpper(final Double aHistoricalUpper) {
    this.historicalUpper = aHistoricalUpper;
  }
}
