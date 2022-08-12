package uk.ac.ucl.cs.covid.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "PUBLIC.MODEL_SCORES")
public class ModelScoresEntity {

  @Id
  private Integer id;

  @Column(name = "IMPORt_tiMESTAMP", nullable = false)
  private LocalDateTime importTimestamp;

  @Column(name = "SCORE_DATE", nullable = false)
  private LocalDate scoreDate;

  @Column(name = "WEIGHTED_DEBIASED", nullable = false)
  private Double weightedDebiased;

  @Column(nullable = false)
  private Double weighted;

  @Column(name = "HISTORICAL_TREND", nullable = false)
  private Double historicalTrend;

  @Column(name = "HISTORICAL_TREND_LOWER", nullable = false)
  private Double historicalTrendLower;

  @Column(name = "HISTORICAL_TREND_UPPER", nullable = false)
  private Double historicalTrendUpper;


}
