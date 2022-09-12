package uk.ac.ucl.cs.covid.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PUBLIC.COUNTRY")
public class CountryEntity {

  /**
   * The table identifier.
   */
  @Id
  private Integer id;

  /**
   * ISO A3 code for a country.
   * Its value is used for querying data associated to a model.
   */
  @Column(name = "COUNTRY_ISO_A3", nullable = false)
  private String countryIsoA3;

  /**
   * Name of the country.
   * Not necessarily the official name, but a name to be used as a label
   * in the frontend.
   */
  @Column(name = "NAME_OF_COUNTRY", nullable = false)
  private String nameOfCountry;

  /**
   * Obtains the name of the country.
   * @return the name of the country
   */
  public String getNameOfCountry() {
    return nameOfCountry;
  }
}
