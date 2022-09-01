package uk.ac.ucl.cs.covid.model;

import jakarta.json.bind.annotation.JsonbProperty;

/**
 * Number of cases per 100K.
 */
public class CountryTotals {

  /**
   * Default constructor for JSONB.
   */
  public CountryTotals() { }

  /**
   * ISO A3 code for a country.
   */
  @JsonbProperty("country")
  private String countryIsoA3;

  /**
   * Total number of cases per 100K population.
   */
  private Double casesPer100K;

  /**
   * Sets the ISO code for the country.
   * @param aCountryIsoA3 the ISO A3 code.
   */
  public void setCountryIsoA3(final String aCountryIsoA3) {
    this.countryIsoA3 = aCountryIsoA3;
  }

  /**
   * Sets the number of COVID cases per 100K population.
   * @param aCasesPer100K the number of cases.
   */
  public void setCasesPer100K(final Double aCasesPer100K) {
    this.casesPer100K = aCasesPer100K;
  }

}
