package uk.ac.ucl.cs.covid.model.chartjs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A data series.
 */
public class Dataset {

  /**
   * The collection of data points in this data set.
   */
  private List<Data> data = new ArrayList<>();

  /**
   * Default constructor for JSONB.
   */
  public Dataset() { }

  /**
   * Obtains the data points in this data set.
   * @return The collection of data points
   */
  public List<Data> getData() {
    return Collections.unmodifiableList(data);
  }

  /**
   * Sets the data points in this data set.
   * @param aData The collection of data points
   */
  public void setData(final List<Data> aData) {
    this.data = Collections.unmodifiableList(aData);
  }

  /**
   * Adds a data point to this data set.
   * @param datapoint The X,Y coordinate
   */
  public void addData(final Data datapoint) {
    this.data.add(datapoint);
  }
}
