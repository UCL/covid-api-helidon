package uk.ac.ucl.cs.covid.model.chartjs;

import java.util.Collections;
import java.util.List;

/**
 * Root object for response containing data by country.
 * Extension to ChartJS data property of a dataset.
 * See: https://www.chartjs.org/docs/latest/general/data-structures.html
 */
public class Root {

  /**
   * The name of the country.
   */
  private String country;

  /**
   * The collection of datasets.
   */
  private List<Dataset> datasets;

  /**
   * Obtains the collection of datasets.
   * @return the datasets.
   */
  public List<Dataset> getDatasets() {
    return Collections.unmodifiableList(datasets);
  }

  /**
   * Sets the collection of datasets.
   * @param aDatasetList the datasets
   */
  public void setDatasets(final List<Dataset> aDatasetList) {
    this.datasets = Collections.unmodifiableList(aDatasetList);
  }

  /**
   * Obtains the name of the country.
   * @return the name of the country.
   */
  public String getCountry() {
    return country;
  }

  /**
   * Sets the name of the country.
   * @param aCountry the country
   */
  public void setCountry(final String aCountry) {
    this.country = aCountry;
  }

}
