package uk.ac.ucl.cs.covid;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import uk.ac.ucl.cs.covid.model.CountryTotals;
import uk.ac.ucl.cs.covid.model.ScoresRow;
import uk.ac.ucl.cs.covid.model.chartjs.Data;
import uk.ac.ucl.cs.covid.model.chartjs.Dataset;
import uk.ac.ucl.cs.covid.model.chartjs.DatasetTag;
import uk.ac.ucl.cs.covid.model.chartjs.Root;
import uk.ac.ucl.cs.covid.persistence.CountryTotalsEntity;
import uk.ac.ucl.cs.covid.persistence.DefaultModelEntity;
import uk.ac.ucl.cs.covid.persistence.ModelEntity;
import uk.ac.ucl.cs.covid.persistence.ModelScoresEntity;
import uk.ac.ucl.cs.covid.persistence.Repository;

/**
 * Controller for accessing data on a country level.
 */
@Dependent
public class ResourceController {

  /**
   * CDI bean with repository of statements for accesssing JPA entities.
   */
  @Inject
  private Repository repository;

  /**
   * Build a data point.
   * @param scoreDate The date of the score.
   * @param scoreValue The value of the score.
   * @return a data point.
   */
  private Data buildDataPoint(
      final LocalDate scoreDate, final Double scoreValue
      ) {
    Data data = new Data();
    data.setScoreDate(scoreDate);
    data.setScoreValue(scoreValue);
    return data;
  }

  /**
   * Construct a message based on {@link uk.ac.ucl.cs.covid.model.chartjs.Root}.
   * @param countryIsoA3 the ISO A3 code of a country
   * @return the response message.
   */
  public Root getChartJsRoot(final String countryIsoA3) {
    DefaultModelEntity defaultModel = repository
        .findModel(countryIsoA3)
        .orElseThrow(NotFoundException::new);
    ModelEntity model = defaultModel.getModel();
    List<ModelScoresEntity> scoresList = repository
        .findAllModelScoresForModelId(model.getId());
    Dataset weighted = new Dataset();
    Dataset weightedDebiased = new Dataset();
    Dataset historicalTrend = new Dataset();
    Dataset historicalLower = new Dataset();
    Dataset historicalUpper = new Dataset();
    for (ModelScoresEntity modelScores: scoresList) {
      // WEIGHTED
      weighted.addData(
          buildDataPoint(
              modelScores.getScoreDate(), modelScores.getWeighted()
              ));
      weighted.setDatasetTag(DatasetTag.WEIGHTED);
      weighted.setLabel(DatasetTag.WEIGHTED.getLabel());
      // WEIGHTED DEBIASED
      weightedDebiased.addData(
          buildDataPoint(
              modelScores.getScoreDate(), modelScores.getWeightedDebiased()
              ));
      weightedDebiased.setDatasetTag(DatasetTag.WEIGHTED_DEBIASED);
      weightedDebiased.setLabel(DatasetTag.WEIGHTED_DEBIASED.getLabel());
      // HISTORICAL
      historicalTrend.addData(
          buildDataPoint(
              modelScores.getScoreDate(), modelScores.getHistoricalTrend()
              ));
      historicalTrend.setDatasetTag(DatasetTag.HISTORICAL);
      historicalTrend.setLabel(DatasetTag.HISTORICAL.getLabel());
      // HISTORICAL LOWER LIMIT
      historicalLower.addData(
          buildDataPoint(
              modelScores.getScoreDate(), modelScores.getHistoricalTrendLower()
              ));
      historicalLower.setDatasetTag(DatasetTag.HISTORICAL_LOWER);
      historicalLower.setLabel(DatasetTag.HISTORICAL_LOWER.getLabel());
      // HISTORICAL UPPER LIMIT
      historicalUpper.addData(
          buildDataPoint(
              modelScores.getScoreDate(), modelScores.getHistoricalTrendUpper()
              ));
      historicalUpper.setDatasetTag(DatasetTag.HISTORICAL_UPPER);
      historicalUpper.setLabel(DatasetTag.HISTORICAL_UPPER.getLabel());
    }
    Root chartJsRoot = new Root();
    chartJsRoot.setDatasets(
        List.of(
            weighted,
            weightedDebiased,
            historicalTrend,
            historicalLower,
            historicalUpper
            ));
    return chartJsRoot;
  }

  /**
   * Constructs a message based on
   * {@link uk.ac.ucl.cs.covid.model.CountryTotals}.
   * @return The totals per country as a List.
   */
  public List<CountryTotals> getTotalsPerCountry() {
    final List<CountryTotalsEntity> countryTotalsList = repository
        .findNumberCases100k();
    return countryTotalsList.stream()
      .map((CountryTotalsEntity e) -> {
        CountryTotals c = new CountryTotals();
        c.setCountryIsoA3(e.getCountryIsoA3());
        c.setCasesPer100K(e.getCasesPer100K());
        return c;
      }).collect(Collectors.toList());
  }

  /**
   * Constructs a message based on
   * {@link uk.ac.ucl.cs.covid.model.ScoresRow}.
   * @param countryCode the ISO A3 code of a country.
   * @return The response message.
   */
  public ScoresRow[] getScores(final String countryCode) {
    DefaultModelEntity defaultModel = repository
      .findModel(countryCode)
      .orElseThrow(NotFoundException::new);
    ModelEntity model = defaultModel.getModel();
    List<ModelScoresEntity> scoresList = repository
      .findAllModelScoresForModelId(model.getId());
    return scoresList.stream()
      .map((ModelScoresEntity e) -> {
        ScoresRow row = new ScoresRow();
        row.setDate(e.getScoreDate());
        row.setWeighted(e.getWeighted());
        row.setWeightedDebiased(e.getWeightedDebiased());
        row.setHistorical(e.getHistoricalTrend());
        row.setHistoricalLower(e.getHistoricalTrendLower());
        row.setHistoricalUpper(e.getHistoricalTrendUpper());
        return row;
      }).toArray(ScoresRow[]::new);
  }
}
