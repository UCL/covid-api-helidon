package uk.ac.ucl.cs.covid;

import java.time.LocalDate;
import java.util.List;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import uk.ac.ucl.cs.covid.model.chartjs.Data;
import uk.ac.ucl.cs.covid.model.chartjs.Dataset;
import uk.ac.ucl.cs.covid.model.chartjs.Root;
import uk.ac.ucl.cs.covid.persistence.DefaultModelEntity;
import uk.ac.ucl.cs.covid.persistence.ModelEntity;
import uk.ac.ucl.cs.covid.persistence.ModelScoresEntity;
import uk.ac.ucl.cs.covid.persistence.Repository;

@Dependent
public class ResourceController {

  @Inject
  private Repository repository;

  private Data buildDataPoint(LocalDate scoreDate, Double scoreValue) {
    Data data = new Data();
    data.setScoreDate(scoreDate);
    data.setScoreValue(scoreValue);
    return data;
  }

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
}
