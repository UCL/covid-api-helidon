package uk.ac.ucl.cs.covid.persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Representation of a point in time of a collection of series of scores
 * produced by a model.
 */
@Entity
@Table(name = "PUBLIC.MODEL_SCORES")
public class ModelScoresEntity {

  /**
   * The table identifier.
   */
  @Id
  private Integer id;

  /**
   * When the row of scores was imported.
   * The timestamp is specific to the row, rather than the transaction.
   */
  @Column(name = "IMPORT_TIMESTAMP", nullable = false)
  private LocalDateTime importTimestamp;

  /**
   * The date of the scores.
   */
  @Column(name = "SCORE_DATE", nullable = false)
  private LocalDate scoreDate;

  /**
   * Search score with reduced effects from media.
   */
  @Column(name = "WEIGHTED_DEBIASED", nullable = false)
  private Double weightedDebiased;

  /**
   * Search score.
   */
  @Column(nullable = false)
  private Double weighted;

  /**
   * Historical trend (2011-2019).
   */
  @Column(name = "HISTORICAL_TREND", nullable = false)
  private Double historicalTrend;

  /**
   * The lower limit of the historical trend.
   */
  @Column(name = "HISTORICAL_TREND_LOWER", nullable = false)
  private Double historicalTrendLower;

  /**
   * The upper limit of the historical trend.
   */
  @Column(name = "HISTORICAL_TREND_UPPER", nullable = false)
  private Double historicalTrendUpper;

  /**
   * Reference to the model producing the scores.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "MODEL_ID", referencedColumnName = "ID")
  private ModelEntity model;

  /**
   * Returns the date of the scores.
   * @return the date of the scores as a {@link LocalDate}.
   */
  public LocalDate getScoreDate() {
    return scoreDate;
  }

  /**
   * Returns the weighted search score.
   * @return the search score.
   */
  public Double getWeighted() {
    return weighted;
  }

  /**
   * Returns the weighted search score with reduced effects from media.
   * @return the search score with reduced bias from media.
   */
  public Double getWeightedDebiased() {
    return weightedDebiased;
  }

  /**
   * Returns the historical trend (2011-2019).
   * @return the historical trend.
   */
  public Double getHistoricalTrend() {
    return historicalTrend;
  }

  /**
   * Returns the lower limit of the historical trend score.
   * @return the lower limit.
   */
  public Double getHistoricalTrendLower() {
    return historicalTrendLower;
  }

  /**
   * Returns the upper limit of the historical trend score.
   * @return the upper limit.
   */
  public Double getHistoricalTrendUpper() {
    return historicalTrendUpper;
  }
}
