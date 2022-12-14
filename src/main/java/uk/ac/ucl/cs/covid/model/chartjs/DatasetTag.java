package uk.ac.ucl.cs.covid.model.chartjs;

public enum DatasetTag {

  /**
   * Google search score with reduced media effects.
   */
  WEIGHTED_DEBIASED("weighted debiased"),

  /**
   * Google search score.
   */
  WEIGHTED("weighted"),

  /**
   * Historical trend.
   */
  HISTORICAL("historical trend"),

  /**
   * Historical trend lower confidence limit.
   */
  HISTORICAL_LOWER("historical trend lower"),

  /**
   * Historical trend upper confidence limit.
   */
  HISTORICAL_UPPER("historical trend upper");

  /**
   * The content of the label.
   */
  private final String label;

  DatasetTag(final String aLabel) {
    this.label = aLabel;
  }

  /**
   * Obtains the label for this tag.
   * @return the label as a String
   */
  public String getLabel() {
    return label;
  }
}
