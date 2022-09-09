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
   * The tag of the dataset.
   */
  private DatasetTag tag;

  /**
   * The label of the dataset to be used in a chart.
   */

  private String label;

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

  /**
   * Returns the tag of this dataset.
   * @return the tag as a String
   */
  public DatasetTag getDatasetTag() {
    return tag;
  }

  /**
   * Sets the tag of this dataset.
   * @param aTag A non-empty String with the tag
   */
  public void setDatasetTag(final DatasetTag aTag) {
    this.tag = aTag;
  }

  /**
   * Returns the label of this dataset.
   * @return the label as a String
   */
  public String getLabel() {
    return label;
  }

  /**
   * Sets the label of this dataset.
   * @param aLabel a String with the label.
   */
  public void setLabel(final String aLabel) {
    this.label = aLabel;
  }

}
