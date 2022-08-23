package uk.ac.ucl.cs.covid.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "COUNTRY_TOTALS")
public class CountryTotalsEntity {

  /**
   * The table identifier.
   */
  @Id
  private Integer id;

  /**
   * ISO A3 code for a country.
   * Its value is used for querying data associated to a model.
   */
  @Column(name = "COUNTRY_ISO_A3", nullable = false, unique = true)
  private String countryIsoA3;

  /**
   * Total number of cases per 100K population.
   */
  @Column(name = "CASES_PER_100K", nullable = false)
  private Double casesPer100K;

  /**
   * Returns the ISO A3 code of the country associated with this model.
   * @return the ISO A3 code.
   */
  public String getCountryIsoA3() {
    return countryIsoA3;
  }

  /**
   * Returns the number of COVID cases per 100K habitants of a country.
   * @return the number of COVID cases
   */
  public Double getCasesPer100K() {
    return casesPer100K;
  }
}
