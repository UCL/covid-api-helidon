package uk.ac.ucl.cs.covid.model.chartjs;

import java.util.List;

public class Dataset {

  private List<Data> data;

  /**
   * Default constructor for JSONB.
   */
  public Dataset() { }

  public List<Data> getData() {
    return data;
  }

  public void setData(List<Data> data) {
    this.data = data;
  }
}
