package uk.ac.ucl.cs.covid;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import uk.ac.ucl.cs.covid.model.chartjs.Root;
import uk.ac.ucl.cs.covid.persistence.Repository;

@Dependent
public class ResourceController {

  @Inject
  private Repository repository;

  public Root getChartJsRoot(final String countryIsoA3) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
