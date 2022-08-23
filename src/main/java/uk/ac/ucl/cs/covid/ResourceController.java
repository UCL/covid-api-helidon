package uk.ac.ucl.cs.covid;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import uk.ac.ucl.cs.covid.model.CountryTotals;
import uk.ac.ucl.cs.covid.model.chartjs.Data;
import uk.ac.ucl.cs.covid.model.chartjs.Dataset;
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
      weighted.addData(
          buildDataPoint(
              modelScores.getScoreDate(), modelScores.getWeighted()
              ));
      weightedDebiased.addData(
          buildDataPoint(
              modelScores.getScoreDate(), modelScores.getWeightedDebiased()
              ));
      historicalTrend.addData(
          buildDataPoint(
              modelScores.getScoreDate(), modelScores.getHistoricalTrend()
              ));
      historicalLower.addData(
          buildDataPoint(
              modelScores.getScoreDate(), modelScores.getHistoricalTrendLower()
              ));
      historicalUpper.addData(
          buildDataPoint(
              modelScores.getScoreDate(), modelScores.getHistoricalTrendUpper()
              ));
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
}
