package uk.ac.ucl.cs.covid.model.chartjs;

import java.util.List;

/**
 * A data series.
 */
public class Dataset {

  /**
   * The collection of data points in this data set.
   */
  private List<Data> data;

  /**
   * Default constructor for JSONB.
   */
  public Dataset() { }

  /**
   * Obtains the data points in this data set.
   * @return The collection of data points
   */
  public List<Data> getData() {
    return data;
  }

  /**
   * Sets the data points in this data set.
   * @param aData The collection of data points
   */
  public void setData(final List<Data> aData) {
    this.data = aData;
  }
}
