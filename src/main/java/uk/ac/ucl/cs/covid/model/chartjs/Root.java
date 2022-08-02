package uk.ac.ucl.cs.covid.model.chartjs;

import java.util.List;

/**
 * The ChartJS data property of a dataset.
 * See: https://www.chartjs.org/docs/latest/general/data-structures.html
 */
public class Root {

  /**
   * The collection of datasets.
   */
  private List<Dataset> datasets;

  /**
   * Obtains the collection of datasets.
   * @return the datasets.
   */
  public List<Dataset> getDatasets() {
    return datasets;
  }

  /**
   * Sets the collection of datasets.
   * @param aDataset the datasets
   */
  public void setDatasets(final List<Dataset> aDataset) {
    this.datasets = aDataset;
  }
}
